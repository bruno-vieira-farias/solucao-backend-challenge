package br.com.bruno.backendchallenge.endpoint.validacaoSenha

import br.com.bruno.backendchallenge.domain.ValidacaoSenhaService
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiResponse
import io.swagger.annotations.ApiResponses
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

    @ApiOperation(value = "Analisa se a senha informada é válida.")
    @ApiResponses(value = [
        ApiResponse(code = 200, message = "Validação realizada com sucesso."),
        ApiResponse(code = 500, message = "Validação não realizada porque ocorreu um erro inesperado.")
    ])
    @PostMapping("/api/validacao-senha")
    fun validaSenha(@RequestBody payload: EntradaDto): ResponseEntity<RetornoDto> {
        val resultado = validadorSenhaService.validaSenha(payload.senha)
        return ResponseEntity(RetornoDto(resultado, possuiErro = false), HttpStatus.OK)
    }

    @ExceptionHandler(Exception::class)
    fun capturaExcecoes(ex: Exception): ResponseEntity<RetornoDto> {
        logger.error(ex.stackTraceToString())
        return ResponseEntity(RetornoDto(possuiErro = true, isSenhaValida = null), HttpStatus.INTERNAL_SERVER_ERROR)
    }
}