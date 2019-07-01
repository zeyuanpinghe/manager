package com.manager.woods.Utils;

import com.manager.woods.framework.utils.MD5Util;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MD5UtilTest {

    @Test
    public void getMd5Code(){
        String objStr = "apricode_woods";
        String objStrMd5 = MD5Util.getMD5Code(objStr);
        System.out.println(objStrMd5);
    }

}
