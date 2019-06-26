package com.apricot.woods.Utils;

import com.apricot.woods.framework.utils.AESAppUtils;
import com.apricot.woods.framework.utils.AESForJs;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static junit.framework.TestCase.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AESAppUtilsTest {

    @Test
    public void aesEnAndDeCrypt() {
        String key = "apricot_woods_aes";
        String passwd = "DF2E0638180AF493E1E3C2A8F9A1DFFC64EE830CA4424A30244A836D611316B6";
        String enPasswd = AESAppUtils.aesEncrypt("123456RWEQRrly",key);
        String dePasswd = AESAppUtils.aesDecrypt(passwd,key);
//        assertEquals(passwd,dePasswd);
        System.out.println(enPasswd);
        System.out.println(dePasswd);
    }

    @Test
    public void aesEnAndDeCrypt2() throws Exception{
        String jsPass = AESForJs.encryptAES("123456RWEQRrly","apricot_woods");
//      AESAppUtils.aesEncrypt(password,aeskey);
        System.out.println(jsPass);
    }
}
