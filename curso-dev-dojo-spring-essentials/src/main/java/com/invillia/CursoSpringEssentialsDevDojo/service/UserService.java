package com.invillia.CursoSpringEssentialsDevDojo.service;

import com.invillia.CursoSpringEssentialsDevDojo.domain.User;
import com.invillia.CursoSpringEssentialsDevDojo.exception.ResourceNotFoundException;
import com.invillia.CursoSpringEssentialsDevDojo.exception.UserAlreadyRegistredException;
import com.invillia.CursoSpringEssentialsDevDojo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public long save(User user){
        if(!userRepository.existsByUsername(user.getUsername())){
            return userRepository.save(user).getId();
        }
        throw new UserAlreadyRegistredException("Student already registred with this username !!!");
    }

    @Transactional(readOnly = true)
    public User findByUsername(String username){
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found by username ! USERNAME: " + username));
    }

}
