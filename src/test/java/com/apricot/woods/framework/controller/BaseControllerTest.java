package com.apricot.woods.framework.controller;

import com.apricot.woods.framework.utils.AESAppUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BaseControllerTest {

    @Value("${apricot.aeskey}")
    private String aeskey;

    @Test
    public void verityApiKeyTest() {
        System.out.println(buildApiKey());

    }
    public String buildApiKey(){
        System.out.println(aeskey);
        return AESAppUtils.aesEncrypt("/login/verity",aeskey);
    }

}