package com.curso.udemy.pontoeletronicokotlin.services.Impl

import com.curso.udemy.pontoeletronicokotlin.documents.Lancamento
import com.curso.udemy.pontoeletronicokotlin.exception.NotFoundException
import com.curso.udemy.pontoeletronicokotlin.repositories.LancamentoRepository
import com.curso.udemy.pontoeletronicokotlin.services.LancamentoService
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service

@Service
class LancamentoServiceImpl(val lancamentoRepository: LancamentoRepository) : LancamentoService {

    override fun buscarPorFuncionarioId(funcionarioId: String, pageRequest: PageRequest): Page<Lancamento> =
            lancamentoRepository.findByFuncionarioId(funcionarioId, pageRequest)

    override fun buscarPorId(id: String): Lancamento =
            lancamentoRepository.findById(id).orElseThrow{ NotFoundException("Lancamento não encontrado com esse id: $id") }

    override fun persistir(lancamento: Lancamento): Lancamento = lancamentoRepository.save(lancamento)

    override fun remover(id: String) = lancamentoRepository.deleteById(id)
}