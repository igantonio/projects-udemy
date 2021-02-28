package com.curso.udemy.pontoeletronicokotlin.documents

import com.curso.udemy.pontoeletronicokotlin.enums.TipoEnum
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.util.*

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
data class Lancamento (

        val data: Date,
        val tipo: TipoEnum,
        val funcionarioId: String,
        val descricao: String? = "",
        val localizacao: String? = "",
        @Id val id: String? = null,
)