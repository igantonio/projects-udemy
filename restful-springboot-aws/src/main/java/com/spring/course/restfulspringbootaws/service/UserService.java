package com.spring.course.restfulspringbootaws.service;

import com.spring.course.restfulspringbootaws.domain.User;
import com.spring.course.restfulspringbootaws.exception.NotFoundException;
import com.spring.course.restfulspringbootaws.model.PageModel;
import com.spring.course.restfulspringbootaws.model.PageRequestModel;
import com.spring.course.restfulspringbootaws.repository.UserRepository;
import com.spring.course.restfulspringbootaws.service.util.HashUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class UserService implements UserDetailsService {

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

    public PageModel<User> listAllOnLazyMode(PageRequestModel pageRequestModel) {
        Pageable pageable = PageRequest.of(pageRequestModel.getPage(), pageRequestModel.getSize());
        Page<User> page = userRepository.findAll(pageable);

        PageModel<User> pageModel = new PageModel<>((int)page.getTotalElements(), page.getSize(), page.getTotalPages(), page.getContent());

        return pageModel;

    }

    public User login(String email, String password) {
        password = HashUtil.getSecurityHash(password);
        return userRepository.findByEmailAndPassword(email, password).orElseThrow(() -> new NotFoundException("There are not found with e-mail = " + email));
    }

    public int updateRole(User user) {
        return userRepository.updateRole(user.getId(), user.getRole());
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("Doen't exist with email = " + username));

        List<GrantedAuthority> authorities =
                Arrays.asList(new SimpleGrantedAuthority("ROLE " + user.getRole().name()));

        org.springframework.security.core.userdetails.User userSpring = new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), authorities);
        return userSpring;
    }
}
