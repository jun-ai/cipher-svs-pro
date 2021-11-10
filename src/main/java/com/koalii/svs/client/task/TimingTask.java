package com.koalii.svs.client.task;

import com.alibaba.fastjson.JSONObject;
import com.koalii.svs.client.dao.SvsMapper;
import com.koalii.svs.client.dto.ResultUtil;
import com.koalii.svs.client.dto.TSignRecord;
import com.koalii.svs.client.sm.SM3DegistUtil;
import com.koalii.svs.client.util.HttpClientUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Component
@Slf4j
public class TimingTask {

    @Value("${svs.signMessageUrl}")
    private String signMessageUrl;

    @Value("${svs.verifySignMessageUrl}")
    private String verifySignMessageUrl;

    @Value("${svs.certAlias}")
    private String certAlias;

    @Autowired
    private SvsMapper svsMapper;

    /**
     * 签名
     * @return
     */
    public ResultUtil signMessage(String signMessage){

        HashMap header=new HashMap();
        JSONObject param = new JSONObject();
        header.put("Content-Type", "application/json;charset=utf-8");
        log.info("请求头为"+header);
        param.put("b64OriginData", signMessage);
        param.put("certAlias",certAlias);
        log.info("请求体为"+param.toString());
        String result = HttpClientUtils.doPostJson(signMessageUrl, header, param.toString());
        return ResultUtil.success(result);
    }

    /**
     * 验证不带原文的消息签名
     * @param b64OriginData
     * @param b64SignedMessage
     * @return
     */
    public Boolean verifySignMessage(String b64OriginData,String b64SignedMessage){
        HashMap header=new HashMap();
        header.put("Content-Type", "application/json;charset=utf-8");
        log.info("请求头为"+header);
        JSONObject param = new JSONObject();
        param.put("b64OriginData",b64OriginData);
        param.put("b64SignedMessage",b64SignedMessage);
        log.info("请求体为"+param.toString());
        HttpClientUtils.doPostJson(verifySignMessageUrl,header,param.toString());
        return false;
    }

    /**
     * 每天的2点、8点、14点、20点都执行一次
     * @return
     */
    @Scheduled(cron="0/5 * * * * ?")
    //@Scheduled(cron="0 0 2,8,12,14,20 * * ?")
    public ResultUtil sign() {
        try {
            List<String> fName = new ArrayList<>();
            List<String> allFile = new ArrayList<>();

            allFile.add("/etc/passwd");
            allFile.add("/etc/passwd-");
            allFile.add("/etc/shadow");
            allFile.add("/etc/shadow-");
            allFile.add("/etc/gshadow");
            allFile.add("/etc/gshadow-");
            allFile.add("/etc/group");
            allFile.add("/etc/group-");
            allFile.add("/etc/pam.d/su");
            allFile.add("/etc/sudoers");
            Date currentTime = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

            String today = formatter.format(currentTime);

            allFile.forEach(f -> {
                try{
                    FileInputStream inputStream = new FileInputStream(new File(f));
                    byte[] buffer=new byte[1024];
                    int len=0;
                    ByteArrayOutputStream bos = new ByteArrayOutputStream();
                    while((len=inputStream.read(buffer))!=-1){
                        bos.write(buffer,0,len);
                    }
                    bos.flush();
                    byte[] originData = bos.toByteArray();
                    String signMessageDetach = SM3DegistUtil.hash(originData.toString());
                    String loginSignName=(today+f).replace("/","-")+".txt";
                    String parent="/datafile/logsign";
                    String filename=parent+"/"+loginSignName;
                    /**
                     * 签名成功并插入数据库
                     */
                    ResultUtil resultUtil = signMessage(signMessageDetach);
                    if ("200".equals(resultUtil.getCode())){
                        TSignRecord tSignRecord=new TSignRecord();
                        log.info(filename+"访问控制信息表文件签名成功");
                        String result=(String) resultUtil.getData();
                        log.info("result为"+result);
                        JSONObject js = JSONObject.parseObject(result);
                        String b64SignedMessage = js.get("b64SignedMessage").toString();
                        tSignRecord.setSign_type("sign_etc");
                        tSignRecord.setSign_b64OriginData(signMessageDetach);
                        tSignRecord.setSign_b64SignedMessage(b64SignedMessage);
                        svsMapper.insert(tSignRecord);
                        log.info("插入数据库控制信息表文件签名记录成功");
                    }else {
                        log.info(filename+"访问控制信息表文件签名失败");
                    }
                    File signFile = new File(filename);
                    if (!signFile.exists()){
                        signFile.createNewFile();
                    }
                    FileUtils.writeStringToFile(signFile,signMessageDetach,true);
                    fName.add(filename);
                    inputStream.close();
                    bos.close();
                }catch (Exception e){
                    log.error("错误信息:"+e);
                }
            }
            );
        } catch (Exception e) {
            log.error("错误信息:"+e);
        }
        return  ResultUtil.fail("500","fail");
    }

    /**
     * 每天的2点、8点、14点、20点都执行一次
     * @return
     */
    @Scheduled(cron="0/5 * * * * ?")
    //@Scheduled(cron="0 0 2,8,12,14,20 * * ?")
    public ResultUtil signLogMessage() {
        try {
            List<String> fName = new ArrayList<>();
            List<String> allFile = new ArrayList<>();

            //allFile.add("/var/log/user");
            //allFile.add("/var/log/auth");
            allFile.add("/var/log/syslog");
            allFile.add("/var/log/messages");
            Date currentTime = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

            String today = formatter.format(currentTime);

            allFile.forEach(f -> {
                        try{
                            FileInputStream inputStream = new FileInputStream(new File(f));
                            byte[] buffer=new byte[1024];
                            int len=0;
                            ByteArrayOutputStream bos = new ByteArrayOutputStream();
                            while((len=inputStream.read(buffer))!=-1){
                                bos.write(buffer,0,len);
                            }
                            bos.flush();
                            byte[] originData = bos.toByteArray();
                            String signMessageDetach = SM3DegistUtil.hash(originData.toString());

                            String loginSignName=(today+f).replace("/","-")+".txt";
                            String parent="/datafile/logsign";
                            String filename=parent+"/"+loginSignName;
                            /**
                             * 签名
                             */
                            ResultUtil resultUtil = signMessage(signMessageDetach);
                            if ("200".equals(resultUtil.getCode())){
                                log.info(filename+"访问日志文件签名成功");
                            }else {
                                log.info(filename+"访问日志文件签名失败");
                            }
                            File signFile = new File(filename);
                            if (!signFile.exists()){
                                signFile.createNewFile();
                            }
                            FileUtils.writeStringToFile(signFile,signMessageDetach,true);
                            fName.add(filename);
                            inputStream.close();
                            bos.close();
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  ResultUtil.fail("500","fail");
    }
}

