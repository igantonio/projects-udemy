package com.curso.udemy.pontoeletronicokotlin.services

import com.curso.udemy.pontoeletronicokotlin.documents.Empresa

interface EmpresaService {

    fun buscarPorCnpj(cnpj: String): Empresa
    fun persistir(empresa: Empresa): Empresa
    fun buscarPorId(id: String): Empresa
    fun empresaExistePorCnpj(cnpj: String): Boolean
}