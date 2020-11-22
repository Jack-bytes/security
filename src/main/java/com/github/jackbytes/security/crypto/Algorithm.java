package com.github.jackbytes.security.crypto;

/**
 * 摘要算法名称
 *
 * @author Jack Wang
 */
@SuppressWarnings("unused")
public interface Algorithm {

    // ++++++++++++++++++++++++ Digest常用 ++++++++++++++++++++++++
    //32char
    String MD5 = "MD5";

    //40char
    String SHA1 = "SHA-1";

    //64char
    String SHA256 = "SHA-256";

    //128char
    String SHA512 = "SHA-512";

    // ++++++++++++++++++++++++ Digest不常用 ++++++++++++++++++++++++

    //32char
    String MD2 = "MD2";

    //56char
    String SHA224 = "SHA-224";

    //96char
    String SHA384 = "SHA-384";


    String SHA512224 = "SHA-512/224";

    String SHA512256 = "SHA-512/256";

    // ++++++++++++++++++++++++ HMAC专用 ++++++++++++++++++++++++

    String HMAC_MD5 = "HmacMD5";
    String HMAC_SHA1 = "HmacSHA1";
    String HMAC_SHA224 = "HmacSHA224";
    String HMAC_SHA256 = "HmacSHA256";
    String HMAC_SHA384 = "HmacSHA384";
    String HMAC_SHA512 = "HmacSHA512";
}
