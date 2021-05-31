package cn.coonu.security.crypto.RSA;

import cn.coonu.security.base64.Base64Util;

import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

public class RSAKeyLoader {

    private final static String ALGORITHM = "RSA";

    /**
     * 从字符串中加载公钥
     *
     * @param publicKey 公钥数据 Base64 字符串
     * @return 公钥
     */
    public static RSAPublicKey loadPublicKey(String publicKey) throws InvalidKeySpecException {

        byte[] key = Base64Util.decode(publicKey);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(key);
        KeyFactory factory = null;
        try {
            factory = KeyFactory.getInstance(ALGORITHM);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
        return (RSAPublicKey) factory.generatePublic(keySpec);
    }

    /**
     * 字符串中加载私钥
     *
     * @param privateKey 私钥数据 Base64 字符串
     * @return 私钥
     */
    public static RSAPrivateKey loadPrivateKey(String privateKey) throws InvalidKeySpecException {

        byte[] key = Base64Util.decode(privateKey);
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(key);
        KeyFactory factory = null;
        try {
            factory = KeyFactory.getInstance(ALGORITHM);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
        return (RSAPrivateKey) factory.generatePrivate(keySpec);

    }
}
