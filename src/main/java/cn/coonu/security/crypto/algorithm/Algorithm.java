package cn.coonu.security.crypto.algorithm;

/**
 * 摘要算法名称
 *
 * @author Jack Wang
 */
public enum Algorithm {

    // ++++++++++++++++++++++++ Digest常用 ++++++++++++++++++++++++

    MD5("MD5", "32char"),
    SHA1("SHA-1", "40char"),
    SHA_256("SHA-256", "64char"),
    SHA_512("SHA-512", "128char"),

    // ++++++++++++++++++++++++ Digest不常用 ++++++++++++++++++++++++

    MD2("MD2", "32char"),
    SHA_224("SHA-224", "56char"),
    SHA_384("SHA-384", "96char"),
    SHA_512_224("SHA-512/224", ""),
    SHA_512_256("SHA-512/256", ""),
    SHA3_224("SHA3-224", ""),
    SHA3_256("SHA3-256", ""),
    SHA3_384("SHA3-384", ""),
    SHA3_512("SHA3-512", "");

    private final String algorithmName;
    private final String description;

    /**
     * 构造方法只能是private 所以这里省略,因为枚举类只能在类中定义好对象,不能在外部定义;
     *
     * @param algorithmName 算法名称
     * @param description   描述
     */
    Algorithm(String algorithmName, String description) {
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
