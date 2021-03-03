package com.spring.course.restfulspringbootaws.repository;

import com.spring.course.restfulspringbootaws.domain.RequestStage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RequestStageRepository extends JpaRepository<RequestStage, Long> {

    List<RequestStage> findAllByRequestId(Long id);

}
