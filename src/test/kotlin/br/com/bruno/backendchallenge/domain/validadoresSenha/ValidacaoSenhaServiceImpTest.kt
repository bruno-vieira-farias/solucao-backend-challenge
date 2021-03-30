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

    @Test
    fun `Não passa no critério de no mínimo 9 caracteres`() {
        assertAll(
                Executable { assertFalse(validadorService.validaSenha("bTp9!fok")) },
                Executable { assertFalse(validadorService.validaSenha("AbTp9!fo")) }
        )
    }

    @Test
    fun `Não passa no critério de no mínimo 1 digito`() {
        assertFalse(validadorService.validaSenha("AbTpz!fok"))
    }

    @Test
    fun `Não passa no critério de no mínimo 1 letra minúscula`() {
        assertFalse(validadorService.validaSenha("ABTP9!FOK"))
    }

    @Test
    fun `Não passa no critério de no mínimo 1 letra maiúscula`() {
        assertFalse(validadorService.validaSenha("abtp9!fok"))
    }

    @Test
    fun `Não passa no critério de no mínimo 1 caracter especial`() {
        assertFalse(validadorService.validaSenha("AbTp9Ifok"))
    }

    @Test
    fun `Não passa no critério de possuir caracteres repetidos`() {
        assertFalse(validadorService.validaSenha("AbTp9!fokk"))
    }

    @Test
    fun `Não passa no critério de possuir espaços em branco`() {
        assertAll(
                Executable { assertFalse(validadorService.validaSenha(" AbTp9!fok")) },
                Executable { assertFalse(validadorService.validaSenha("AbTp9 !fok")) },
                Executable { assertFalse(validadorService.validaSenha("AbTp9!fok ")) }
        )
    }

    @Test
    fun `Algumas senhas válidas`() {
        assertAll(
                Executable { assertTrue(validadorService.validaSenha("@aHc6-Zt+")) },
                Executable { assertTrue(validadorService.validaSenha("xaMs!1SAb")) },
                Executable { assertTrue(validadorService.validaSenha("QF7G#lKtP")) }
        )
    }
}