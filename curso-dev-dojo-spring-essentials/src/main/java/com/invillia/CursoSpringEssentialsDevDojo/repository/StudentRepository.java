package com.invillia.CursoSpringEssentialsDevDojo.repository;

import com.invillia.CursoSpringEssentialsDevDojo.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    List<Student>findByNameIgnoreCaseContaining(String name);
    boolean existsByEmail(String email);

}
