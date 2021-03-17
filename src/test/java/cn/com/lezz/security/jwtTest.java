package cn.com.lezz.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class jwtTest {

    @Test
    public void test() {

        Map map = new HashMap();
        map.put("aa", "dd");

//        String token = new JWTUtil("sflsakjhflsjldjf").createToken(map);
//        System.out.println(token);
    }

    @Test
    public void test1(){

        String token = JWT.create()
                .withClaim("loginName", "admin")
                .withClaim("id", "4028938170af22c90170af23262a0041")
                .withExpiresAt(new Date())
                .sign(Algorithm.HMAC256("123456"));
        System.out.println(token);
    }




}
