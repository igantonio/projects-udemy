package com.curso.udemy.pontoeletronicokotlin.utils

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

/**
 * Quando possuimos um metodo que terá apenas uma linha, podemos fazer essa implementação inline sem precisar do return
 *
 * No Kotlin não utilizamos o new para instanciar uma classe. Apenas abrimos e fechamos parenteses
 */

class SenhaUtils {

    fun gerarBCrypt(senha: String): String = BCryptPasswordEncoder().encode(senha)

}