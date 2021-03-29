package com.spring.course.restfulspringbootaws.dto;

import com.spring.course.restfulspringbootaws.domain.enums.Role;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter @Setter
public class UserUpdateRoleDto {

    @NotNull(message = "Role required")
    private Role role;

}
