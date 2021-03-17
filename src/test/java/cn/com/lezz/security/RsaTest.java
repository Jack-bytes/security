package cn.com.lezz.security;

import cn.com.lezz.security.crypto.RSA.RSAGenerator;
import cn.com.lezz.security.crypto.RSA.RSAKeyLoader;
import cn.com.lezz.security.crypto.RSA.RSAUtil;
import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;
import java.security.interfaces.RSAPublicKey;

public class RsaTest {

    @Test
    public void test(){

        RSAGenerator.generateKey("D:/cache/key", 1588);
    }


    @Test
    public void test2() throws Exception {

        //2048
//        String publicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAlF+wuG5rYghZlzDrl/ewHthAUfnS6K8nxLNAH1JgxwiptPH2RzEb4Sj0PZq7V9iHte85+eFmAqeyVtIgUn2PqcIuIxNlWLnuzYBEd33kfE3NSGQm6J66gjweEaXz9PoZyAeHEH6DoTXOj/visZwXWGRd1A35BRKcG51hCtuy3PGbDMssp0Axi12o/6CmyOw/qUcXDiUtsHmSt4I9nfDvg+F1SVIm+0KveK18E+0oxocWR2Cx/NQ3c/rbSHnZ9bUDRuWi0Fzc4Bu7TzpqMyRocIt6JJQ7AiwhCJWJXJEwwq7Ae9meAfDfHdy6dCiLs21/TNUy/PwzGJ93y+WvhskzkQIDAQAB";

        //1024
        //String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCixWaAIEEoUFRTETOBmS7klTKRQ86PLd4zogUCRgndIecUEhnYmYDE25pEuhgOeM1jodTiBFJeJbRMOijzA6yylomb+Zv7DcqR0Zcu42PukMeieKDzWTjSvh5OEZAbQOeP6JVeKOucZLxOjegHHD8LBcV+aCiuXJ4abWlv2SQjNwIDAQAB";
        //1588
        String publicKey = "MIHlMA0GCSqGSIb3DQEBAQUAA4HTADCBzwKBxwgHBRRmCr7A4GO1FY3sRGIcpzWWfx/U1Nz7avTMUbQ2N5VBLYAP1p/fkylMp3EbHQUOGZl/9DGWn4PAtx3h+tCgpIPu2ToXWhfuNLvZ3FpPhK82FXUy4BgnA2RkuF7Pro1Og3dtCIqt+1Rce+CXtnRXnitiM3rrB1kyMotaMjDrcEiuax/haznv10KVkti2LEoEdTHhYvavG85u+y4fbuxXCVDv9euMKiQmQ0uEK4ImZbWZ+xmOmsLEkabBOTJBPYqqF16OdwMCAwEAAQ==";
        RSAPublicKey key = RSAKeyLoader.loadPublicKey(publicKey);
        byte[] plaintext = "fsgssssdfsffsfssdfssffsfssgssfsdfssfsgssfsdfssfsgssfsdfssfsgssfsdfssffsfsgssfsdfssfsgssfsdfssfsgssfsdfssfsgssfsdfssffsfsgssfsdfssfsgssfsdfssfsgssfsdfssfsgssfsdfssffsgssfsdfssfsgssfsdfssdfs".getBytes(StandardCharsets.UTF_8);
        System.out.println("plaintext: " + plaintext.length);

        RSAUtil.encrypt(key, plaintext);

    }

    @Test
    public void test22(){
        System.out.println(1595 >> 3);
    }
}
