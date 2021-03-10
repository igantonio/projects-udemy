package com.spring.course.restfulspringbootaws.service;

import com.spring.course.restfulspringbootaws.domain.Request;
import com.spring.course.restfulspringbootaws.domain.User;
import com.spring.course.restfulspringbootaws.domain.enums.RequestState;
import com.spring.course.restfulspringbootaws.exception.NotFoundException;
import com.spring.course.restfulspringbootaws.model.PageModel;
import com.spring.course.restfulspringbootaws.model.PageRequestModel;
import com.spring.course.restfulspringbootaws.repository.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
        return requestRepository.findById(id).orElseThrow(() -> new NotFoundException("There are not found with id = " + id));
    }

    public List<Request> listAll() {
        return requestRepository.findAll();
    }

    public PageModel<Request> listAllOnLazyMode(PageRequestModel pageRequestModel) {
        Pageable pageable = PageRequest.of(pageRequestModel.getPage(), pageRequestModel.getSize());
        Page<Request> page = requestRepository.findAll(pageable);

        PageModel<Request> pageModel = new PageModel<>((int)page.getTotalElements(), page.getSize(), page.getTotalPages(), page.getContent());

        return pageModel;

    }

    public List<Request> listAllByOwnerId(Long ownerId) {
        return requestRepository.findAllByOwnerId(ownerId);
    }

    public PageModel<Request> listAllByOwnerIdOnLazyMode(Long ownerId, PageRequestModel pageRequestModel) {
        Pageable pageable = PageRequest.of(pageRequestModel.getPage(), pageRequestModel.getSize());
        Page<Request> page = requestRepository.findAllByOwnerId(ownerId,pageable);

        PageModel<Request> pageModel = new PageModel<>((int)page.getTotalElements(), pageRequestModel.getSize(), page.getTotalPages(), page.getContent());

        return pageModel;
    }


}
