package com.curso.udemy.pontoeletronicokotlin.services.Impl

import com.curso.udemy.pontoeletronicokotlin.documents.Funcionario
import com.curso.udemy.pontoeletronicokotlin.exception.NotFoundException
import com.curso.udemy.pontoeletronicokotlin.repositories.FuncionarioRepository
import com.curso.udemy.pontoeletronicokotlin.services.FuncionarioService
import org.springframework.stereotype.Service

@Service
class FuncionarioServiceImpl(val funcionarioRepository: FuncionarioRepository) : FuncionarioService {

    override fun persistir(funcionario: Funcionario): Funcionario = funcionarioRepository.save(funcionario)

    override fun buscarPorCpf(cpf: String): Funcionario = funcionarioRepository.findByCpf(cpf)
            .orElseThrow{ NotFoundException("Funcionario não encontrado com esse cpf: $cpf") }

    override fun buscarPorEmail(email: String): Funcionario = funcionarioRepository.findByEmail(email)
            .orElseThrow{ NotFoundException("Funcionario não encontrado com esse email: $email") }

    override fun buscarPorId(id: String): Funcionario = funcionarioRepository.findById(id)
            .orElseThrow{ NotFoundException("Funcionario não encontrado com esse id: $id") }

    override fun funcionarioExistePorCpf(cpf: String): Boolean = funcionarioRepository.existsByCpf(cpf)

    override fun funcionarioExistePorEmail(email: String): Boolean = funcionarioRepository.existsByEmail(email)
}