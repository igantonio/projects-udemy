package com.curso.udemy.pontoeletronicokotlin.repositories

import com.curso.udemy.pontoeletronicokotlin.documents.Funcionario
import org.springframework.data.mongodb.repository.MongoRepository
import java.util.*

/**
 *  Para extender uma classe deve utilizar o ":"
 */

interface FuncionarioRepository : MongoRepository<Funcionario, String> {

    fun findByEmail(email: String): Optional<Funcionario>
    fun findByCpf(cpf: String): Optional<Funcionario>
    fun existsByCpf(cpf: String): Boolean
    fun existsByEmail(email: String): Boolean

}