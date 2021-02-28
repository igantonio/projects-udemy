package com.invillia.CursoSpringEssentialsDevDojo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Student extends AbstractEntity{

    @Column(nullable = false)
    @NotEmpty(message = "O campo nome do estudante é obrigatório !")
    private String name;

    @Column(nullable = false)
    @NotEmpty(message = "O campo e-mail é obrigatório !")
    @Email
    private String email;



//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
}
