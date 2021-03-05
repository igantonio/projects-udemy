package com.spring.course.restfulspringbootaws.service;

import com.spring.course.restfulspringbootaws.domain.Request;
import com.spring.course.restfulspringbootaws.domain.enums.RequestState;
import com.spring.course.restfulspringbootaws.repository.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class RequestService {

    @Autowired
    private RequestRepository requestRepository;

    public Request save(Request request) {
        request.setState(RequestState.OPEN);
        request.setCreationDate(new Date());
        return requestRepository.save(request);
    }

    public Request update(Request request) {
        return requestRepository.save(request);
    }

    public Request getById(Long id) {
        return requestRepository.findById(id).get();
    }

    public List<Request> listAll() {
        return requestRepository.findAll();
    }

    public List<Request> listAllByOwnerId(Long ownerId) {
        return requestRepository.findAllByOwnerId(ownerId);
    }

}
