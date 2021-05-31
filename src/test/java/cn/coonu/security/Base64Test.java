package cn.coonu.security;

import cn.coonu.security.base64.Base64Util;
import org.junit.jupiter.api.Test;

public class Base64Test {

    @Test
    public void test(){
        byte[] source = "我爱你啊1 数字签名就是只有信息的发送者才能产生的别人无法伪造的一段数字串，这段数字串同时也是对信息的发送者发送信息真实性的一个有效证明".getBytes();
        System.out.println(Base64Util.encodeForUrl(source));
        System.out.println(Base64Util.encode(source));
    }
}
