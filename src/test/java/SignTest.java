public class SignTest {
//
//    public static String ip = "60.247.61.98";
//    public static int port = 15009;
//    public static int timeout_sec = 20;
//    public static String certAlias = "sm2";
//    public static String data = "hello world";
//    public static Svs2ClientHelper helper = Svs2ClientHelper.getInstance();
//    static {
//        helper.init(ip, port, timeout_sec);
//    }
//    public static String sign(byte [] data){
//        Svs2ClientHelper.SvsResultData svsResultData = helper.cdbPkcs7DetachSignEx(data,certAlias);
//        System.out.println("errno:"+svsResultData.m_errno);
//        System.out.println("b64SignedData:"+svsResultData.m_b64SignedData);
//        System.out.println("b64Cert:"+svsResultData.m_b64Cert);
//        return svsResultData.m_b64SignedData;
//    }
//
//    public static boolean verify(byte [] data,String signData){
//
//        Svs2ClientHelper.SvsResultData svsResultData  = helper.cdbPkcs7DetachVerifyEx(signData,data);
//
//        System.out.println("errno:"+svsResultData.m_errno);
//
//        System.out.println("b64Cert:"+svsResultData.m_b64Cert);
//
//        return svsResultData.m_errno == 0;
//    }
//
//    public static void main(String[] args) {
//        String signData = sign(data.getBytes());
//        System.out.println("签名数据："+ signData);
//        boolean signResult = verify(data.getBytes(),signData);
//        System.out.println("验签是否成功：" + signResult);
//    }
}
