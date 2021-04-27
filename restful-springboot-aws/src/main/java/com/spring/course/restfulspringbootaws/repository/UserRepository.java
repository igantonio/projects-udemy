package com.spring.course.restfulspringbootaws.repository;

import com.spring.course.restfulspringbootaws.domain.User;
import com.spring.course.restfulspringbootaws.domain.enums.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmailAndPassword(String email, String password);

    @Transactional
    @Modifying
    @Query("UPDATE user SET role = ?2 WHERE id = ?1")
    int updateRole(Long id, Role role);

    Optional<User> findByEmail(String email);

}
