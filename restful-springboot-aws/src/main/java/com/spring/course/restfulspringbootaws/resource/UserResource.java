package com.spring.course.restfulspringbootaws.resource;

import com.spring.course.restfulspringbootaws.domain.Request;
import com.spring.course.restfulspringbootaws.domain.User;
import com.spring.course.restfulspringbootaws.dto.UserLoginDto;
import com.spring.course.restfulspringbootaws.service.RequestService;
import com.spring.course.restfulspringbootaws.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("users")
public class UserResource {

    @Autowired
    private UserService userService;

    @Autowired
    private RequestService requestService;

    @PostMapping
    public ResponseEntity<User> save(@RequestBody User user) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(user));
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> update(@PathVariable("id") Long id, @RequestBody User user) {
        user.setId(id);
        return ResponseEntity.ok(userService.save(user));
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(userService.getById(id));
    }

    @GetMapping()
    public ResponseEntity<List<User>> listAll() {
        return ResponseEntity.ok(userService.listAll());
    }

    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody UserLoginDto userLoginDto) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userService.login(userLoginDto.getEmail(), userLoginDto.getPassword()));
    }

    @GetMapping("/{id}/requests")
    public ResponseEntity<List<Request>> listAllRequestsByOwnerId(@PathVariable("id") Long id) {
        return ResponseEntity.ok(requestService.listAllByOwnerId(id));
    }

}
