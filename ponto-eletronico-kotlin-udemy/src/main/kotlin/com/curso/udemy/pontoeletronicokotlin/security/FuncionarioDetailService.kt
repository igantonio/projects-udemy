package com.curso.udemy.pontoeletronicokotlin.security

import com.curso.udemy.pontoeletronicokotlin.documents.Funcionario
import com.curso.udemy.pontoeletronicokotlin.services.FuncionarioService
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class FuncionarioDetailService(val funcionarioService: FuncionarioService) : UserDetailsService {

    override fun loadUserByUsername(username: String?): UserDetails {
        if (username != null) {
            val funcionario: Funcionario = funcionarioService.buscarPorEmail(username)
            return FuncionarioPrincipal(funcionario)
        }
        throw UsernameNotFoundException(username)
    }
}