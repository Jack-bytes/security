package cn.coonu.security.crypto.digest;

import cn.coonu.security.base64.Base64Util;
import cn.coonu.security.crypto.algorithm.Algorithm;
import cn.coonu.security.crypto.algorithm.HmacAlgorithms;

import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 获取摘要 算法包含MD5等, 详见 algorithm包
 *
 * @author Jack Wang
 * @see Algorithm
 */
@SuppressWarnings("unused")
public class DigestUtil {

    private static final char[] HEX_CHAR = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    // ++++++++++++++++++++++++ MD5 AND SHA, 返回的摘要信息均是16进制的字符串 ++++++++++++++++++++++++

    public static String md5(byte[] source) {
        return digest(source, Algorithm.MD5);
    }

    public static String sha1(byte[] source) {
        return digest(source, Algorithm.SHA1);
    }

    public static String sha256(byte[] source) {
        return digest(source, Algorithm.SHA_256);
    }

    public static String sha512(byte[] source) {
        return digest(source, Algorithm.SHA_512);
    }

    public static String digest(String source, Algorithm algorithm) {
        return digest(source.getBytes(StandardCharsets.UTF_8), algorithm);
    }

    /**
     * 生成摘要
     * 注: 在MessageDigest使用时,勿将其定义为单例(static),此对象非线程安全,每次使用时均使用MessageDigest.getInstance()方法获取对象;
     *
     * @param source    源数据
     * @param algorithm 算法名称
     * @return 16进制摘要信息字符串
     */
    public static String digest(byte[] source, Algorithm algorithm) {
        byte[] result;
        try {
            result = MessageDigest.getInstance(algorithm.getAlgorithmName()).digest(source);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("没有此算法!", e);
        }
        return toHexString(result);
    }

    // +++++++++++++++++++++++++++ HMAC, 返回的摘要信息均是16进制的字符串 +++++++++++++++++++++++++++

    public static String hmacMD5(byte[] source, byte[] key) {
        return hmac(source, key, HmacAlgorithms.HMAC_MD5);
    }

    public static String hmacSHA1(byte[] source, byte[] key) {
        return hmac(source, key, HmacAlgorithms.HMAC_SHA1);
    }

    public static String hmacSHA256(byte[] source, byte[] key) {
        return hmac(source, key, HmacAlgorithms.HMAC_SHA_256);
    }

    public static String hmacSHA512(byte[] source, byte[] key) {
        return hmac(source, key, HmacAlgorithms.HMAC_SHA_512);
    }

    /**
     * HMAC 算法生成16进制摘要信息字符串;
     *
     * @param source    待生成摘要信息的源数据;
     * @param key       秘钥,相当于盐值,可以用方法生成,也可以自己定义;
     * @param algorithm 算法;
     * @return 16进制摘要信息字符串;
     */
    public static String hmac(byte[] source, byte[] key, HmacAlgorithms algorithm) {
        Mac mac;
        try {
            mac = Mac.getInstance(algorithm.getAlgorithmName());
            mac.init(new SecretKeySpec(key, algorithm.getAlgorithmName()));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("没有指定算法", e);
        } catch (InvalidKeyException e) {
            throw new RuntimeException("秘钥非法", e);
        }
        return toHexString(mac.doFinal(source));
    }

    /**
     * 生成16进制形式的 key
     *
     * @param algorithm 算法
     * @return 16进制形式的 key
     */
    public static String generateHexHmacKey(HmacAlgorithms algorithm) {
        return toHexString(generateHmacKey(algorithm));
    }

    /**
     * 生成 base64 形式的 key
     *
     * @param algorithm 算法
     * @return base64 形式的 key
     */
    public static String generateBase64HmacKey(HmacAlgorithms algorithm) {
        return Base64Util.encode(generateHmacKey(algorithm));
    }

    /**
     * 生成 HMAC 秘钥;
     *
     * @param algorithm 算法名称
     * @return 秘钥byte数组;
     */
    public static byte[] generateHmacKey(HmacAlgorithms algorithm) {
        byte[] secretKey = null;
        try {
            secretKey = KeyGenerator.getInstance(algorithm.getAlgorithmName()).generateKey().getEncoded();
        } catch (NoSuchAlgorithmException ignore) {
        }
        return secretKey;
    }

    // ++++++++++++++++++++++++ byte 转换成 16进制字符串 ++++++++++++++++++++++++

    /**
     * 将byte转换成16进制字符串;
     * 此方法性能优于下面个方法, 不过测试的只是少量数据转换,但是在digest中,也只会有少量数据转换;
     *
     * @param source 源
     * @return 16进制字符串
     */
    public static String toHexString(byte[] source) {
        StringBuilder builder = new StringBuilder();
        for (byte item : source) {
            //这里item会被当做32位int来处理, 正数高位全是0, 负数高位全是1, 所以需要& 0xf0, 去除负数时高位的1, 右移4位后也是int值;
            //>>> 无符号右移;
            builder.append(HEX_CHAR[(item & 0xf0) >>> 4]); // 0xf0 为16进制, 转换成2进制是11110000
            // 取出字节的低四位 作为索引得到相应的十六进制标识符
            builder.append(HEX_CHAR[item & 0x0f]);
        }
        return builder.toString();
    }

    /**
     * 将16进制字符串转化成byte数组;
     *
     * @param hexString 16进制字符串
     * @return byte数组;
     */
    public static byte[] hexToBytes(String hexString) {

        // TODO
        return null;
    }

    public static String toHexString2(byte[] source) {
        StringBuilder builder = new StringBuilder();
        for (byte item : source) {
            builder.append(Integer.toHexString((item & 0xFF) | 0x100), 1, 3);
        }
        return builder.toString();
    }

}
