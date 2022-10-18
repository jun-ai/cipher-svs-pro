//package com.koalii.svs.client.task;
//
//import com.koalii.svs.client.dto.ResultUtil;
//import com.koalii.svs.client.sm.SM3DegistUtil;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.commons.io.FileUtils;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//
//import java.io.ByteArrayOutputStream;
//import java.io.File;
//import java.io.FileInputStream;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//
//@Component
//@Slf4j
//public class TimingTask {
//
//    /**
//     *  每五秒执行一次
//     */
//    @Scheduled(cron="0/7 * * * * ?")
//    public void executeFileDownLoadTask() {
//        System.out.println("定时任务启动");
//    }
//
//    @Scheduled(cron="0/3 * * * * ?")
//    public ResultUtil sign() {
//        try {
//            List<String> fName = new ArrayList<>();
//            List<String> allFile = new ArrayList<>();
//
//            allFile.add("/etc/passwd");
//            allFile.add("/etc/passwd-");
//            allFile.add("/etc/shadow");
//            allFile.add("/etc/shadow-");
//            allFile.add("/etc/gshadow");
//            allFile.add("/etc/gshadow-");
//            allFile.add("/etc/group");
//            allFile.add("/etc/group-");
//            allFile.add("/etc/pam.d/su");
//            allFile.add("/etc/sudoers");
//
//            Date currentTime = new Date();
//            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
//
//            String today = formatter.format(currentTime);
//
//            allFile.forEach(f -> {
//                try{
//                    FileInputStream inputStream = new FileInputStream(new File(f));
//                    byte[] buffer=new byte[1024];
//                    int len=0;
//                    ByteArrayOutputStream bos = new ByteArrayOutputStream();
//                    while((len=inputStream.read(buffer))!=-1){
//                        bos.write(buffer,0,len);
//                    }
//                    bos.flush();
//                    byte[] originData = bos.toByteArray();
//                    String signMessageDetach = SM3DegistUtil.hash(originData.toString());
//                    String loginSignName=(today+f).replace("/","-")+".txt";
//                    String parent="/datafile/logsign";
//                    String filename=parent+"/"+loginSignName;
//
//                    File signFile = new File(filename);
//                    log.info("signFile的目录为"+signFile.toString());
//                    if (!signFile.exists()){
//                        signFile.createNewFile();
//                    }
//                    FileUtils.writeStringToFile(signFile,signMessageDetach,true);
//                    fName.add(filename);
//                    inputStream.close();
//                    bos.close();
////                    return  ResultUtil.success("200","success");
//                }catch (Exception e){
//                    e.printStackTrace();
//                }
//            }
//            );
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return  ResultUtil.fail("500","fail");
//    }
//}
//
