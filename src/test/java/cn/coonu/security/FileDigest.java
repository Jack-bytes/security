package cn.coonu.security;

import cn.coonu.security.crypto.digest.DigestUtil;
import cn.coonu.security.crypto.algorithm.Algorithm;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public class FileDigest {

    @Test
    public void test() throws IOException, NoSuchAlgorithmException {
        File dir = new File("D:\\SSD_File\\Current_File");
        String otherPath = "E:\\新建文件夹 (2)\\备份\\SSD_File\\Current_File\\";//需要末尾斜杠

        File[] files = dir.listFiles();
        for(File file: files){
            if (file.isDirectory()) {
                continue;
            }
            FileInputStream fis = new FileInputStream(file);
            byte[] resource = new byte[fis.available()];
            fis.read(resource);

            FileInputStream in = new FileInputStream(otherPath + file.getName());
            byte[] resource2 = new byte[in.available()];
            in.read(resource2);
            fis.close();
            in.close();
            if (!DigestUtil.digest(resource, Algorithm.SHA_256).equals(DigestUtil.digest(resource2, Algorithm.SHA_256))) {
                System.out.println(file.getName());
            }

        }
    }

}
