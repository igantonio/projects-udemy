package com.curso.udemy.pontoeletronicokotlin.repositories

import com.curso.udemy.pontoeletronicokotlin.documents.Lancamento
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.mongodb.repository.MongoRepository

/**
 *  Para extender uma classe deve utilizar o ":"
 *  Criando metodo para retornar uma lista de lancamento pelo id do funcionario paginada
 */

interface LancamentoRepository : MongoRepository<Lancamento, String> {

    fun findByFuncionarioId(funcionarioId: String, pageable: Pageable): Page<Lancamento>

}