package com.curso.udemy.pontoeletronicokotlin.security

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.dao.DaoAuthenticationProvider
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder


/**
 * @Configuration -> Mostrando pro spring que é uma classe de configuração
 * @EnableWebSecurity -> Habilitando o web security na aplicação
 * EnableGlobalMethodSecurity -> Validar o metodo especifico por perfil
 *
 */

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
class SecurityConfig(val funcionarioDetailService: FuncionarioDetailService) : WebSecurityConfigurerAdapter() {

    override fun configure(auth: AuthenticationManagerBuilder?) {
        auth?.authenticationProvider(authenticationProvider())
    }

    override fun configure(http: HttpSecurity?) {
        //Regras de segurança
        http?.
            authorizeRequests()?. // Autenticação nas requisições
            antMatchers("/api/cadastrar-pj", "/api/cadastrar-pf")?. // Fazer com que essas rotas sejam permitidas
            permitAll()?. // Permitindo rotas informadas no comando anterior
            anyRequest()?. // Autenticar todas requisições
            authenticated()?.and()?. // Garantir todas requisições autenticadas e
            httpBasic()?.and()?. // Será utilizada autenticação basica e
            sessionManagement()?. // Vou usar gerenciamento de sessão
            sessionCreationPolicy(SessionCreationPolicy.STATELESS)?.and()?. // Valida durante a requisição(desde o inicio da chamada até o retorno e)
            csrf()?.disable() // Desabilitando proteção de segurança
    }

    @Bean
    fun authenticationProvider(): DaoAuthenticationProvider {
        val authProvider = DaoAuthenticationProvider()
        authProvider.setUserDetailsService(funcionarioDetailService)
        authProvider.setPasswordEncoder(encoder())
        return authProvider
    }

    @Bean
    fun encoder(): PasswordEncoder = BCryptPasswordEncoder()
}