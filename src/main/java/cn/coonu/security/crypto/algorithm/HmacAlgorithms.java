package cn.coonu.security.crypto.algorithm;

/**
 * Hmac摘要算法名称
 */
public enum HmacAlgorithms {

    HMAC_MD5("HmacMD5", ""),
    HMAC_SHA1("HmacSHA1", ""),
    HMAC_SHA_224("HmacSHA224", ""),
    HMAC_SHA_256("HmacSHA256", ""),
    HMAC_SHA_384("HmacSHA384", ""),
    HMAC_SHA_512("HmacSHA512", "");

    private final String algorithmName;
    private final String description;

    /**
     * 构造方法只能是private 所以这里省略,因为枚举类只能在类中定义好对象,不能在外部定义;
     *
     * @param algorithmName 算法名称
     * @param description   描述
     */
    HmacAlgorithms(String algorithmName, String description) {
        this.algorithmName = algorithmName;
        this.description = description;
    }

    public String getAlgorithmName() {
        return this.algorithmName;
    }

    public String getDescription() {
        return this.description;
    }

}
