package br.com.bruno.backendchallenge.endpoint

import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.MvcResult
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultHandlers

/**
 * Função que envia uma requisição POST para testes de integração.
 */
fun enviaRequisicaoHttpPost(mockMvc: MockMvc, uri: String, entradaAsJson: String): MvcResult {
    return mockMvc.perform(MockMvcRequestBuilders.post(uri)
            .contentType(MediaType.APPLICATION_JSON)
            .content(entradaAsJson)
    ).andDo(MockMvcResultHandlers.print())
            .andReturn()
}