package br.com.bruno.backendchallenge.domain.validadoresSenha

import br.com.bruno.backendchallenge.domain.ValidacaoSenhaService
import org.springframework.stereotype.Service

@Service
class ValidacaoSenhaServiceImp(
        private val config: ValidacaoSenhaProperties
) : ValidacaoSenhaService {

    override fun validaSenha(senha: String): Boolean {
        try {
            require(senha.possuiQuantidadeMinimaCaracteres(config.quantidadeMinimaCaracteres!!))
            require(senha.possuiQuantidadeMinimaDigitos(config.quantidadeMinimaDigitos!!))
            require(senha.possuiQuantidadeMinimaLetrasMinusculas(config.quantidadeMinimaLetrasMinusculas!!))
            require(senha.possuiQuantidadeMinimaLetrasMaiusculas(config.quantidadeMinimaLetrasMaiusculas!!))
            require(senha.possuiQuantidadeMinimaCaracteresEspeciais(config.quantidadeMinimaCaracteresEspeciais!!))
            require(!senha.possuiCaracteresRepetidos())
            require(!senha.possuiEspacosEmBranco())
        } catch (e: IllegalArgumentException) {
            /* Abafa excecoes esperadas */
            return false
        }

        return true
    }
}