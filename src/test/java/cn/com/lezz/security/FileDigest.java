package cn.com.lezz.security;

import cn.com.lezz.security.crypto.digest.DigestUtil;
import cn.com.lezz.security.crypto.Algorithm;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public class FileDigest {

    @Test
    public void test() throws IOException, NoSuchAlgorithmException {
        File dir = new File("E:\\开发常用工具\\01 - 生产架构中软件工具\\redis");
        File[] files = dir.listFiles();
        for(File file: files){
            FileInputStream fis = new FileInputStream(file);
            byte[] resouse = new byte[fis.available()];
            fis.read(resouse);
            String s = DigestUtil.digest(resouse, Algorithm.SHA256);
            System.out.println(file.getName() + " :   " + s);


        }
    }

}
