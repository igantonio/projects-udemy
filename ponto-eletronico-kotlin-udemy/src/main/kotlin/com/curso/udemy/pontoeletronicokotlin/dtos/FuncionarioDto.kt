package com.curso.udemy.pontoeletronicokotlin.dtos

import org.hibernate.validator.constraints.Email
import org.hibernate.validator.constraints.Length
import org.hibernate.validator.constraints.NotEmpty

data class FuncionarioDto(
        @get:NotEmpty(message = "Nome não pode ser vazio.")
        @get:Length(min = 3, max = 200, message = "Nome deve conter entre 3 e 200 caracteres.")
        val nome: String = "",

        @get:NotEmpty(message = "Nome não pode ser vazio.")
        @get:Length(min = 3, max = 200, message = "Nome deve conter entre 3 e 200 caracteres.")
        @get:Email(message = "Email inválido.")
        val email: String = "",

        val senha: String? = null,

        val valorHora: String? = null,

        val qtdHorasTrabalhoDia: String? = null,

        val qtdHorasAlmoco: String? = null,

        val id: String? = null,

)
