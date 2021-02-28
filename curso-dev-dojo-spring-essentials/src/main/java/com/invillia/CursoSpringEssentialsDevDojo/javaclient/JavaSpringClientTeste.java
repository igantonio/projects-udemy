package com.invillia.CursoSpringEssentialsDevDojo.javaclient;

import com.invillia.CursoSpringEssentialsDevDojo.domain.Student;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

public class JavaSpringClientTeste {

    public static void main(String[] args) {
        RestTemplate restTemplate = new RestTemplateBuilder()
                .rootUri("http://localhost:8080/v1/protected/students")
                .basicAuthentication("igor", "devdojo")
                .build();

        /*
        * Abaixo 3 formas de retornar os dados d banco de dados.
        * Utilizando:
        * getForObject();
        * getForEntity();
        * exchange();
        * */

        Student studentByObject = restTemplate.getForObject("/{id}", Student.class, 4);
        //        restTemplate.getForObject("/4", Student.class);
        ResponseEntity<Student> studentByEntity = restTemplate.getForEntity("/{id}", Student.class, 4);

        System.out.println(studentByObject);
        System.out.println(studentByEntity.getBody());

        //pegando todos os dados do banco de dados e setando num vetor
        Student[] listStudentsObject = restTemplate.getForObject("/", Student[].class);
        System.out.println(Arrays.toString(listStudentsObject));

        ResponseEntity<List<Student>> exchange = restTemplate.exchange(
                "/",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Student>>() {
                });

        System.out.println(exchange.getBody());

    }

}
