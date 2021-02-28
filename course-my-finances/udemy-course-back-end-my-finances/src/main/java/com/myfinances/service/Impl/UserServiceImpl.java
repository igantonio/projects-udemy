package com.myfinances.service.Impl;

import com.myfinances.entity.User;
import com.myfinances.exception.AuthenticationException;
import com.myfinances.exception.BusinessRuleException;
import com.myfinances.repository.UserRepository;
import com.myfinances.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Transient;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public User authenticate(String email, String password) {

        Optional<User> userOptional = userRepository.findByEmail(email);

        if (userOptional.isEmpty()) {
            throw new AuthenticationException("Usuário não encontrado para o email informado!");
        }

        if (!userOptional.get().getPassword().equals(password)) {
            throw new AuthenticationException("Senha inválida!");
        }

        return userOptional.get();
    }


    @Override
    @Transient
    public User saveUser(User user) {
        validateEmail(user.getEmail());
        return userRepository.save(user);
    }


    public void validateEmail(String email) {
        if (userRepository.existsByEmail(email)) {
            throw new BusinessRuleException("Já existe um usuário cadastro com este email!");
        }
    }


    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }
}
