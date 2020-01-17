package security;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 对称加密算法
 *
 */
public class SymEncodeUtil {
    
    public static void main(String[] args) throws NoSuchAlgorithmException {
        
        String userId = "2135073786";
        String companyId = "6a965039b84d47fabb197153b9f724a6";
        String content = String.valueOf((userId + companyId).hashCode());
        String key = "1578888423628";
        System.out.print(encrypt(content, key));
        
    }
    
    public static String encrypt(String str, String key) throws NoSuchAlgorithmException {
        if (null == str || 0 == str.length()) {
            return null;
        }
        char[] hexDigits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        // 创建SHA1算法消息摘要对象
        MessageDigest mdTemp = MessageDigest.getInstance("SHA1");
        // 使用指定的字节数组更新摘要。
        mdTemp.update(str.getBytes(StandardCharsets.UTF_8));
        // 生成的哈希值的字节数组
        byte[] md = mdTemp.digest();
        // SHA1算法生成信息摘要关键过程
        int j = md.length;
        char[] buf = new char[j * 2];
        int k = 0;
        for (int i = 0; i < j; i++) {
            byte byte0 = md[i];
            buf[k++] = hexDigits[byte0 >>> 4 & 0xf];
            buf[k++] = hexDigits[byte0 & 0xf];
        }
        return new String(buf);
    }
    
//    /**
//     * 
//     * @param content
//     * @param key
//     * @return
//     */
//    public static String encrypt(String content, String key) {
//        try {
//            SecureRandom random = new SecureRandom();
//            DESKeySpec desKey = new DESKeySpec(key.getBytes());
//            // 创建一个密匙工厂，然后用它把DESKeySpec转换成
//            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
//            SecretKey securekey = keyFactory.generateSecret(desKey);
//            // Cipher对象实际完成加密操作
//            Cipher cipher = Cipher.getInstance("DES");
//            // 用密匙初始化Cipher对象,ENCRYPT_MODE用于将 Cipher 初始化为加密模式的常量
//            cipher.init(Cipher.ENCRYPT_MODE, securekey, random);
//            byte[] bytes = cipher.doFinal(content.getBytes()); // 按单部分操作加密或解密数据，或者结束一个多部分操作
//            return new String(bytes);
//        } catch (Throwable e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//    
//    /**
//     * 解密
//     * 
//     * @param src
//     *            byte[]
//     * @param password
//     *            String
//     * @return byte[]
//     * @throws Exception
//     */
//    public static String decrypt(String content, String key) throws Exception {
//        // DES算法要求有一个可信任的随机数源
//        SecureRandom random = new SecureRandom();
//        // 创建一个DESKeySpec对象
//        DESKeySpec desKey = new DESKeySpec(key.getBytes());
//        // 创建一个密匙工厂
//        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");// 返回实现指定转换的
//                                                                            // Cipher
//                                                                            // 对象
//        // 将DESKeySpec对象转换成SecretKey对象
//        SecretKey securekey = keyFactory.generateSecret(desKey);
//        // Cipher对象实际完成解密操作
//        Cipher cipher = Cipher.getInstance("DES");
//        // 用密匙初始化Cipher对象
//        cipher.init(Cipher.DECRYPT_MODE, securekey, random);
//        // 真正开始解密操作
//        byte[] bytes = cipher.doFinal(content.getBytes());
//        return new String(bytes, StandardCharsets.UTF_8);
//    }

}
