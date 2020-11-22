package com.github.jackbytes.security.JWT;

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
public class JWTUtil {

    /**
     * token维持的时间;
     * 单位：秒
     */
    private int duration = 30 * 60;

    private String audience = "everyone";

    /**
     * 签发者
     */
    private String issuer = "system";

    private Algorithm algorithm;

    private String claimName = "data";

    public JWTUtil(String secret) {
        if (secret == null || "".equals(secret.trim())) {
            throw new IllegalArgumentException("请设置算法秘钥!");
        }
        this.algorithm = Algorithm.HMAC256(secret);
    }

    public String createToken(Map<String, ?> data) {
        //发布时间
        long issuedAt = System.currentTimeMillis();
        //超时时间
        long expiresAt = issuedAt + this.duration * 1000;
        JWTCreator.Builder builder = JWT.create()
                .withAudience(this.audience)
                .withExpiresAt(new Date(expiresAt))
                .withIssuedAt(new Date(issuedAt))
                .withIssuer(this.issuer)
                .withClaim(this.claimName, data);

        return builder.sign(this.algorithm);//sign 的时候会自动识别并把typ 和 alg加进去;
    }

    /**
     * 校验token
     *
     * @param token token
     * @return claim
     * @see com.auth0.jwt.exceptions.JWTDecodeException             token是base64编码,如果出现解析失败则会抛这个异常;
     * @see com.auth0.jwt.exceptions.AlgorithmMismatchException     生成token的算法和解析token算法不一致异常;
     * @see com.auth0.jwt.exceptions.SignatureVerificationException 签名校验失败,一般是秘钥错误
     * @see com.auth0.jwt.exceptions.TokenExpiredException          token过期;
     * @see com.auth0.jwt.exceptions.InvalidClaimException          从claim中取数据的类型和放进去的类型对应不上;
     */
    public Map<String, ?> verify(String token) {
        JWTVerifier verifier = JWT
                .require(this.algorithm)
                .withAudience(this.audience)
                .withIssuer(this.issuer)
                .build();
        DecodedJWT decodedJWT = verifier.verify(token);
        Claim claim = decodedJWT.getClaim(this.claimName);
        return claim.asMap();
    }

}
