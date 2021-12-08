package cn.coonu.security;

import cn.coonu.security.crypto.digest.DigestUtil;
import cn.coonu.security.crypto.Algorithm;
import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class DigestTest {

    @Test
    public void test() {

        byte[] bytes = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, -1, -2, 10, -20, -52, -128, -127,
                0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, -1, -2, 10, -20, -52, -128, -127};

        String wx1 = DigestUtil.toHexString2(bytes);
        String o1 = DigestUtil.toHexString(bytes);

        long time2 = System.nanoTime();
        String wx = DigestUtil.toHexString2(bytes);
        System.out.println(System.nanoTime() - time2);

        long time = System.nanoTime();
        String o = DigestUtil.toHexString(bytes);
        System.out.println(System.nanoTime() - time);

        System.out.println(o);
        System.out.println(wx);

    }

    @Test
    public void test1() throws NoSuchAlgorithmException {

        String s = "sfsdgfs 收工时费胜多负少的3543543564542";
        byte[] source = s.getBytes(StandardCharsets.UTF_8);
        System.out.println(DigestUtil.digest(source, Algorithm.SHA_384));
        //E1682E9153DCB2BF250804FFE502B45186E8C763
        //8FF5A0A145CDB730434E854B0736768BD75A8BFD118D0E8CC9962219FF9B9025
        //60FD7E566EFFC9EF44AA1638E7E0A1EBED4D8BF636B2761341B99D22F7A58575D07CB15AC7F206DA7DEAECC5DDDE9995223DB3A629157FAE90371BC40AAA83E6


    }

    @Test
    public void test2() throws NoSuchAlgorithmException {
        System.out.println(DigestUtil.generateBase64HmacKey(Algorithm.HMAC_SHA_512));
    }

    @Test
    public void test3() throws InvalidKeyException, NoSuchAlgorithmException {
        System.out.println(DigestUtil.hmac("sfsgh4rwgfsd".getBytes(StandardCharsets.UTF_8), "dfsgsdgsee".getBytes(), Algorithm.HMAC_MD5));
    }
}
