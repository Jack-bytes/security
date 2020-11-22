package com.github.jackbytes.security;

import com.github.jackbytes.security.base64.Base64Util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class faceTest {

    public void test() throws IOException {
        File file = new File("D:/cache/11.jpg");
        FileInputStream fis = new FileInputStream(file);
        byte[] bytes = new byte[(int) file.length()];
        fis.read(bytes);
        fis.close();
        String s = Base64Util.encode(bytes);
        System.out.println(s);
    }
}
