package com.manager.woods.framework.utils;

import org.hibernate.validator.constraints.br.TituloEleitoral;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AESAppUtilsTest {

    @Value("${manager.aeskey}")
    private String aeskey;

    @Test
    public void checkDeAes(){
        System.out.println(AESAppUtils.aesDecrypt("A80CE27311FD5CF966A36B650C4A60D5",aeskey));
    }

    @Test
    public void checkEnAes(){
        System.out.println(AESAppUtils.aesEncrypt("123QWER",aeskey));
    }
}