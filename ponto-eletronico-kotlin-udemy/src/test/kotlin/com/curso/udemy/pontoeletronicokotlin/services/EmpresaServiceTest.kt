package com.curso.udemy.pontoeletronicokotlin.services


/**
 * @MockBean -> Cria um objeto falso.
 * @Before -> Executa sempre antes de cada teste
 */

//@RunWith(SpringRunner::class)
//@SpringBootTest
//@Profile("test")
class EmpresaServiceTest {

//    @Autowired
//    val empresaService: EmpresaService? = null
//
//    @MockBean
//    private val empresaRepository: EmpresaRepository? = null
//
//    private val CNPJ = "51463645000100"
//
//    @Before
//    @Throws(Exception::class)
//    fun setUp() {
//        /**
//         * O papel do "?" nesse cenário é como se fosse um IF,
//         *    validando, caso a empresaRepository não seja nula chama o findByCnpj.
//         *    Caso seja nula não chama
//         */
//        BDDMockito.given(empresaRepository?.findByCnpj(CNPJ)).willReturn(empresa())
//        BDDMockito.given(empresaRepository?.save(empresa())).willReturn(empresa())
//    }
//
//    @Test
//    fun testBuscarEmpresaPorCnpj() {
//        val empresa: Empresa? = empresaService?.buscarPorCnpj(CNPJ)
//        Assert.assertNotNull(empresa)
//    }
//
//    @Test
//    fun testPersistirEmpresa() {
//        val empresa: Empresa? = empresaService?.persistir(empresa())
//        Assert.assertNotNull(empresa)
//    }
//
//    private fun empresa(): Empresa = Empresa("Razão SOcial", CNPJ, "1")

}