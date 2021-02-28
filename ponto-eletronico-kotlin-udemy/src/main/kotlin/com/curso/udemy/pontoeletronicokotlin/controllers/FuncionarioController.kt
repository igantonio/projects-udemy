package com.curso.udemy.pontoeletronicokotlin.controllers

import com.curso.udemy.pontoeletronicokotlin.documents.Funcionario
import com.curso.udemy.pontoeletronicokotlin.dtos.FuncionarioDto
import com.curso.udemy.pontoeletronicokotlin.response.Response
import com.curso.udemy.pontoeletronicokotlin.services.FuncionarioService
import com.curso.udemy.pontoeletronicokotlin.utils.SenhaUtils
import org.springframework.http.ResponseEntity
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("api/funcionarios")
class FuncionarioController(val funcionarioService: FuncionarioService) {

    @PutMapping("/{id}")
    fun atualizar(@PathVariable("id") id: String,
                  @Valid @RequestBody funcionarioDto: FuncionarioDto,
                  result: BindingResult): ResponseEntity<Response<FuncionarioDto>> {
        val response: Response<FuncionarioDto> = Response<FuncionarioDto>()
        val funcionario: Funcionario = funcionarioService.buscarPorId(id)

        if (result.hasErrors()) {
            result.allErrors.forEach{erro -> response.erros.add(erro.defaultMessage.toString())}
            return ResponseEntity.badRequest().body(response)
        }

        val funcAtualizar: Funcionario = atualizarDadosFuncionario(funcionario, funcionarioDto)
        funcionarioService.persistir(funcionario)
        response.data = converterFuncionarioDto(funcAtualizar)
        return ResponseEntity.ok(response)
    }

    private fun atualizarDadosFuncionario(funcionario: Funcionario, funcionarioDto: FuncionarioDto): Funcionario {
        var senha: String
        if(funcionarioDto.senha == null) {
            senha = funcionario.senha
        } else {
            senha = SenhaUtils().gerarBCrypt(funcionarioDto.senha)
        }

        return Funcionario(
                funcionarioDto.nome,
                funcionario.email,
                senha,
                funcionario.cpf,
                funcionario.perfil,
                funcionario.empresaId,
                funcionarioDto.valorHora?.toDouble(),
                funcionarioDto.qtdHorasTrabalhoDia?.toFloat(),
                funcionarioDto.qtdHorasAlmoco?.toFloat(),
                funcionario.id
        )
    }

    private fun converterFuncionarioDto(funcionario: Funcionario): FuncionarioDto =
            FuncionarioDto(
                    funcionario.nome,
                    funcionario.email,
                    "",
                    funcionario.valorHora.toString(),
                    funcionario.qtdHorasTrabalhoDia.toString(),
                    funcionario.qtdHorasAlmoco.toString(),
                    funcionario.id
            )

}