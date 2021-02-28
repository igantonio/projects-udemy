package com.myfinances.controller;

import com.myfinances.dto.UserDTO;
import com.myfinances.entity.User;
import com.myfinances.exception.AuthenticationException;
import com.myfinances.exception.BusinessRuleException;
import com.myfinances.service.Impl.ExpenseServiceImpl;
import com.myfinances.service.Impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Optional;

@RestController
@RequestMapping("api/users")
public class UserController {

    private final UserServiceImpl userServiceImpl;
    private final ExpenseServiceImpl expenseServiceImpl;

    @Autowired
    public UserController(UserServiceImpl userServiceImpl, ExpenseServiceImpl expenseServiceImpl) {
        this.userServiceImpl = userServiceImpl;
        this.expenseServiceImpl = expenseServiceImpl;
    }


    @PostMapping("/authentication")
    public ResponseEntity authentication(@RequestBody UserDTO userDTO) {
        try {
            final User authenticateUser = userServiceImpl.authenticate(userDTO.getEmail(), userDTO.getPassword());
            return ResponseEntity.ok(authenticateUser);
        } catch (AuthenticationException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    @PostMapping
    public ResponseEntity save(@RequestBody UserDTO userDTO) {

        User user = User.builder()
                .name(userDTO.getName())
                .email(userDTO.getEmail())
                .password(userDTO.getPassword()).build();

        try {
            User saveUser = userServiceImpl.saveUser(user);
            return new ResponseEntity(saveUser, HttpStatus.CREATED);
        } catch (BusinessRuleException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("{id}/balance")
    public ResponseEntity getBalanceByUser(@PathVariable("id") Long id) {

        Optional<User> userOptional = userServiceImpl.findById(id);
        if (userOptional.isEmpty()) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        BigDecimal balance = expenseServiceImpl.getBalanceByUser(id);
        return ResponseEntity.ok(balance);
    }

}
