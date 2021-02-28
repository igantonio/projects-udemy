package com.curso.udemy.pontoeletronicokotlin.exception.advice

class StandardError(
        val timestamp: String,
        val status: Int,
        val errorTitle: String,
        val message: String,
        val path: String
)