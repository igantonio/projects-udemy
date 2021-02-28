package com.curso.udemy.pontoeletronicokotlin.controllers

import com.curso.udemy.pontoeletronicokotlin.documents.Funcionario
import com.curso.udemy.pontoeletronicokotlin.documents.Lancamento
import com.curso.udemy.pontoeletronicokotlin.dtos.LancamentoDto
import com.curso.udemy.pontoeletronicokotlin.enums.TipoEnum
import com.curso.udemy.pontoeletronicokotlin.response.Response
import com.curso.udemy.pontoeletronicokotlin.services.FuncionarioService
import com.curso.udemy.pontoeletronicokotlin.services.LancamentoService
import org.springframework.beans.factory.annotation.Value
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.validation.BindingResult
import org.springframework.validation.ObjectError
import org.springframework.web.bind.annotation.*
import java.text.SimpleDateFormat
import javax.validation.Valid

@RestController
@RequestMapping("/api/lancamentos")
class LancamentoController(val lancamentoService: LancamentoService,
                           val funcionarioService: FuncionarioService) {

    private val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")

    @Value("\${paginacao.qtd_por_pagina}")
    val qtdPorPagina: Int = 15

    @PostMapping
    fun adicionar(@Valid @RequestBody lancamentoDto:LancamentoDto,
                    result: BindingResult) :ResponseEntity<Response<LancamentoDto>> {
        val response: Response<LancamentoDto> = Response<LancamentoDto>()
        validarFuncionario(lancamentoDto, result)

        if(result.hasErrors()) {
            for (erro in result.allErrors) response.erros.add(erro.defaultMessage.toString())

            return ResponseEntity.badRequest().body(response)
        }

        val lancamento: Lancamento = converterDtoParaLancamento(lancamentoDto, result)
        lancamentoService.persistir(lancamento)
        response.data = converterLancamentoDto(lancamento)

        return ResponseEntity.ok(response)
    }

    @GetMapping("/{id}")
    fun listarPorId(@PathVariable("id") id: String) : ResponseEntity<Response<LancamentoDto>> {
        val response: Response<LancamentoDto> = Response<LancamentoDto>()
        val lancamento: Lancamento? = lancamentoService.buscarPorId(id)

        if(lancamento == null) {
            response.erros.add("Lancamento não encontrado para o id $id")
            return ResponseEntity.badRequest().body(response)
        }

        response.data = converterLancamentoDto(lancamento)
        return ResponseEntity.ok(response)
    }

    @GetMapping("/funcionario/{funcionarioId}")
    fun listarPorFuncionarioId(@PathVariable("funcionarioId") funcionarioId: String,
                                @RequestParam(value = "pag", defaultValue = "0") pag: Int,
                                @RequestParam(value = "ord", defaultValue = "id") ord: String,
                                @RequestParam(value = "dir", defaultValue = "DESC") dir: String):
            ResponseEntity<Response<Page<LancamentoDto>>> {

        val response: Response<Page<LancamentoDto>> = Response<Page<LancamentoDto>>()
        val pageRequest: PageRequest = PageRequest.of(pag, qtdPorPagina, Sort.Direction.valueOf(dir), ord)

        val lancamentos: Page<Lancamento> = lancamentoService.buscarPorFuncionarioId(funcionarioId, pageRequest)

        val lancamentosDto: Page<LancamentoDto> = lancamentos.map(this::converterLancamentoDto)

        response.data = lancamentosDto
        return ResponseEntity.ok(response)
    }

    @PutMapping("/{id}")
    fun atualizar(@PathVariable("id") id: String, @Valid @RequestBody lancamentoDto: LancamentoDto,
                  result: BindingResult) :ResponseEntity<Response<LancamentoDto>> {
        val response: Response<LancamentoDto> = Response<LancamentoDto>()
        validarFuncionario(lancamentoDto, result)

        lancamentoDto.id = id
        val lancamento: Lancamento = converterDtoParaLancamento(lancamentoDto, result)

        if (result.hasErrors()) {
            result.allErrors.forEach{erro -> response.erros.add(erro.defaultMessage.toString())}

            return ResponseEntity.badRequest().body(response)
        }

        lancamentoService.persistir(lancamento)
        response.data = converterLancamentoDto(lancamento)
        return ResponseEntity.ok(response)
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')") // Para remover um lancamento eu devo possuir um usuário com perfil ADMIN
    fun remover(@PathVariable("id") id: String): ResponseEntity<Response<String>> {
        val response: Response<String> = Response<String>()
        val lancamento: Lancamento? = lancamentoService.buscarPorId(id)

        if (lancamento == null) {
            response.erros.add("Erro ao remover o laçamento. Registro não encontrado para o id $id")
            return ResponseEntity.badRequest().body(response)
        }

        lancamentoService.remover(id)
        return ResponseEntity.ok(Response<String>())
    }

    private fun validarFuncionario(lancamentoDto: LancamentoDto, result: BindingResult) {
        if(lancamentoDto.funcionarioId == null) {
            result.addError(ObjectError("funcionario", "Funcionario não informado."))
            return
        }

        val funcionario: Funcionario? = funcionarioService.buscarPorId(lancamentoDto.funcionarioId)
        if (funcionario == null) {
            result.addError(ObjectError("funcionario", "Funcionario não encontrado. ID inexistente."))
        }
    }

    private fun converterDtoParaLancamento(lancamentoDto: LancamentoDto, result: BindingResult): Lancamento {
        if(lancamentoDto.id != null) {
            val lanc: Lancamento? = lancamentoService.buscarPorId(lancamentoDto.id!!)
            if(lanc == null) result.addError(ObjectError("lancamento", "Lancamento não encontrado."))
        }

        return Lancamento(
                dateFormat.parse(lancamentoDto.data),
                TipoEnum.valueOf(lancamentoDto.tipo!!),
                lancamentoDto.funcionarioId!!,
                lancamentoDto.descricao,
                lancamentoDto.localizacao,
                lancamentoDto.id
        )
    }

    private fun converterLancamentoDto(lancamento: Lancamento): LancamentoDto =
            LancamentoDto(
                    dateFormat.format(lancamento.data),
                    lancamento.tipo.toString(),
                    lancamento.descricao,
                    lancamento.localizacao,
                    lancamento.funcionarioId,
                    lancamento.id
            )

}