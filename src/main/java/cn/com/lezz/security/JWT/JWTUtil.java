package cn.com.lezz.security.JWT;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.Map;

/**
 * @author Jack Wang
 */
@Getter
@Setter
public class JWTUtil {     //重新写单例模式,getInstance,然后把添加额外的data改写成一个一个加;

    /*--------------------------------------------
	|             C O N S T A N T S             |
	============================================*/

    private static JWTUtil util;

    /*--------------------------------------------
	|    I N S T A N C E   V A R I A B L E S    |
	============================================*/

    /**
     * token维持的时间;
     * 单位：秒
     */
    private int expireTime = 30 * 60;

    private String audience = "everyone";

    /**
     * 签发者
     */
    private String issuer = "system";

    private Algorithm algorithm;

    private String claimName = "data";

    /*--------------------------------------------
	|         C O N S T R U C T O R S           |
	============================================*/

    private JWTUtil(String secret) {
        if (secret == null || "".equals(secret.trim())) {
            throw new IllegalArgumentException("请设置算法秘钥!");
        }
        this.algorithm = Algorithm.HMAC256(secret);
    }

    /*--------------------------------------------
	|               M E T H O D S               |
	============================================*/

    public static JWTUtil getInstance(String secret) {
        if (util == null) {
            synchronized (JWTUtil.class) {
                if (util == null) {
                    util = new JWTUtil(secret);






                }
            }
        }
        return util;
    }

    public String createToken(Map<String, ?> data) {
        //发布时间
        long issuedAt = System.currentTimeMillis();
        //超时时间
        long expiresAt = issuedAt + this.expireTime * 1000;
        JWTCreator.Builder builder = JWT.create()
                .withAudience(this.audience)
                .withExpiresAt(new Date(expiresAt))
                .withIssuedAt(new Date(issuedAt))
                .withIssuer(this.issuer)
                .withClaim(this.claimName, data);

        return builder.sign(this.algorithm);//sign 的时候会自动识别并把typ 和 alg加进去;
    }

    /**
     * 校验token, 以下异常是按照抛出的先后顺序排列;
     *
     * @param token token
     * @return claim
     * @see com.auth0.jwt.exceptions.JWTDecodeException             token是base64编码,如果出现解析失败则会抛这个异常;
     * @see com.auth0.jwt.exceptions.AlgorithmMismatchException     生成token的算法和解析token算法不一致异常;
     * @see com.auth0.jwt.exceptions.SignatureVerificationException 签名校验失败,一般是秘钥错误
     * @see com.auth0.jwt.exceptions.TokenExpiredException          token过期;
     * @see com.auth0.jwt.exceptions.InvalidClaimException          从claim中取数据的类型和放进去的类型对应不上;
     */
    public Object verify(String token) {
        JWTVerifier verifier = JWT
                .require(this.algorithm)
                .withAudience(this.audience)
                .withIssuer(this.issuer)
                .build();
        DecodedJWT decodedJWT = verifier.verify(token);
        Claim claim = decodedJWT.getClaim(this.claimName);
        return claim.as(Object.class);//asMap();
    }

}
