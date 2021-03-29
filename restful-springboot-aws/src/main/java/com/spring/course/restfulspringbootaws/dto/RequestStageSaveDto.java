package com.spring.course.restfulspringbootaws.dto;

import com.spring.course.restfulspringbootaws.domain.Request;
import com.spring.course.restfulspringbootaws.domain.RequestStage;
import com.spring.course.restfulspringbootaws.domain.User;
import com.spring.course.restfulspringbootaws.domain.enums.RequestState;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RequestStageSaveDto {

    private String description;

    @NotNull(message = "States required")
    private RequestState state;

    @NotNull(message = "Request required")
    private Request request;

    @NotNull(message = "Owner required")
    private User owner;

    public RequestStage transformToRequestStage() {
        RequestStage requestStage = new RequestStage(null, description, null, state, request, owner);
        return requestStage;
    }

}
