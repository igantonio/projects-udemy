package com.curso.udemy.pontoeletronicokotlin.security

import com.curso.udemy.pontoeletronicokotlin.documents.Funcionario
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

/**
 * out -> Identifica que a lista de perfis pode somente receber filhos de GrantedAuthority, ou seja, quem implementa essa interface(forma de proteção de informação)
 * getAuthorities() -> devolve lista de perfil do funcionario que está sendo autenticado
 *
 * isEnabled -> informa se o usuário está autenticado ou não.
 * getUsername -> utilizado para fazer a autenticação
 * isCredentialsNonExpired -> notificar se as credenciais expiram
 * getPassword -> senha para autenticação
 * isAccountNonExpired -> conta expira ou nao
 * isAccountNonLocked -> conta bloqueia ou nao
 */

class FuncionarioPrincipal(val funcionario: Funcionario) : UserDetails {

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        val authorities: MutableCollection<GrantedAuthority> = mutableListOf<GrantedAuthority>()
        authorities.add(SimpleGrantedAuthority(funcionario.perfil.toString()))
        return authorities
    }

    override fun getPassword(): String = funcionario.senha

    override fun getUsername(): String = funcionario.email

    override fun isAccountNonExpired(): Boolean = true

    override fun isAccountNonLocked(): Boolean = true

    override fun isCredentialsNonExpired(): Boolean = true

    override fun isEnabled(): Boolean = true
}