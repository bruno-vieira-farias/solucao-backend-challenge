package br.com.bruno.backendchallenge.endpoint.validacaoSenha

import br.com.bruno.backendchallenge.domain.ValidacaoSenhaService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController


@RestController
class ValidacaoSenhaController(
        private val validadorSenhaService: ValidacaoSenhaService
) {

    @PostMapping("/api/validacao-senha")
    fun validaSenha(@RequestBody payload: EntradaDto): ResponseEntity<RetornoSucessoDto> {
        val resultado = validadorSenhaService.validaSenha(payload.senha)
        return ResponseEntity(RetornoSucessoDto(resultado), HttpStatus.OK)
    }

    @ExceptionHandler(Exception::class)
    fun capturaValidacoesBeanValidation(ex: Exception): ResponseEntity<RetornoErroDto> {
        //TODO - Logar a ocorrencia do erro para consulta técnica.
        val mensagemErro = "Ocorreu um erro nao mapeado."
        return ResponseEntity<RetornoErroDto>(RetornoErroDto(mensagemErro), HttpStatus.INTERNAL_SERVER_ERROR)
    }

}