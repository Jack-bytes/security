package cn.coonu.security;


import cn.coonu.security.base64.Base64Util;
import cn.coonu.security.crypto.AES.AESRunner;
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

        String encryptMsg = "fIImd30W5VWJBQoklfM6nJ63YyeEybOOpXEHIbMHSsER1x2d5WjmraTFxiS8To0ud4aZ8Bk0twhVvUZiFNavyRKqMhBCqNwHfec4ay6hbP/Q9hJbFf1Fk63bQU/xBLxc9DICQQqT6q7whuK/Auc7p4oDHVVd8fYUAJgumQjDhPsXylifRr92pTW2KkcnrDO4EIm56miK6M3PIsohhIztuH/OO4D3rLoOIkxYMwYpavdlfa2s9dFF3nqzTeg3UZVdNCoFhfOL+3Av/xJUeHP50g6SzX7wT2LSMsGsXTIFPFLS0ZB8NqIXZVQTtEK2NLHrWtJbXjIk5IyoV8wXElJhSXJzbQ08XYEKDBaZNZmWyTkO+N66YVRSbKtCgmAATTsfuhxxS1XUX1qT02rSKXhkeSa5+xsi/C6HuVX7XAIlrb1C7JIFoxPTr7SHmnXjTkNN2jyyZe+/JbQqXB+TTrUY0XOGxp12vO2eIvhhILpa02kjLzwOvwSNyN6jfMeK2SrRIMs6MIPA4o2d6MUKjroSW7tUzgdz5jPDh7DbNcFWXjbQlkj/fCoCHA278cm3WYXSSwwQ/GFtjhIYDqJSeg6e6WViQb4mtvNmbM3QDqdqu3SswoXU7f8z09tdFD9JnTeHhTj3evhjitEACEzaOlQUsYjXaip6YLiGoqfJo5AB0As=";
        byte[] aesKey = Base64Util.decode("UECktyCFnY1AGiQZUzOd7O62xSP845JHsp9pgVzWlnp");
        byte[] iv = Arrays.copyOfRange(aesKey, 0, 16);
        AESRunner runner = new AESRunner(aesKey, iv);
        byte[] bytes = runner.decrypt(Base64Util.decode(encryptMsg));



    }

}
