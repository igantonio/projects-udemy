package com.curso.udemy.pontoeletronicokotlin.controller

import com.curso.udemy.pontoeletronicokotlin.documents.Funcionario
import com.curso.udemy.pontoeletronicokotlin.documents.Lancamento
import com.curso.udemy.pontoeletronicokotlin.dtos.LancamentoDto
import com.curso.udemy.pontoeletronicokotlin.enums.PerfilEnum
import com.curso.udemy.pontoeletronicokotlin.enums.TipoEnum
import com.curso.udemy.pontoeletronicokotlin.services.FuncionarioService
import com.curso.udemy.pontoeletronicokotlin.services.LancamentoService
import com.curso.udemy.pontoeletronicokotlin.utils.SenhaUtils
import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.context.annotation.Profile
import org.springframework.http.MediaType
import org.springframework.security.test.context.support.WithMockUser
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import java.text.SimpleDateFormat
import java.util.*

//@RunWith(SpringRunner::class)
//@SpringBootTest
//@AutoConfigureMockMvc
//@Profile("test")
class LancamentoControllerTest {

//    @Autowired
//    private val mvc: MockMvc? = null
//
//    @MockBean
//    private val lancamentoService: LancamentoService? = null
//
//    @MockBean
//    private val funcionarioService: FuncionarioService? = null
//
//    private val urlBase: String = "/api/lancamentos/"
//    private val idFuncionario: String = "1"
//    private val idLancamento: String = "1"
//    private val tipo: String = TipoEnum.INICIO_TRABALHO.name
//    private val data: Date = Date()
//
//    private val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
//
//    @Test
//    @Throws(Exception::class)
//    @WithMockUser
//    fun testCadastrarLancamento() {
//        val lancamento: Lancamento = obterDadosLancamento()
//        BDDMockito.given<Funcionario>(funcionarioService?.buscarPorId(idFuncionario))
//                .willReturn(funcionario())
//
//        BDDMockito.given(lancamentoService?.persistir(obterDadosLancamento()))
//                .willReturn(lancamento)
//
//        mvc!!.perform(MockMvcRequestBuilders.post(urlBase)
//                .content(obterJsonRequisicaoPost())
//                .contentType(MediaType.APPLICATION_JSON)
//                .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk)
//                .andExpect(jsonPath("$.data.tipo").value(tipo))
//                .andExpect(jsonPath("$.data.data").value(dateFormat.format(data)))
//                .andExpect(jsonPath("$.data.funcionarioId").value(idFuncionario))
//                .andExpect(jsonPath("$.data.erros").isEmpty())
//    }
//
//    @Test
//    @Throws(Exception::class)
//    @WithMockUser
//    fun testCadastrarLancamentoFuncionarioIdInvalido() {
//        BDDMockito.given<Funcionario>(funcionarioService?.buscarPorId(idFuncionario))
//                .willReturn(null)
//
//        mvc!!.perform(MockMvcRequestBuilders.post(urlBase)
//                .content(obterJsonRequisicaoPost())
//                .contentType(MediaType.APPLICATION_JSON)
//                .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isBadRequest())
//                .andExpect(jsonPath("$.erros").value("Funcionário não encontrado. ID inexistente."))
//                .andExpect(jsonPath("$.data").isEmpty())
//    }
//
//    @Test
//    @Throws(Exception::class)
//    @WithMockUser(username = "admin@admin.com", roles = arrayOf("ADMIN"))
//    fun testRemoverLancamento() {
//        BDDMockito.given<Lancamento>(lancamentoService?.buscarPorId(idLancamento))
//                .willReturn(obterDadosLancamento())
//
//        mvc!!.perform(MockMvcRequestBuilders.delete(urlBase+idLancamento)
//                .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk)
//    }

//    @Throws(JsonProcessingException::class)
//    private fun obterJsonRequisicaoPost(): String {
//        val lancamentoDto: LancamentoDto = LancamentoDto(dateFormat.format(data), tipo, "Descrição", "1.243.4.345", idFuncionario)
//
//        val mapper = ObjectMapper()
//        return mapper.writeValueAsString(lancamentoDto)
//    }
//
//    private fun obterDadosLancamento(): Lancamento =
//            Lancamento(data, TipoEnum.valueOf(tipo), idFuncionario, "Descricao", "1.243.4.345", idLancamento)
//
//    private fun funcionario(): Funcionario =
//            Funcionario(
//                    "Nome",
//                    "email@gmail.com",
//                    SenhaUtils().gerarBCrypt("123456"),
//                    "06098609845",
//                    PerfilEnum.ROLE_USUARIO,
//                    idFuncionario
//            )

}