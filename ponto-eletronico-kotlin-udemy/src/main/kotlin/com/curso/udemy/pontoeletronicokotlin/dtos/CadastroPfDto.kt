package com.curso.udemy.pontoeletronicokotlin.dtos

import org.hibernate.validator.constraints.Email
import org.hibernate.validator.constraints.Length
import org.hibernate.validator.constraints.NotEmpty
import org.hibernate.validator.constraints.br.CPF
import org.hibernate.validator.constraints.br.CNPJ

data class CadastroPfDto(

        @get:NotEmpty(message = "Nome não pode ser vazio.")
        @get:Length(min = 3, max = 200, message = "Nome deve conter entre 3 e 200 caracteres.")
        val nome: String = "",

        @get:NotEmpty(message = "Nome não pode ser vazio.")
        @get:Length(min = 5, max = 200, message = "Nome deve conter entre 5 e 200 caracteres.")
        @get:Email(message = "Email inválido.")
        val email: String = "",

        @get:NotEmpty(message = "Senha não pode ser vazio.")
        val senha: String = "",

        @get:NotEmpty(message = "CPF não pode ser vazio.")
        @get:CPF(message = "CPF inválido.")
        val cpf: String = "",

        @get:NotEmpty(message = "Nome não pode ser vazio.")
        @get:CNPJ(message = "CNPJ inválido.")
        val cnpj: String = "",

        val empresaId: String = "",

        val valorHora: String? = null,

        val qtdHorasTrabalhoDia: String? = null,

        val qtdHorasAlmoco: String? = null,

        val id: String? = null,
)
