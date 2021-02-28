package com.curso.udemy.pontoeletronicokotlin.services.Impl

import com.curso.udemy.pontoeletronicokotlin.documents.Empresa
import com.curso.udemy.pontoeletronicokotlin.exception.NotFoundException
import com.curso.udemy.pontoeletronicokotlin.repositories.EmpresaRepository
import com.curso.udemy.pontoeletronicokotlin.services.EmpresaService
import org.springframework.stereotype.Service

/**
 * Ao declarar no construtor padrão a variavel para empresaRepository o spring já identifica sozinho a sua instancia
 */

@Service
class EmpresaServiceImpl(val empresaRepository: EmpresaRepository) : EmpresaService {


    override fun buscarPorCnpj(cnpj: String): Empresa = empresaRepository.findByCnpj(cnpj)
            .orElseThrow{NotFoundException("Empresa não encontrada com esse cnpj: $cnpj")}

    override fun persistir(empresa: Empresa): Empresa = empresaRepository.save(empresa)

    override fun buscarPorId(id: String): Empresa = empresaRepository.findById(id)
            .orElseThrow{NotFoundException("Empresa não encontrada com esse id: $id")}

    override fun empresaExistePorCnpj(cnpj: String): Boolean = empresaRepository.existsByCnpj(cnpj)
}