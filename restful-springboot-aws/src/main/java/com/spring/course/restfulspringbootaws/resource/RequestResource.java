package com.spring.course.restfulspringbootaws.resource;

import com.spring.course.restfulspringbootaws.domain.Request;
import com.spring.course.restfulspringbootaws.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("requests")
public class RequestResource {

    @Autowired
    private RequestService requestService;

    @PostMapping
    public ResponseEntity<Request> save(@RequestBody Request request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(requestService.save(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Request> update(@PathVariable("id") Long id, @RequestBody Request request) {
        request.setId(id);
        return ResponseEntity.ok(requestService.save(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Request> getById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(requestService.getById(id));
    }

    @GetMapping()
    public ResponseEntity<List<Request>> listAll() {
        return ResponseEntity.ok(requestService.listAll());
    }


}
