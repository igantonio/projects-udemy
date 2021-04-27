package com.spring.course.restfulspringbootaws.security;

import com.spring.course.restfulspringbootaws.service.util.HashUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class CustomPasswordEncoder implements PasswordEncoder {


    @Override
    public String encode(CharSequence rawPassword) {
        return HashUtil.getSecurityHash(rawPassword.toString());
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return HashUtil.getSecurityHash(rawPassword.toString()).equals(encodedPassword);
    }
}
