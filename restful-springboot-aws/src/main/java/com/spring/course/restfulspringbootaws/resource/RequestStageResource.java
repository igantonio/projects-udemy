package com.spring.course.restfulspringbootaws.resource;

import com.spring.course.restfulspringbootaws.domain.RequestStage;
import com.spring.course.restfulspringbootaws.dto.RequestStageSaveDto;
import com.spring.course.restfulspringbootaws.service.RequestStageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("request-stages")
public class RequestStageResource {

    @Autowired
    private RequestStageService requestStageService;

    @PostMapping
    public ResponseEntity<RequestStage> save(@RequestBody @Valid RequestStageSaveDto requestStageSaveDto) {
        RequestStage requestStage = requestStageSaveDto.transformToRequestStage();
        return ResponseEntity.status(HttpStatus.CREATED).body(requestStageService.save(requestStage));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RequestStage> getById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(requestStageService.getById(id));
    }

}
