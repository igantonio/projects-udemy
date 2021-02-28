package com.curso.udemy.pontoeletronicokotlin.controllers

import com.curso.udemy.pontoeletronicokotlin.documents.Empresa
import com.curso.udemy.pontoeletronicokotlin.dtos.EmpresaDto
import com.curso.udemy.pontoeletronicokotlin.response.Response
import com.curso.udemy.pontoeletronicokotlin.services.EmpresaService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/empresas")
class EmpresaController(val empresaService: EmpresaService) {

    @GetMapping("/cnpj/{cnpj}")
    fun buscarPorCnpj(@PathVariable("cnpj") cnpj: String): ResponseEntity<Response<EmpresaDto>> {
        val response: Response<EmpresaDto> = Response<EmpresaDto>()
        val empresa: Empresa = empresaService.buscarPorCnpj(cnpj)

        response.data = converterEmpresaDto(empresa)
        return ResponseEntity.ok(response)
    }

    private fun converterEmpresaDto(empresa: Empresa): EmpresaDto =
            EmpresaDto(empresa.razaoSocial, empresa.cnpj, empresa.id)

}