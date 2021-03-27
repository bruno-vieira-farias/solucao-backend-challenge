package br.com.bruno.backendchallenge.domain.ValidadoresSenha

import br.com.bruno.backendchallenge.domain.ValidacaoSenhaService
import org.springframework.stereotype.Service

@Service
class ValidacaoSenhaServiceImp() : ValidacaoSenhaService {

    override fun validaSenha(senha: String): Boolean {
        try {
            //TODO - Que tal colocar estes "números mágicos" em uma arquivo de properties.
            require(senha.possuiQuantidadeMinimaCaracteres(quantidadeMinimaCaracteres = 9))
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