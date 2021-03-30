package br.com.bruno.backendchallenge.endpoint.validacaoSenha

import br.com.bruno.backendchallenge.endpoint.enviaRequisicaoHttpPost
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.Assertions.assertAll
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.function.Executable
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.HttpStatus
import org.springframework.test.web.servlet.MockMvc

@SpringBootTest
@AutoConfigureMockMvc
internal class ValidacaoSenhaControllerRetornoSucessoTest {
    val endpoint = "/api/validacao-senha"

    @Autowired
    lateinit var mockMvc: MockMvc

    @Autowired
    lateinit var objectMapper: ObjectMapper

    @Test
    fun `Retorno de SUCESSO com senha VÁLIDA`() {
        val entradaDtoAsJSON = objectMapper.writeValueAsString(EntradaDto(senha = "AbTp9!fok"))
        val retornoDtoAsJSON = objectMapper.writeValueAsString(RetornoDto(isSenhaValida = true, possuiErro = false))

        val resultado = enviaRequisicaoHttpPost(mockMvc, endpoint, entradaDtoAsJSON)

        assertAll(
                Executable { assertEquals(resultado.response.status, HttpStatus.OK.value()) },
                Executable { assertEquals(resultado.response.contentAsString, retornoDtoAsJSON) }
        )
    }

    @Test
    fun `Retorno de SUCESSO com senha INVÀLIDA`() {
        val entradaDtoAsJSON = objectMapper.writeValueAsString(EntradaDto(senha = "AbTp9 fok"))
        val retornoDtoAsJSON = objectMapper.writeValueAsString(RetornoDto(isSenhaValida = false, possuiErro = false))

        val resultado = enviaRequisicaoHttpPost(mockMvc, endpoint, entradaDtoAsJSON)

        assertAll(
                Executable { assertEquals(resultado.response.status, HttpStatus.OK.value()) },
                Executable { assertEquals(resultado.response.contentAsString, retornoDtoAsJSON) }
        )
    }
}