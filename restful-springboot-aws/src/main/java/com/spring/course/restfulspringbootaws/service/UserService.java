package com.spring.course.restfulspringbootaws.service;

import com.spring.course.restfulspringbootaws.domain.User;
import com.spring.course.restfulspringbootaws.exception.NotFoundException;
import com.spring.course.restfulspringbootaws.repository.UserRepository;
import com.spring.course.restfulspringbootaws.service.util.HashUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User save(User user) {
        user.setPassword(HashUtil.getSecurityHash(user.getPassword()));
        return userRepository.save(user);
    }

    public User update(User user) {
        user.setPassword(HashUtil.getSecurityHash(user.getPassword()));
        return userRepository.save(user);
    }

    public User getById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new NotFoundException("There are not found with id = " + id));
    }

    public List<User> listAll() {
        return userRepository.findAll();
    }

    public User login(String email, String password) {
        password = HashUtil.getSecurityHash(password);
        return userRepository.findByEmailAndPassword(email, password).orElseThrow(() -> new NotFoundException("There are not found with e-mail = " + email));
    }

}
