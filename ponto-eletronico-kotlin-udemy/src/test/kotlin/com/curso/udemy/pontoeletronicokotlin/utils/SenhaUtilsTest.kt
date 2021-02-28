package com.curso.udemy.pontoeletronicokotlin.utils

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

/**
 * Realizando teste unitario para a classe que gera uma senha criptografada
 */

class SenhaUtilsTest {

    private val SENHA = "1234";
    private val bCryptEncoder = BCryptPasswordEncoder()

    @Test
    fun testGerarHashSenha() {
        val hash = SenhaUtils().gerarBCrypt(SENHA)
        Assertions.assertTrue(bCryptEncoder.matches(SENHA, hash))
    }

}