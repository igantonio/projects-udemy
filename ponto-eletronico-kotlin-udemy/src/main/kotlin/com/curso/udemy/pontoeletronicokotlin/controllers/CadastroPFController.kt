package com.curso.udemy.pontoeletronicokotlin.controllers

import com.curso.udemy.pontoeletronicokotlin.documents.Empresa
import com.curso.udemy.pontoeletronicokotlin.documents.Funcionario
import com.curso.udemy.pontoeletronicokotlin.dtos.CadastroPfDto
import com.curso.udemy.pontoeletronicokotlin.enums.PerfilEnum
import com.curso.udemy.pontoeletronicokotlin.response.Response
import com.curso.udemy.pontoeletronicokotlin.services.EmpresaService
import com.curso.udemy.pontoeletronicokotlin.services.FuncionarioService
import com.curso.udemy.pontoeletronicokotlin.utils.SenhaUtils
import org.springframework.http.ResponseEntity
import org.springframework.validation.BindingResult
import org.springframework.validation.ObjectError
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping("api/cadastrar-pf")
class CadastroPFController (val empresaService: EmpresaService,
                            val funcionarioService: FuncionarioService) {

    @PostMapping
    fun cadastrar(@Valid @RequestBody cadastroPfDto: CadastroPfDto,
                  result: BindingResult): ResponseEntity<Response<CadastroPfDto>> {

        val response: Response<CadastroPfDto> = Response<CadastroPfDto>()
        val empresa: Empresa = empresaService.buscarPorCnpj(cadastroPfDto.cnpj)

        validarDadosExistentes(cadastroPfDto, empresa, result)

        if (result.hasErrors()) {
            result.allErrors.forEach{erro -> response.erros.add(erro.defaultMessage.toString())}
            return ResponseEntity.badRequest().body(response)
        }


        var funcionario: Funcionario = converterDtoParaFuncionario(cadastroPfDto, empresa)
        funcionarioService.persistir(funcionario)
        response.data = converterCadastroPFDto(funcionario, empresa)

        return ResponseEntity.ok(response)

    }

    private fun converterCadastroPFDto(funcionario: Funcionario, empresa: Empresa): CadastroPfDto =
            CadastroPfDto(
                    funcionario.nome,
                    funcionario.email,
                    "",
                    funcionario.cpf,
                    empresa.cnpj,
                    empresa.id.toString(),
                    funcionario.valorHora.toString(),
                    funcionario.qtdHorasTrabalhoDia.toString(),
                    funcionario.qtdHorasAlmoco.toString(),
                    funcionario.id
            )

    private fun validarDadosExistentes(cadastroPfDto: CadastroPfDto, empresa: Empresa?, result: BindingResult) {

        if(empresa == null) {
            result.addError(ObjectError("empresa", "Empresa não cadastrada."))
        }

        if (funcionarioService.funcionarioExistePorCpf(cadastroPfDto.cpf)) {
            result.addError(ObjectError("funcionario", "CPF já existente."))
        }

        if (funcionarioService.funcionarioExistePorEmail(cadastroPfDto.email)) {
            result.addError(ObjectError("funcionario", "Email já existente."))
        }
    }

    private fun converterDtoParaFuncionario(cadastroPfDto: CadastroPfDto, empresa: Empresa): Funcionario =
            Funcionario(
                    cadastroPfDto.nome,
                    cadastroPfDto.email,
                    SenhaUtils().gerarBCrypt(cadastroPfDto.senha),
                    cadastroPfDto.cpf,
                    PerfilEnum.ROLE_USUARIO,
                    empresa.id.toString(),
                    cadastroPfDto.valorHora?.toDouble(),
                    cadastroPfDto.qtdHorasTrabalhoDia?.toFloat(),
                    cadastroPfDto.qtdHorasAlmoco?.toFloat(),
                    cadastroPfDto.id
            )

}