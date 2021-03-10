package com.spring.course.restfulspringbootaws.repository;

import com.spring.course.restfulspringbootaws.AbstractApplicationTest;
import com.spring.course.restfulspringbootaws.domain.Request;
import com.spring.course.restfulspringbootaws.domain.User;
import com.spring.course.restfulspringbootaws.domain.enums.RequestState;
import com.spring.course.restfulspringbootaws.domain.enums.Role;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

public class RequestRepositoryTests extends AbstractApplicationTest {

    @Autowired
    private RequestRepository requestRepository;

    @Autowired
    private UserRepository userRepository;

    @BeforeAll
    public void setup() {
        saveUser();
        saveTest();
    }

    public void saveTest() {
        User owner = new User();
        owner.setId(1L);
        Request request = new Request(null, "Novo Leptop HP", "Pretendo obter um laptop HP", new Date(), RequestState.OPEN, owner, null);

        Request createdRequest = requestRepository.save(request);

        Assertions.assertEquals(1L, createdRequest.getId());

    }

    @Test
    public void updateTest() {
        User owner = new User();
        owner.setId(1L);

        Request request = new Request(1L, "Novo Leptop HP", "Pretendo obter um laptop HP, de RAM 16 GB", null, RequestState.OPEN, owner, null);

        Request updateRequest = requestRepository.save(request);

        Assertions.assertEquals("Pretendo obter um laptop HP, de RAM 16 GB", updateRequest.getDescription());
    }

    @Test
    public void getByIdTest() {
        Request request = requestRepository.findById(1L).get();

        Assertions.assertEquals("Novo Leptop HP", request.getSubject());
    }

    @Test
    public void listByOwnerId() {
        List<Request> requests = requestRepository.findAllByOwnerId(1L);

        Assertions.assertEquals(1, requests.size());
    }

    @Test
    public void listTest() {
        List<Request> requests = requestRepository.findAll();

        Assertions.assertEquals(1, requests.size());
    }

    @Test
    public void updatedStatusTest() {
        int affectedRows = requestRepository.updateStatus(1L, RequestState.IN_PROGRESS);

        Assertions.assertEquals(1, affectedRows);
    }

    private void saveUser() {
        User user = new User(null, "Igor", "Igor@gmail.com", "123", Role.ADMINISTRATOR, null, null);

        User createdUser = userRepository.save(user);

        Assertions.assertEquals(1L, createdUser.getId());
    }

}
