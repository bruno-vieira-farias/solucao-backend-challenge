package br.com.bruno.backendchallenge.domain.ValidadoresSenha

import br.com.bruno.backendchallenge.domain.ValidacaoSenhaService
import org.springframework.stereotype.Service

@Service
class ValidacaoSenhaServiceImp(
        private val config: ValidacaoSenhaProperties
) : ValidacaoSenhaService {

    override fun validaSenha(senha: String): Boolean {
        try {
            require(senha.possuiQuantidadeMinimaCaracteres(config.quantidadeMinimaCaracteres!!))
            require(senha.possuiQuantidadeMinimaDigitos(quantidadeMinimaDigitos = 1))
            require(senha.possuiQuantidadeMinimaLetrasMinusculas(quantidadeMinimaLetrasMinusculas = 1))
            require(senha.possuiQuantidadeMinimaLetrasMaiusculas(quantidadeMinimaLetrasMaiusculas = 1))
            require(senha.possuiQuantidadeMinimaCaracteresEspeciais(quantidadeMinimaCaracteresEspeciais = 1))
            require(!senha.possuiCaracteresRepetidos())
            require(!senha.possuiEspacosEmBranco())
        }
        catch (e: IllegalArgumentException) {
            return false
        }

        return true
    }
}