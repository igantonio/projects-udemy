package com.spring.course.restfulspringbootaws.repository;

import com.spring.course.restfulspringbootaws.AbstractApplicationTest;
import com.spring.course.restfulspringbootaws.domain.Request;
import com.spring.course.restfulspringbootaws.domain.RequestStage;
import com.spring.course.restfulspringbootaws.domain.User;
import com.spring.course.restfulspringbootaws.domain.enums.RequestState;
import com.spring.course.restfulspringbootaws.domain.enums.Role;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

public class RequestStageRepositoryTests extends AbstractApplicationTest {

    @Autowired
    private RequestStageRepository requestStageRepository;

    @Autowired
    private RequestRepository requestRepository;

    @Autowired
    private UserRepository userRepository;


    @BeforeAll
    public void setup() {
        saveUser();
        saveRequest();
    }

    @Test
    public void saveTest() {
        User owner = new User();
        owner.setId(1L);

        Request request = new Request();
        request.setId(1L);

        RequestStage stage = new RequestStage(null, "Foi comprado um novo laptop de marca HD e com 16 GB de RAM", new Date(), RequestState.CLOSED, request, owner);

        RequestStage createdStage = requestStageRepository.save(stage);

        Assertions.assertEquals(1L, createdStage.getId());
    }

    @Test
    public void getByIdTests() {
        RequestStage requestStage = requestStageRepository.findById(1L).get();

        Assertions.assertEquals("Foi comprado um novo laptop de marca HD e com 16 GB de RAM", requestStage.getDescription());
    }

    @Test
    public void listByRequestIdTest() {
        List<RequestStage> stages = requestStageRepository.findAllByRequestId(1L);

        Assertions.assertEquals(1, stages.size());
    }

    private void saveUser() {
        User user = new User(null, "Igor", "Igor@gmail.com", "123", Role.ADMINISTRATOR, null, null);

        userRepository.save(user);
    }

    private void saveRequest() {
        User owner = new User();
        owner.setId(1L);
        Request request = new Request(null, "Novo Leptop HP", "Pretendo obter um laptop HP", new Date(), RequestState.OPEN, owner, null);

        requestRepository.save(request);
    }

}
