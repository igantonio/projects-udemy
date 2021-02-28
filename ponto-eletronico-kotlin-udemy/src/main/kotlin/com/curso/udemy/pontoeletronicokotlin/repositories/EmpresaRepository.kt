package com.curso.udemy.pontoeletronicokotlin.repositories

import com.curso.udemy.pontoeletronicokotlin.documents.Empresa
import org.springframework.data.mongodb.repository.MongoRepository
import java.util.*

/**
 *  Para extender uma classe deve utilizar o ":"
 */

interface EmpresaRepository : MongoRepository<Empresa, String>{

    fun findByCnpj(cnpj: String): Optional<Empresa>
    fun existsByCnpj(cnpj: String): Boolean

}