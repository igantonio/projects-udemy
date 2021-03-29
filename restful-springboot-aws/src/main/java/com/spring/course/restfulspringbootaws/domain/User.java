package com.spring.course.restfulspringbootaws.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.spring.course.restfulspringbootaws.domain.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "user")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 75, nullable = false)
    private String name;

    @Column(length = 75, nullable = false, unique = true)
    private String email;

    @Getter(onMethod = @__({@JsonIgnore}))
    @Setter(onMethod = @__({@JsonProperty}))
    @Column(length = 100, nullable = false)
    private String password;

    @Column(length = 20, nullable = false, updatable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

    @Getter(onMethod = @__({@JsonIgnore}))
    @OneToMany(mappedBy = "owner")
    private List<Request> requests = new ArrayList<>();

    @Getter(onMethod = @__({@JsonIgnore}))
    @OneToMany(mappedBy = "owner")
    private List<RequestStage> stages = new ArrayList<>();

}
