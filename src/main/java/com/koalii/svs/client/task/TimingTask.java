package com.koalii.svs.client.task;

import com.alibaba.fastjson.JSONObject;
import com.koalii.svs.client.dto.ResultUtil;
import com.koalii.svs.client.sm.SM3DegistUtil;
import com.koalii.svs.client.util.HttpClientUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
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

    /**
     * 签名
     * @return
     */
    @Scheduled(cron="0/9 * * * * ?")
    //@RequestMapping("/signMessage")
    public ResultUtil signMessage(){

        HashMap header=new HashMap();
        JSONObject param = new JSONObject();
        String signMessage="NyuEXEMpxTcEAmeXqoNHcsyUHZc4naDhWvLLRSDkg0gnoleBUuAAbEgqivzRY40Pn2ezqG9YvxmlOp3CwJLSgeMN0sAOVKhohh7btehWxKqHMBWmk1zxi+M8aSvKhUMZuoeTbS6KoNpGOyCeLW81fpRsj8BNlH3BuQm4yK0ReDc=";
        header.put("Content-Type", "application/json;charset=utf-8");
        log.info("请求头为"+header);
        param.put("b64OriginData", signMessage);
        param.put("certAlias",certAlias);
        log.info("请求体为"+param.toString());
        String result = HttpClientUtils.doPostJson(signMessageUrl, header, param.toString());
        JSONObject jsresult = JSONObject.parseObject(result);
        String b64SignedMessage = (String)jsresult.get("b64SignedMessage");
        log.info("B64 签名消息:"+b64SignedMessage);
        verifySignMessage(signMessage,b64SignedMessage);
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

    //@Scheduled(cron="0/3 * * * * ?")
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

                    File signFile = new File(filename);
                    log.info("signFile的目录为"+signFile.toString());
                    if (!signFile.exists()){
                        signFile.createNewFile();
                    }
                    FileUtils.writeStringToFile(signFile,signMessageDetach,true);
                    fName.add(filename);
                    inputStream.close();
                    bos.close();
//                    return  ResultUtil.success("200","success");
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

