package br.com.bruno.backendchallenge.domain.ValidadoresSenha

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.function.Executable

internal class ValidacaoSenhaServiceImpTest{

    private val validador = ValidacaoSenhaServiceImp()

    @Test
    fun `Valida senhas que estão no README do challenge`(){
        assertAll(
                Executable { assertFalse(validador.validaSenha("")) },
                Executable { assertFalse(validador.validaSenha("aa")) },
                Executable { assertFalse(validador.validaSenha("ab")) },
                Executable { assertFalse(validador.validaSenha("AAAbbbCc")) },
                Executable { assertFalse(validador.validaSenha("AbTp9!foo")) },
                Executable { assertFalse(validador.validaSenha("AbTp9!foA")) },
                Executable { assertFalse(validador.validaSenha("AbTp9 fok")) },
                Executable { assertTrue(validador.validaSenha("AbTp9!fok")) },
        )
    }
}