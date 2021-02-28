package com.curso.udemy.pontoeletronicokotlin.services

import com.curso.udemy.pontoeletronicokotlin.documents.Funcionario

interface FuncionarioService {

    fun persistir(funcionario: Funcionario): Funcionario
    fun buscarPorCpf(cpf: String): Funcionario
    fun buscarPorEmail(email: String): Funcionario
    fun buscarPorId(id: String): Funcionario
    fun funcionarioExistePorCpf(cpf: String): Boolean
    fun funcionarioExistePorEmail(email: String): Boolean

}