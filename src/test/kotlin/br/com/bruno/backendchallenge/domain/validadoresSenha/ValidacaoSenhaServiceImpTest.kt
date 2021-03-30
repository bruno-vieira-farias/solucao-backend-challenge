package br.com.bruno.backendchallenge.domain.validadoresSenha

import br.com.bruno.backendchallenge.domain.ValidacaoSenhaService
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.function.Executable
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
internal class ValidacaoSenhaServiceImpTest{

    @Autowired
    private lateinit var validadorService : ValidacaoSenhaService

    @Test
    fun `Valida senhas que estão no README do challenge`() {
        assertAll(
                Executable { assertFalse(validadorService.validaSenha("")) },
                Executable { assertFalse(validadorService.validaSenha("aa")) },
                Executable { assertFalse(validadorService.validaSenha("ab")) },
                Executable { assertFalse(validadorService.validaSenha("AAAbbbCc")) },
                Executable { assertFalse(validadorService.validaSenha("AbTp9!foo")) },
                Executable { assertFalse(validadorService.validaSenha("AbTp9!foA")) },
                Executable { assertFalse(validadorService.validaSenha("AbTp9 fok")) },
                Executable { assertTrue(validadorService.validaSenha("AbTp9!fok")) }
        )
    }
}