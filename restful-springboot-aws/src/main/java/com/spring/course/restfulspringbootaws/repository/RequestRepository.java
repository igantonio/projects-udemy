package com.spring.course.restfulspringbootaws.repository;

import com.spring.course.restfulspringbootaws.domain.Request;
import com.spring.course.restfulspringbootaws.domain.RequestStage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RequestRepository extends JpaRepository<Request, Long> {

    List<Request> findAllByOwnerId(Long id);

    @Query(value = "UPDATE REQUEST SET STATE = :state WHERE id = :id", nativeQuery = true)
    Request updateStatus(Long id, RequestStage state);

}
