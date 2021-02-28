package com.invillia.CursoSpringEssentialsDevDojo.adapter;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/*
* Classe teste para simular a criptografia da senha
*
* */

public class PasswordEncoder {

    public static void main(String[] args) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        System.out.println(passwordEncoder.encode("devdojo"));
    }

}

