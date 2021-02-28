package com.curso.udemy.pontoeletronicokotlin.documents

import com.curso.udemy.pontoeletronicokotlin.enums.PerfilEnum
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

/*
* Quando abro Empresa e os parensetes, eu estou definindo um contrutor pra esse documento.
*
* val -> é uma variavel que não pode ser alterada, como se fosse o final. Depois que define o estado dela não pode ser modificado
* var -> variavel, pode ser modificado
* ? -> Operador utilizado para identificar que o atributo pode ser nulo
*
* @Document ->vToda classe que representa um mongo deve ser anotada com .
* @Id -> Toda classe que representa um mongo deve possuir um id
*
* data -> representa os acessores aos atributos da empresa. Ex os getters e setters
* */

@Document
data class Funcionario (

        var nome: String,
        val email: String,
        val senha: String,
        val cpf: String,
        val perfil: PerfilEnum,
        val empresaId: String,
        val valorHora: Double? = 0.0,
        val qtdHorasTrabalhoDia: Float? = 0.0f,
        val qtdHorasAlmoco: Float? = 0.0f,
        @Id val id: String? = null,
)