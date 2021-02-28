package com.curso.udemy.pontoeletronicokotlin.controllers

import com.curso.udemy.pontoeletronicokotlin.documents.Empresa
import com.curso.udemy.pontoeletronicokotlin.documents.Funcionario
import com.curso.udemy.pontoeletronicokotlin.dtos.CadastroPjDto
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
@RequestMapping("api/cadastrar-pj")
class CadastroPJController (val empresaService: EmpresaService,
                            val funcionarioService: FuncionarioService) {

    @PostMapping
    fun cadastrar(@Valid @RequestBody cadastroPJDto: CadastroPjDto,
                    result: BindingResult): ResponseEntity<Response<CadastroPjDto>> {

        val response: Response<CadastroPjDto> = Response<CadastroPjDto>()

        validarDadosExistentes(cadastroPJDto, result)

        if (result.hasErrors()) {
            result.allErrors.forEach{erro -> response.erros.add(erro.defaultMessage.toString())}
            return ResponseEntity.badRequest().body(response)
        }

        val empresa: Empresa = converterDtoParaEmpresa(cadastroPJDto)
        empresaService.persistir(empresa)

        var funcionario: Funcionario = converterDtoParaFuncionario(cadastroPJDto, empresa)
        funcionarioService.persistir(funcionario)
        response.data = converterCadastroPJDto(funcionario, empresa)

        return ResponseEntity.ok(response)

    }

    private fun converterCadastroPJDto(funcionario: Funcionario, empresa: Empresa): CadastroPjDto =
            CadastroPjDto(
                    funcionario.nome,
                    funcionario.email,
                    "",
                    funcionario.cpf,
                    empresa.cnpj,
                    empresa.razaoSocial,
                    funcionario.id
            )

    private fun converterDtoParaFuncionario(cadastroPJDto: CadastroPjDto, empresa: Empresa): Funcionario =
            Funcionario(
                    cadastroPJDto.nome,
                    cadastroPJDto.email,
                    SenhaUtils().gerarBCrypt(cadastroPJDto.senha),
                    cadastroPJDto.cpf,
                    PerfilEnum.ROLE_ADMIN,
                    empresa.id.toString()
            )

    private fun converterDtoParaEmpresa(cadastroPJDto: CadastroPjDto): Empresa =
            Empresa(cadastroPJDto.razaoSocial, cadastroPJDto.cnpj)

    private fun validarDadosExistentes(cadastroPJDto: CadastroPjDto, result: BindingResult) {
        if (empresaService.empresaExistePorCnpj(cadastroPJDto.cnpj)) {
            result.addError(ObjectError("empresa", "Empresa já existente."))
        }

        if (funcionarioService.funcionarioExistePorCpf(cadastroPJDto.cpf)) {
            result.addError(ObjectError("funcionario", "CPF já existente."))
        }

        if (funcionarioService.funcionarioExistePorEmail(cadastroPJDto.email)) {
            result.addError(ObjectError("funcionario", "Email já existente."))
        }
    }


}