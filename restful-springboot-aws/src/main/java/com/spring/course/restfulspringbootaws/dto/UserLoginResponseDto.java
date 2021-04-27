package com.spring.course.restfulspringbootaws.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;

@Getter
@AllArgsConstructor
public class UserLoginResponseDto implements Serializable {


    private static final long serialVersionUID = 1L;

    private String token;
    private Long expireIn;
    private String tokenProvider;

}
