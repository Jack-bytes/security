package cn.coonu.security.crypto.RSA;

import cn.coonu.security.base64.Base64Util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

/**
 * 生成RSA key
 *
 * @author Jack Wang
 */
public class RSAGenerator {

    /**
     * 此算法可以有很多:
     * https://docs.oracle.com/javase/8/docs/technotes/guides/security/StandardNames.html#KeyPairGenerator;
     */
    private final static String ALGORITHM = "RSA";

    /**
     * 随机生成密钥对
     *
     * @param path    生成秘钥文件的目标路径(不包含文件名, 仅是路径)
     * @param keySize 长度,一般1024, 或者2048
     */
    public static void generateKey(String path, int keySize) {

        KeyPairGenerator generator;
        try {
            generator = KeyPairGenerator.getInstance(ALGORITHM);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return;
        }

        generator.initialize(keySize, new SecureRandom());
        // 生成一个密钥对，保存在keyPair中
        KeyPair keyPair = generator.generateKeyPair();
        // 得到私钥
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
        // 得到公钥
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        // 得到公钥字符串
        String publicKeyString = Base64Util.encode(publicKey.getEncoded());
        // 得到私钥字符串
        String privateKeyString = Base64Util.encode(privateKey.getEncoded());

        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }
        BufferedWriter publicKeyBw = null;
        BufferedWriter privateKeyBw = null;
        try {
            // 将密钥对写入到文件
            FileWriter publicKeyFw = new FileWriter(path + "/publicKey.keystore");
            FileWriter privateKeyFw = new FileWriter(path + "/privateKey.keystore");
            publicKeyBw = new BufferedWriter(publicKeyFw); //TODO 是否需要设置编码;
            privateKeyBw = new BufferedWriter(privateKeyFw);
            publicKeyBw.write(publicKeyString);
            privateKeyBw.write(privateKeyString);
            //flush操作可以不用, close中自带flush, 但是close可能会出现异常, 导致无法flush, 所以还是加上这个操作;
            publicKeyBw.flush();
            privateKeyBw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (publicKeyBw != null) {
                    publicKeyBw.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (privateKeyBw != null) {
                    privateKeyBw.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
