package cn.coonu.security.base64;

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
     * base64url 编码  不含结尾"=", 并且标准编码中的'+'和'/'会分别被替换成'-'和'_'
     *
     * @param source 源数据
     * @return 编码后的字符串
     */
    public static String encodeForUrl(String source) {
        return encodeForUrl(source.getBytes(StandardCharsets.UTF_8));
    }

    /**
     * base64url 编码  不含结尾"=", 并且标准编码中的'+'和'/'会分别被替换成'-'和'_'
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
     * base64 解码, 不含'='也能正常解码(含'='的话得包含正确数量的等号), 但是不能包含非正常字符, URL编码的'-'和'_'也不例外, 不能包含
     *
     * @param base64Code code
     * @return byte[]
     */
    public static byte[] decode(String base64Code) {
        return Base64.getDecoder().decode(base64Code);
    }

    /**
     * base64 解码, 含'='也能正确解码(但是得含正确数量的等号), 但是不能包含非正常字符, 标准编码的'+'和'/'也不例外, 不能包含;
     *
     * @param base64UrlCode code
     * @return byte[]
     */
    public static byte[] decodeUrl(String base64UrlCode) {
        return Base64.getUrlDecoder().decode(base64UrlCode);
    }

}
