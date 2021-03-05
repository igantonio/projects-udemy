package com.spring.course.restfulspringbootaws.service.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class HashUtilTests {

    @Test
    public void getSecureHashTest() {
        String hash = HashUtil.getSecurityHash("123");
        System.out.println(hash);
        Assertions.assertEquals(64, hash.length());
    }

}
