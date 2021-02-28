package com.curso.udemy.pontoeletronicokotlin.exception.advice

import com.curso.udemy.pontoeletronicokotlin.exception.NotFoundException
import org.springframework.http.HttpEntity
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.client.HttpClientErrorException
import java.text.SimpleDateFormat
import javax.servlet.http.HttpServletRequest

@ControllerAdvice
class ControllerAdvice {

    private val formatter = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")

    @ExceptionHandler(NotFoundException::class)
    fun notFoundException(e: NotFoundException, httpRequest: HttpServletRequest): HttpEntity<StandardError> {
        val status: HttpStatus = HttpStatus.NOT_FOUND

        return ResponseEntity
                .status(status)
                .body(newStandardError(status, "Recurso não encontrado!", e, httpRequest))

    }

    @ExceptionHandler(HttpClientErrorException.Unauthorized::class)
    fun unauthotized(e: NotFoundException, httpRequest: HttpServletRequest): HttpEntity<StandardError> {
        val status: HttpStatus = HttpStatus.UNAUTHORIZED

        return ResponseEntity
                .status(status)
                .body(newStandardError(status, "Usuário não autorizado", e, httpRequest))

    }

    fun newStandardError(status: HttpStatus, titleError: String, e: RuntimeException, request: HttpServletRequest): StandardError
        = StandardError(
                formatter.format(System.currentTimeMillis()),
                status.value(),
                titleError,
                e.message.toString(),
                request.requestURI
        )

}