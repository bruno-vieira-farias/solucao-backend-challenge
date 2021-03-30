package br.com.bruno.backendchallenge.domain.validadoresSenha

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

/**
 * Representa os valores referentes a validação da senha presente no arquivo de properties.
 */
@Component
data class ValidacaoSenhaProperties(
        @Value("\${senha.quantidade-minima-caracteres}") val quantidadeMinimaCaracteres: Int? = null,
        @Value("\${senha.quantidade-minima-digitos}") val quantidadeMinimaDigitos: Int? = null,
        @Value("\${senha.quantidade-minima-letras-minusculas}") val quantidadeMinimaLetrasMinusculas: Int? = null,
        @Value("\${senha.quantidade-minima-letras-maiusculas}") val quantidadeMinimaLetrasMaiusculas: Int? = null,
        @Value("\${senha.quantidade-minima-caracteres-especiais}") val quantidadeMinimaCaracteresEspeciais: Int? = null
)