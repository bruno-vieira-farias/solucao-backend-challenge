package br.com.bruno.backendchallenge.endpoint.validacaoSenha

import br.com.bruno.backendchallenge.domain.ValidacaoSenhaService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
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
    private val logger: Logger = LoggerFactory.getLogger(ValidacaoSenhaController::class.java)

    @PostMapping("/api/validacao-senha")
    fun validaSenha(@RequestBody payload: EntradaDto): ResponseEntity<RetornoSucessoDto> {
        val resultado = validadorSenhaService.validaSenha(payload.senha)
        return ResponseEntity(RetornoSucessoDto(resultado), HttpStatus.OK)
    }

    @ExceptionHandler(Exception::class)
    fun capturaExcecoes(ex: Exception): ResponseEntity<RetornoErroDto> {
        logger.error(ex.stackTraceToString())

        return ResponseEntity(RetornoErroDto(mensagemErro = "Ocorreu um erro nao mapeado."),HttpStatus.INTERNAL_SERVER_ERROR)
    }
}