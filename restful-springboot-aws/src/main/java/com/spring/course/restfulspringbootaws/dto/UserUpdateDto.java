package com.spring.course.restfulspringbootaws.dto;

import com.spring.course.restfulspringbootaws.domain.Request;
import com.spring.course.restfulspringbootaws.domain.RequestStage;
import com.spring.course.restfulspringbootaws.domain.User;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class UserUpdateDto {

    @NotBlank(message = "Name required")
    private String name;

    @Email(message = "Invalid email address")
    private String email;

    @Size(min = 7, max = 99, message = "Password must be between 7 and 99")
    private String password;

    private List<Request> requests = new ArrayList<>();
    private List<RequestStage> stages = new ArrayList<>();

    public User tranformToUser() {
        User user = new User(null, this.name, this.email, this.password, null, this.requests, this.stages);
        return user;
    }

}
