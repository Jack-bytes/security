package com.github.jackbytes.security;

import com.github.jackbytes.security.base64.Base64Util;
import org.junit.jupiter.api.Test;

import java.io.*;

public class Test11 {
    @Test
    public void test() throws IOException {
        File file = new File("D:/cache/finger.txt");
        FileReader fileReader = new FileReader(file);
        BufferedReader reader = new BufferedReader(fileReader);
        String s = reader.readLine();
        byte[] bytes = Base64Util.decode(s);
        FileOutputStream out = new FileOutputStream("D:/cache/finger1.bmp");
        out.write(bytes);
    }
}
