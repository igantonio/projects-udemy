package com.spring.course.restfulspringbootaws;

import com.spring.course.restfulspringbootaws.domain.Request;
import com.spring.course.restfulspringbootaws.domain.RequestStage;
import com.spring.course.restfulspringbootaws.domain.User;
import com.spring.course.restfulspringbootaws.domain.enums.RequestState;
import com.spring.course.restfulspringbootaws.domain.enums.Role;
import com.spring.course.restfulspringbootaws.service.RequestService;
import com.spring.course.restfulspringbootaws.service.RequestStageService;
import com.spring.course.restfulspringbootaws.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;

@SpringBootApplication
public class RestfulSpringbootAwsApplication implements CommandLineRunner {

	@Autowired
	private UserService userService;

	@Autowired
	private RequestService requestService;

	@Autowired
	private RequestStageService requestStageService;

	public static void main(String[] args) {
		SpringApplication.run(RestfulSpringbootAwsApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {


		/*
		User user = new User(null, "Igor", "Igor@gmail.com", "123", Role.ADMINISTRATOR, null, null);
		userService.save(user);
		Request request = requestService.save(new Request(null, "Novo Leptop HP", "Pretendo obter um laptop HP", new Date(), RequestState.OPEN, user, null));
		RequestStage requestStage = requestStageService.save(new RequestStage(null, "Foi comprado um novo laptop de marca HD e com 16 GB de RAM", new Date(), RequestState.CLOSED, request, user));

		for (int i = 0; i < 15 ; i++) {
			user.setId(null);
			user.setEmail(i+user.getEmail());
			userService.save(user);
		}*/
	}
}
