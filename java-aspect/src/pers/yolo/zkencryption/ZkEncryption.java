package pers.yolo.zkencryption;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class ZkEncryption {

    /**
     * 使用SHA-1算法加密，然后由BASE64编码
     */
    public static void main(String[] args) throws NoSuchAlgorithmException {
        // 字符串格式，形如 用户名:密码
        String usernameAndPasswordStr = "username:password";
        String algorithm = "SHA-1";
        byte[] digest = MessageDigest.getInstance(algorithm).digest(usernameAndPasswordStr.getBytes(StandardCharsets.UTF_8));
        String encryptStr = Base64.getEncoder().encodeToString(digest);
        System.out.println(encryptStr);
    }

}
