package com.spring.course.restfulspringbootaws.dto;

import com.spring.course.restfulspringbootaws.domain.enums.Role;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UserUpdateRoleDto {

    private Role role;

}
