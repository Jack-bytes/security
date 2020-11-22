package com.github.jackbytes.security.crypto.RSA;

import com.github.jackbytes.security.base64.Base64Util;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

public class RSAUtil {

    /**
     * 公钥加密过程
     *
     * @param publicKey 公钥
     * @param plainText 明文数据
     * @return 密文 byte[]
     * @throws Exception 加密过程中的异常信息
     */
    public static byte[] encrypt(RSAPublicKey publicKey, byte[] plainText) throws Exception {

        int a = publicKey.getModulus().bitLength();
        System.out.println("keySize: " + a);

        // 使用默认RSA
        //不同的padding, 加密时的最大长度和输出长度不一样;
        // 所以输入输出长度由padding决定;  是从RSACipher的init方法看出来的;
        Cipher cipher = Cipher.getInstance("RSA");     // todo 搞明白这里应该填些什么,每一段的作用,还有,是这里决定了块大小,还是在生成秘钥的时候就决定了;

        System.out.println(cipher.getAlgorithm());
        // cipher= Cipher.getInstance("RSA", new BouncyCastleProvider());
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);

        //outputSize是通过  (key.getModulus().bitLength()) + 7 >> 3   得到,
        // 在RSACipher中的反编译后173 行, 代码如下
        //this.outputSize = RSACore.getByteLength(var6.getModulus());    var6类型是RSAKey
        //反编译后在173,实际可能不在;
        //outputSize要在cipher初始化后才能拿到,说明outSize是和key相关联的;
        System.out.println(cipher.getParameters());
        System.out.println("OutputSize: " + cipher.getOutputSize(1));
        byte[] output = cipher.doFinal(plainText);
        return output;

    }

    /**
     * 私钥加密过程
     *
     * @param privateKey    私钥
     * @param plainTextData 明文数据
     * @return
     * @throws Exception 加密过程中的异常信息
     */
    public static byte[] encrypt(RSAPrivateKey privateKey, byte[] plainTextData) throws Exception {
        if (privateKey == null) {
            throw new Exception("加密私钥为空, 请设置");
        }
        Cipher cipher = null;
        try {
            // 使用默认RSA
            cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE, privateKey);
            System.out.println("BlockSize: " + cipher.getBlockSize());
            System.out.println("OutputSize: " + cipher.getOutputSize(1));
            byte[] output = cipher.doFinal(plainTextData);
            return output;
        } catch (NoSuchAlgorithmException e) {
            throw new Exception("无此加密算法");
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
            return null;
        } catch (InvalidKeyException e) {
            throw new Exception("加密私钥非法,请检查");
        } catch (IllegalBlockSizeException e) {
            throw new Exception("明文长度非法");
        } catch (BadPaddingException e) {
            throw new Exception("明文数据已损坏");
        }
    }


    /**
     * 私钥解密过程
     *
     * @param privateKey      私钥
     * @param ciphertextBytes 密文数据
     * @return 明文
     * @throws Exception 解密过程中的异常信息
     */
    public static byte[] decrypt(RSAPrivateKey privateKey, byte[] ciphertextBytes) throws Exception {

        return decryptCommon(privateKey, ciphertextBytes);
    }

    /**
     * 公钥解密过程
     *
     * @param publicKey       公钥
     * @param ciphertextBytes 密文数据
     * @return 明文
     * @throws Exception 解密过程中的异常信息
     */
    public static byte[] decrypt(RSAPublicKey publicKey, byte[] ciphertextBytes) throws Exception {

        return decryptCommon(publicKey, ciphertextBytes);
    }

    private static byte[] decryptCommon(Key key, byte[] ciphertextBytes)
            throws NoSuchPaddingException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException, Exception {

        if (key == null) {
            throw new Exception("解密秘钥为空!");
        }
        if (ciphertextBytes == null || ciphertextBytes.length == 0) {
            throw new Exception("密文数据错误!");
        }

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {
            // 使用默认RSA
            Cipher cipher = Cipher.getInstance("RSA");
            // cipher= Cipher.getInstance("RSA", new BouncyCastleProvider());
            cipher.init(Cipher.DECRYPT_MODE, key);// 这里可选择解密还是加密模式

            int length = ciphertextBytes.length;
            int outputSize = cipher.getOutputSize(length);

            int offset = 0;
            while (offset < length) {
                byte[] plaintextBlock = cipher.doFinal(ciphertextBytes, offset, outputSize);
                //System.out.println(plaintextBlock.length + "**************");
                outputStream.write(plaintextBlock);
                offset += outputSize;
            }
        } catch (NoSuchAlgorithmException | IOException e) {
            e.printStackTrace();
        } finally {
            try {
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return outputStream.toByteArray();

    }





































    /**
     * 签名算法
     */
    public static final String SIGN_ALGORITHMS = "SHA1WithRSA";

    /**
     * RSA签名
     *
     * @param content    待签名数据
     * @param privateKey 商户私钥
     * @param encode     字符集编码
     * @return 签名值
     */
    public static String sign(String content, String privateKey, String encode) {
        try {
            PKCS8EncodedKeySpec priPKCS8 = new PKCS8EncodedKeySpec(Base64Util.decode(privateKey));

            KeyFactory keyf = KeyFactory.getInstance("RSA");
            PrivateKey priKey = keyf.generatePrivate(priPKCS8);

            java.security.Signature signature = java.security.Signature.getInstance(SIGN_ALGORITHMS);

            signature.initSign(priKey);
            signature.update(content.getBytes(encode));

            byte[] signed = signature.sign();

            return Base64Util.encode(signed);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static String sign(String content, String privateKey) {
        try {
            PKCS8EncodedKeySpec priPKCS8 = new PKCS8EncodedKeySpec(Base64Util.decode(privateKey));
            KeyFactory keyf = KeyFactory.getInstance("RSA");
            PrivateKey priKey = keyf.generatePrivate(priPKCS8);
            java.security.Signature signature = java.security.Signature.getInstance(SIGN_ALGORITHMS);
            signature.initSign(priKey);
            signature.update(content.getBytes());
            byte[] signed = signature.sign();
            return Base64Util.encode(signed);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * RSA验签名检查
     *
     * @param content   待签名数据
     * @param sign      签名值
     * @param publicKey 分配给开发商公钥
     * @param encode    字符集编码
     * @return 布尔值
     */
    public static boolean doCheck(String content, String sign, String publicKey, String encode) {
        try {
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            byte[] encodedKey = Base64Util.decode(publicKey);
            PublicKey pubKey = keyFactory.generatePublic(new X509EncodedKeySpec(encodedKey));


            java.security.Signature signature = java.security.Signature
                    .getInstance(SIGN_ALGORITHMS);

            signature.initVerify(pubKey);
            signature.update(content.getBytes(encode));

            boolean bverify = signature.verify(Base64Util.decode(sign));
            return bverify;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public static boolean doCheck(String content, String sign, String publicKey) {
        try {
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            byte[] encodedKey = Base64Util.decode(publicKey);
            PublicKey pubKey = keyFactory.generatePublic(new X509EncodedKeySpec(encodedKey));


            java.security.Signature signature = java.security.Signature
                    .getInstance(SIGN_ALGORITHMS);

            signature.initVerify(pubKey);
            signature.update(content.getBytes());

            boolean bverify = signature.verify(Base64Util.decode(sign));
            return bverify;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


}
