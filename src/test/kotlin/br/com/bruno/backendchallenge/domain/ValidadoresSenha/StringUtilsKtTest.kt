package br.com.bruno.backendchallenge.domain.ValidadoresSenha

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.function.Executable

internal class StringUtilsKtTest{

    @Test
    fun `String POSSUI caracteres repetidos portanto DEVE retornar TRUE`() {
        assertAll(
                Executable { assertTrue("aa".possuiCaracteresRepetidos()) },
                Executable { assertTrue("abcdeff".possuiCaracteresRepetidos()) },
                Executable { assertTrue("  ".possuiCaracteresRepetidos()) }
        )
    }

    @Test
    fun `String NAO POSSUI caracteres repetidos portanto DEVE retornar FALSE`() {
        assertAll(
                Executable { assertFalse("abc".possuiCaracteresRepetidos()) },
                Executable { assertFalse("123".possuiCaracteresRepetidos()) }
        )
    }

    @Test
    fun `String POSSUI a quantidade mínima de caracteres especiais portanto DEVE retornar TRUE`() {
        assertAll(
                Executable { assertTrue("".possuiQuantidadeMinimaCaracteresEspeciais(quantidadeMinimaCaracteresEspeciais = 0)) },
                Executable { assertTrue("1@".possuiQuantidadeMinimaCaracteresEspeciais(quantidadeMinimaCaracteresEspeciais = 1)) },
                Executable { assertTrue("12!(".possuiQuantidadeMinimaCaracteresEspeciais(quantidadeMinimaCaracteresEspeciais = 2)) }
        )
    }

    @Test
    fun `String NAO POSSUI a quantidade mínima de caracteres especiais portanto DEVE retornar FALSE`() {
        assertAll(
                Executable { assertFalse("a".possuiQuantidadeMinimaCaracteresEspeciais(quantidadeMinimaCaracteresEspeciais = 1)) },
                Executable { assertFalse("1@".possuiQuantidadeMinimaCaracteresEspeciais(quantidadeMinimaCaracteresEspeciais = 2)) },
                Executable { assertFalse("12!(".possuiQuantidadeMinimaCaracteresEspeciais(quantidadeMinimaCaracteresEspeciais = 5)) }
        )
    }

    @Test
    fun `String passando parametro NEGATIVO na funcao possuiQuantidadeMinimaCaracteresEspeciais DEVE LANÇAR uma IllegalArgumentException`(){
        assertThrows(IllegalArgumentException::class.java) {
            "qualquer texto".possuiQuantidadeMinimaCaracteresEspeciais(-1)
            "qualquer texto".possuiQuantidadeMinimaCaracteresEspeciais(-2)
            "qualquer texto".possuiQuantidadeMinimaCaracteresEspeciais(-10)
        }
    }

    @Test
    fun `String POSSUI espaços em branco portanto DEVE retornar TRUE`() {
        assertAll(
                Executable { assertTrue(" aa".possuiEspacosEmBranco()) },
                Executable { assertTrue("a a".possuiEspacosEmBranco()) },
                Executable { assertTrue("aa ".possuiEspacosEmBranco()) },
                Executable { assertTrue(" ".possuiEspacosEmBranco()) }
        )
    }

    @Test
    fun `String NAO POSSUI espaços em branco portanto DEVE retornar FALSE`() {
        assertAll(
                Executable { assertFalse("aa".possuiEspacosEmBranco()) },
                Executable { assertFalse("a#$#a".possuiEspacosEmBranco()) },
                Executable { assertFalse("".possuiEspacosEmBranco()) }
        )
    }

    @Test
    fun `String POSSUI a quantidade mínima de caracteres DEVE retornar TRUE`(){
        assertAll(
                Executable { assertTrue("".possuiQuantidadeMinimaCaracteres(quantidadeMinimaCaracteres = 0)) },
                Executable { assertTrue("1".possuiQuantidadeMinimaCaracteres(quantidadeMinimaCaracteres = 1)) },
                Executable { assertTrue("12".possuiQuantidadeMinimaCaracteres(quantidadeMinimaCaracteres = 2)) },
                Executable { assertTrue("12".possuiQuantidadeMinimaCaracteres(quantidadeMinimaCaracteres = 1)) },
                Executable { assertTrue("123".possuiQuantidadeMinimaCaracteres(quantidadeMinimaCaracteres = 2)) }
        )
    }

    @Test
    fun `String NAO POSSUI quantidade mínima de caracteres DEVE retornar FALSE`(){
        assertAll(
                Executable { assertFalse("12345".possuiQuantidadeMinimaCaracteres(quantidadeMinimaCaracteres = 6)) },
                Executable { assertFalse("".possuiQuantidadeMinimaCaracteres(quantidadeMinimaCaracteres = 6)) },
                Executable { assertFalse("12345678".possuiQuantidadeMinimaCaracteres(quantidadeMinimaCaracteres = 9)) }
        )
    }

    @Test
    fun `String qualquer chamando a funcao possuiQuantidadeMinimaCaracteres com parametro negativo deve lançar uma IllegalArgumentException`(){
        assertThrows(IllegalArgumentException::class.java) {
            "qualquer texto".possuiQuantidadeMinimaCaracteres(quantidadeMinimaCaracteres=-1)
            "qualquer texto".possuiQuantidadeMinimaCaracteres(quantidadeMinimaCaracteres=-10)
        }
    }

    @Test
    fun `String NAO POSSUI a quantidade minima de digitos DEVE retornar FALSE`(){
        assertAll(
                Executable { assertFalse("aaa".possuiQuantidadeMinimaDigitos( quantidadeMinimaDigitos = 1))},
                Executable { assertFalse("1aa".possuiQuantidadeMinimaDigitos( quantidadeMinimaDigitos = 2))},
                Executable { assertFalse("12a".possuiQuantidadeMinimaDigitos( quantidadeMinimaDigitos = 3))},
        )
    }

