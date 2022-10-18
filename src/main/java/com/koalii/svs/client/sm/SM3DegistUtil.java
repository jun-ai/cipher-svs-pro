//package com.koalii.svs.client.sm;
//
//import lombok.extern.slf4j.Slf4j;
//
//import java.io.UnsupportedEncodingException;
//import java.security.Security;
//import java.util.Arrays;
//import org.bouncycastle.crypto.digests.SM3Digest;
//import org.bouncycastle.jce.provider.BouncyCastleProvider;
//import org.bouncycastle.pqc.math.linearalgebra.ByteUtils;
//
//@Slf4j
//public class SM3DegistUtil {
//    private static final String ENCODING = "UTF-8";
//    static {
//        Security.addProvider(new BouncyCastleProvider());
//    }
//
//    /**
//     * sm3算法
//     *
//     * @param paramStr 待操作字符串
//     * @return 返回加密后，固定长度=32的16进制字符串
//     */
//    public static String hash(String paramStr) {
//        if (paramStr == null){
//            throw new IllegalStateException("值不能为空!");
//        }
//        // 将返回的hash值转换成16进制字符串
//        String resultHexString = "";
//        try {
//            // 将字符串转换成byte数组
//            byte[] srcData = paramStr.getBytes(ENCODING);
//            // 调用hash()
//            byte[] resultHash = getHash(srcData);
//            // 将返回的hash值转换成16进制字符串
//            resultHexString = ByteUtils.toHexString(resultHash);
//            return resultHexString.toUpperCase();
//        } catch (UnsupportedEncodingException e) {
//            log.info("SM3算法失败：",e);
//            throw new  IllegalStateException("SM3算法异常");
//        }
//
//    }
//
//    /**
//     * 返回长度=32的byte数组
//     *
//     * @param srcData 源数据
//     * @return 数组
//     * @Description  生成对应的hash值
//     */
//    public static byte[] getHash(byte[] srcData) {
//
//        try {
//            SM3Digest digest = new SM3Digest();
//            digest.update(srcData, 0, srcData.length);
//            byte[] hash = new byte[digest.getDigestSize()];
//            digest.doFinal(hash, 0);
//            return hash;
//        }catch (Exception e){
//            log.info("返回byte数组失败：",e);
//            throw new IllegalStateException("返回byte数组异常");
//        }
//    }
//
//    /**
//     * 判断源数据与hash数据是否一致
//     *
//     * @param srcStr       原字符串
//     * @param sm3HexString 16进制字符串
//     * @return 校验结果
//     * @Description 通过验证原数组和生成的hash数组是否为同一数组，验证2者是否为同一数据
//     */
//    public static boolean verify(String srcStr, String sm3HexString) {
//        boolean flag = false;
//        try {
//            byte[] srcData = srcStr.getBytes(ENCODING);
//            byte[] sm3Hash = ByteUtils.fromHexString(sm3HexString);
//            byte[] newHash = getHash(srcData);
//            if (Arrays.equals(newHash, sm3Hash)) {
//                flag = true;
//            }
//            return flag;
//        } catch (UnsupportedEncodingException e) {
//            log.info("判断源数据与hash数据错误：",e);
//            throw new IllegalStateException("源数据与hash数据异常");
//        }
//
//    }
//}
//
