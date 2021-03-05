package com.spring.course.restfulspringbootaws.service.util;


import org.apache.commons.codec.digest.DigestUtils;

public class HashUtil {

    public static String getSecurityHash(String text) {
        return DigestUtils.sha256Hex(text);
    }

}
