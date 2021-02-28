package com.curso.udemy.pontoeletronicokotlin

import com.curso.udemy.pontoeletronicokotlin.documents.Empresa
import com.curso.udemy.pontoeletronicokotlin.documents.Funcionario
import com.curso.udemy.pontoeletronicokotlin.enums.PerfilEnum
import com.curso.udemy.pontoeletronicokotlin.repositories.EmpresaRepository
import com.curso.udemy.pontoeletronicokotlin.repositories.FuncionarioRepository
import com.curso.udemy.pontoeletronicokotlin.repositories.LancamentoRepository
import com.curso.udemy.pontoeletronicokotlin.utils.SenhaUtils
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

/**
 * !! -> Operador para garantir que no atributo vai estar o valor
 *
 *
 */

@SpringBootApplication
class PontoEletronicoKotlinApplication
(
		val empresaRepository: EmpresaRepository,
		val funcionarioRepository: FuncionarioRepository,
		val lancamentoRepository: LancamentoRepository) : CommandLineRunner {


	override fun run(vararg args: String?) {
		empresaRepository.deleteAll()
		funcionarioRepository.deleteAll()
		lancamentoRepository.deleteAll()

		val empresa: Empresa = empresaRepository.save(Empresa("Empresa", "10443887000146"))

		val admin: Funcionario = funcionarioRepository.save(Funcionario(
				"Admin",
				"admin@empresa.com",
				SenhaUtils().gerarBCrypt("1234"),
				"87628158811",
				PerfilEnum.ROLE_ADMIN,
				empresa.id!!,
				15.0,
				8f,
				1f
		))

		val funcionario: Funcionario = funcionarioRepository.save(Funcionario(
				"Funcionario",
				"funcionario@empresa.com",
				SenhaUtils().gerarBCrypt("1234"),
				"62598669843",
				PerfilEnum.ROLE_USUARIO,
				empresa.id,
				15.0,
				8f,
				1f
		))

		print("\n********************************************************************")
		print("\n********************************************************************")
		print("\nID dos documentos")
		println("Empresa ID: " + empresa.id)
		println("Admin ID: " + admin.id)
		println("Funcionario ID: " + funcionario.id)

		print("\n********************************************************************")
		print("\n********************************************************************")
		print("\nSelect empresa by cnpj\n")
		println(empresaRepository.findByCnpj("10443887000146"))

		print("\n********************************************************************")
		print("\n********************************************************************")
		print("\nSelect funcionario by cpf\n")
		println(funcionarioRepository.findByCpf("87628158811"))

		print("\n********************************************************************")
		print("\n********************************************************************")
		print("\nSelect funcionario by email\n")
		println(funcionarioRepository.findByEmail("funcionario@empresa.com"))


		print("\n********************************************************************")
		print("\n********************************************************************")
		print("\nSERVIDOR EM PE\n")

	}

}

fun main(args: Array<String?>) {
	SpringApplication.run(PontoEletronicoKotlinApplication::class.java, *args)
}


