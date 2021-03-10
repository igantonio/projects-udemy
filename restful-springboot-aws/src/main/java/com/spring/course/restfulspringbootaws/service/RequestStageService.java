package com.spring.course.restfulspringbootaws.service;

import com.spring.course.restfulspringbootaws.domain.Request;
import com.spring.course.restfulspringbootaws.domain.RequestStage;
import com.spring.course.restfulspringbootaws.domain.enums.RequestState;
import com.spring.course.restfulspringbootaws.exception.NotFoundException;
import com.spring.course.restfulspringbootaws.model.PageModel;
import com.spring.course.restfulspringbootaws.model.PageRequestModel;
import com.spring.course.restfulspringbootaws.repository.RequestRepository;
import com.spring.course.restfulspringbootaws.repository.RequestStageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

    public PageModel<RequestStage> listAllByRequestIdOnLazyMode(Long requestId, PageRequestModel pageRequestModel) {
        Pageable pageable = PageRequest.of(pageRequestModel.getPage(), pageRequestModel.getSize());
        Page<RequestStage> page = requestStageRepository.findAllByRequestId(requestId, pageable);

        PageModel<RequestStage> pageModel = new PageModel<>((int)page.getTotalElements(), pageRequestModel.getSize(), page.getTotalPages(), page.getContent());

        return pageModel;
    }

    public List<RequestStage> listAllByRequestId(Long requestId) {
        return requestStageRepository.findAllByRequestId(requestId);
    }

}
