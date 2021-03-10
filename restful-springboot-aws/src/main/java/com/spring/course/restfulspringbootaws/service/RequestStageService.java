package com.spring.course.restfulspringbootaws.service;

import com.spring.course.restfulspringbootaws.domain.RequestStage;
import com.spring.course.restfulspringbootaws.domain.enums.RequestState;
import com.spring.course.restfulspringbootaws.exception.NotFoundException;
import com.spring.course.restfulspringbootaws.repository.RequestRepository;
import com.spring.course.restfulspringbootaws.repository.RequestStageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class RequestStageService {

    @Autowired
    private RequestStageRepository requestStageRepository;

    @Autowired
    private RequestRepository requestRepository;


    public RequestStage save(RequestStage stage) {
        stage.setRealizationDate(new Date());

        RequestStage createdStage = requestStageRepository.save(stage);

        Long requestId = stage.getRequest().getId();
        RequestState state = stage.getState();

        requestRepository.updateStatus(requestId, state);
        return createdStage;
    }

    public RequestStage getById(Long id) {
        return requestStageRepository.findById(id).orElseThrow(() -> new NotFoundException("There are not found with id = " + id));
    }

    public List<RequestStage> listAllByRequestId(Long requestId) {
        return requestStageRepository.findAllByRequestId(requestId);
    }

}
