package br.com.bruno.backendchallenge.endpoint.validacaoSenha

import br.com.bruno.backendchallenge.domain.ValidacaoSenhaService
import br.com.bruno.backendchallenge.endpoint.enviaRequisicaoHttpPost
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.Assertions.assertAll
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.function.Executable
import org.mockito.Mockito.`when`
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.HttpStatus
import org.springframework.test.web.servlet.MockMvc

@SpringBootTest
@AutoConfigureMockMvc
internal class ValidacaoSenhaControllerRetornoErroTest {
    val endpoint = "/api/validacao-senha"

    @Autowired
    lateinit var mockMvc: MockMvc

    @Autowired
    lateinit var objectMapper: ObjectMapper

    @MockBean
    lateinit var service: ValidacaoSenhaService

    @Test
    fun `retorno de ERRO (forçado)`() {
        `when`(service.validaSenha("")).thenThrow(IllegalArgumentException::class.java)

        val entradaDtoAsJSON = objectMapper.writeValueAsString(EntradaDto(""))
        val retornoDtoAsJSON = objectMapper.writeValueAsString(RetornoDto(isSenhaValida = null, possuiErro = true))

        val resultado = enviaRequisicaoHttpPost(mockMvc, endpoint, entradaDtoAsJSON)

        assertAll(
                Executable { assertEquals(resultado.response.status, HttpStatus.INTERNAL_SERVER_ERROR.value()) },
                Executable { assertEquals(resultado.response.contentAsString, retornoDtoAsJSON) }
        )
    }

}