    @Test
    fun `String POSSUI a quantidade minima de digitos DEVE retornar TRUE`(){
        assertAll(
                Executable { assertTrue("aaa".possuiQuantidadeMinimaDigitos( quantidadeMinimaDigitos = 0))},
                Executable { assertTrue("1aa".possuiQuantidadeMinimaDigitos( quantidadeMinimaDigitos = 1))},
                Executable { assertTrue("12a".possuiQuantidadeMinimaDigitos( quantidadeMinimaDigitos = 2))},
                Executable { assertTrue("12345a".possuiQuantidadeMinimaDigitos( quantidadeMinimaDigitos = 2))}
        )
    }

    @Test
    fun `String passando parametro NEGATIVO para a funcao possuiQuantidadeMinimaDigitos() DEVE LANÇAR uma IllegalArgumentException`(){
        assertThrows(IllegalArgumentException::class.java) {
            "qualquer text0".possuiQuantidadeMinimaDigitos(quantidadeMinimaDigitos = -1)
            "qualquer text0".possuiQuantidadeMinimaDigitos(quantidadeMinimaDigitos = -2)
            "qualquer text0".possuiQuantidadeMinimaDigitos(quantidadeMinimaDigitos = -20)
        }
    }

    @Test
    fun `String POSSUI a quantidade mínima de letras maiusculas DEVE retornar TRUE`(){
        assertAll(
                Executable { assertTrue("aaA".possuiQuantidadeMinimaLetrasMaiusculas( quantidadeMinimaLetrasMaiusculas = 1))},
                Executable { assertTrue("!@#%^&*()-+A".possuiQuantidadeMinimaLetrasMaiusculas( quantidadeMinimaLetrasMaiusculas = 1))},
                Executable { assertTrue("".possuiQuantidadeMinimaLetrasMaiusculas( quantidadeMinimaLetrasMaiusculas = 0))},
                Executable { assertTrue("BCasdegtdsaer".possuiQuantidadeMinimaLetrasMaiusculas( quantidadeMinimaLetrasMaiusculas = 2))},
        )
    }

    @Test
    fun `String NAO POSSUI a quantidade minima de letras maiusculas DEVE retornar FALSE`(){
        assertAll(
                Executable { assertFalse("aaa".possuiQuantidadeMinimaLetrasMaiusculas( quantidadeMinimaLetrasMaiusculas = 1))},
                Executable { assertFalse("111".possuiQuantidadeMinimaLetrasMaiusculas( quantidadeMinimaLetrasMaiusculas = 1))},
                Executable { assertFalse("!@#%^&*()-+".possuiQuantidadeMinimaLetrasMaiusculas( quantidadeMinimaLetrasMaiusculas = 1))},
                Executable { assertFalse("Aaa".possuiQuantidadeMinimaLetrasMaiusculas( quantidadeMinimaLetrasMaiusculas = 2))},
        )
    }

    @Test
    fun `String passando parametro NEGATIVO para funcao validaQuantidadeMinimaLetrasMaiusculas() DEVE LANÇAR uma IllegalArgumentException`(){
        assertThrows(IllegalArgumentException::class.java) {
            "qualquer texto".possuiQuantidadeMinimaLetrasMaiusculas(quantidadeMinimaLetrasMaiusculas =-1)
            "qualquer texto".possuiQuantidadeMinimaLetrasMaiusculas(quantidadeMinimaLetrasMaiusculas =-2)
            "qualquer texto".possuiQuantidadeMinimaLetrasMaiusculas(quantidadeMinimaLetrasMaiusculas =-10)
        }
    }

    @Test
    fun `String POSSUI a quantidade minima de letras minúsculas DEVE retornar TRUE`(){
        assertAll(
                Executable { assertTrue("aAA".possuiQuantidadeMinimaLetrasMinusculas(quantidadeMinimaLetrasMinusculas = 1)) },
                Executable { assertTrue("!@#%^&*()-+a".possuiQuantidadeMinimaLetrasMinusculas(quantidadeMinimaLetrasMinusculas = 1)) },
                Executable { assertTrue("".possuiQuantidadeMinimaLetrasMinusculas(quantidadeMinimaLetrasMinusculas = 0)) }
        )
    }

    @Test
    fun `String NAO POSSUI a quantidade minima de letras minusculas DEVE retornar FALSE`(){
        assertAll(
                Executable { assertFalse("AAA".possuiQuantidadeMinimaLetrasMinusculas(quantidadeMinimaLetrasMinusculas = 1)) },
                Executable { assertFalse("111".possuiQuantidadeMinimaLetrasMinusculas(quantidadeMinimaLetrasMinusculas = 1)) },
                Executable { assertFalse("!@#%^&*()-+".possuiQuantidadeMinimaLetrasMinusculas(quantidadeMinimaLetrasMinusculas = 1)) }
        )
    }

    @Test
    fun `String passando um parametro negativo para a funcao possuiQuantidadeMinimaLetrasMinusculas() DEVE LANCAR uma IllegalArgumentException`(){
        assertThrows(IllegalArgumentException::class.java) {
            "qualquer texto".possuiQuantidadeMinimaLetrasMinusculas(quantidadeMinimaLetrasMinusculas = -1)
            "qualquer texto".possuiQuantidadeMinimaLetrasMinusculas(quantidadeMinimaLetrasMinusculas = -2)
            "qualquer texto".possuiQuantidadeMinimaLetrasMinusculas(quantidadeMinimaLetrasMinusculas = -10)
        }
    }
}