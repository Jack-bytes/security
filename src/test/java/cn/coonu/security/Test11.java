package cn.coonu.security;

import cn.coonu.security.crypto.digest.DigestUtil;
import org.junit.jupiter.api.Test;

import java.io.*;

public class Test11 {
    @Test
    public void test() throws IOException {
        File file = new File("D:/cache/finger.txt");

    }

    @Test
    public void test2() throws IOException {
        File dic = new File("C:\\Users\\Jack\\Desktop\\Current_File");
        File[] files = dic.listFiles();
        for (File file : files) {

            if (file.isFile()) {
                FileInputStream in = new FileInputStream(file);
                byte[] source = new byte[in.available()];
                in.read(source);
                //获取同步下来的文件的md5;
                String message = DigestUtil.md5(source);

                FileInputStream inputStream = new FileInputStream("D:/SSD_File/Current_File/" + file.getName());
                byte[] localSource = new byte[inputStream.available()];
                inputStream.read(localSource);
                if (!DigestUtil.md5(source).equals(DigestUtil.md5(localSource))) {
                    System.out.println("比对失败: " + file.getName());
                }


            }
        }
    }
}
