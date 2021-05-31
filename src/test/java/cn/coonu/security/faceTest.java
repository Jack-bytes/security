package cn.coonu.security;

import cn.coonu.security.base64.Base64Util;
import org.junit.jupiter.api.Test;

import java.io.*;

public class faceTest {

    @Test
    public void test() throws IOException {
        File file = new File("C:/Users/Jack/Desktop/新建文件夹 (2)/11.bmp");
        FileInputStream fis = new FileInputStream(file);
        byte[] bytes = new byte[fis.available()];
        fis.read(bytes);
        System.out.println(bytes.length);
        fis.close();
        String s = Base64Util.encode(bytes);
        Writer writer = new FileWriter("C:/Users/Jack/Desktop/新建文件夹 (2)/11.txt");
        writer.write(s);
        writer.close();
        //System.out.println(s);
    }
}
