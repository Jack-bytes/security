package cn.coonu.security;


import cn.coonu.security.base64.Base64Util;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class Test22 {

    @Test
    public void test() {
        String key = "YLVM6d9W9p8fy9DLz7nS5Mdl5Ki6j0GffbSa7upjnHn=";
        byte[] b = Base64Util.decodeUrl(key);
        System.out.println(Arrays.toString(b));
    }

    // ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/


    @Test
    public void test1() {


    }

}
