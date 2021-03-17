package cn.com.lezz.security;

import com.fastdfs.FastDFSClientUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.activation.MimetypesFileTypeMap;
import java.io.*;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test22 {

    public static void main(String[] args) throws Exception {
//        MimetypesFileTypeMap map = new MimetypesFileTypeMap("image/jpeg jpg");
        //map.getContentType();


        String[] s = {"a", "b", "c"};

        System.out.println(Arrays.toString(s));
        FastDFSClientUtils fastDFSClientUtils = FastDFSClientUtils.getInstance();
        String url = fastDFSClientUtils.upload(new File("D:/cache/a.jpg"));
        System.out.println(url);





    }
}
