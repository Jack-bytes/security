package cn.com.lezz.security.base64;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * base64 编码util
 * <p>
 * Done!
 *
 * @author Jack Wang
 */
@SuppressWarnings("unused")
public class Base64Util {

    // ++++++++++++++++++++++++++++++++++++++++++++++++ URL编码 ++++++++++++++++++++++++++++++++++++++++++++++++

    /**
     * base64url 编码  不含结尾"="
     *
     * @param source 源数据
     * @return 编码后的字符串
     */
    public static String encodeForUrl(String source) {
        return encodeForUrl(source.getBytes(StandardCharsets.UTF_8));
    }

    /**
     * base64url 编码  不含结尾"="
     *
     * @param source 源数据
     * @return 编码后的字符串
     */
    public static String encodeForUrl(byte[] source) {
        return Base64.getUrlEncoder().withoutPadding().encodeToString(source);
    }

    // ++++++++++++++++++++++++++++++++++++++++++++++++ 标准编码 ++++++++++++++++++++++++++++++++++++++++++++++++

    /**
     * base64 标准编码
     *
     * @param source 源数据
     * @return 编码后的字符串
     */
    public static String encode(String source) {
        return encode(source.getBytes(StandardCharsets.UTF_8));
    }

    /**
     * base64 标准编码
     *
     * @param source 源数据
     * @return 编码后的字符串
     */
    public static String encode(byte[] source) {
        return Base64.getEncoder().encodeToString(source);
    }

    // ++++++++++++++++++++++++++++++++++++++++++++++++ 解码 ++++++++++++++++++++++++++++++++++++++++++++++++

    /**
     * base64 解码
     *
     * @param base64Code code
     * @return byte[]
     */
    public static byte[] decode(String base64Code) {
        return Base64.getDecoder().decode(base64Code);
    }

    /**
     * base64 解码
     *
     * @param base64UrlCode code
     * @return byte[]
     */
    public static byte[] decodeUrl(String base64UrlCode) {
        return Base64.getUrlDecoder().decode(base64UrlCode);
    }

}
