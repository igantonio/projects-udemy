package com.spring.course.restfulspringbootaws.dto;

import com.spring.course.restfulspringbootaws.domain.Request;
import com.spring.course.restfulspringbootaws.domain.RequestStage;
import com.spring.course.restfulspringbootaws.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class RequestSaveDto {

    @NotBlank(message = "Subject required")
    private String subject;

    private String description;

    @NotNull(message = "Owner required")
    private User owner;

    private List<RequestStage> stages = new ArrayList<>();

    public Request transformToRequest() {
        Request request = new Request(null, this.subject, this.description, null, null, this.owner, stages);
        return request;
    }
}
