package com.spring.course.restfulspringbootaws.repository;


import com.spring.course.restfulspringbootaws.domain.User;
import com.spring.course.restfulspringbootaws.domain.enums.Role;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserRepositoryTests {

    @Autowired
    private UserRepository userRepository;

    @BeforeAll
    public void setup() {
        saveTest();
    }

    public void saveTest() {
        User user = new User(null, "Igor", "Igor@gmail.com", "123", Role.ADMINISTRATOR, null, null);

        User createdUser = userRepository.save(user);

        Assertions.assertEquals(1L, createdUser.getId());
    }

    @Test
    public void updateTest() {
        User user = new User(1L, "Igor Gabriel", "Igor@gmail.com", "123", Role.ADMINISTRATOR, null, null);

        User updatedUser = userRepository.save(user);

        Assertions.assertEquals("Igor Gabriel", updatedUser.getName());
    }

    @Test
    public void getByIdTest() {
        User user =  userRepository.findById(1L).get();

        Assertions.assertEquals("123", user.getPassword());
    }

    @Test
    public void listTest() {
        List<User> users = userRepository.findAll();

        Assertions.assertEquals(1, users.size());
    }

    @Test
    public void loginTest() {
        User user = userRepository.findByEmailAndPassword("Igor@gmail.com", "123").get();

        Assertions.assertEquals(1L, user.getId());
    }

}
