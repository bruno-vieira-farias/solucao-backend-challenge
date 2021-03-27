package br.com.bruno.backendchallenge.domain.ValidadoresSenha

/**
 *  Retorna `true` quando a [String] possuir espaços em branco.
 */
fun String.possuiEspacosEmBranco() = this.any { it == ' ' }

/**
 *  Retorna `true` quando a [String] possuir caracteres repetidos.
 */
fun String.possuiCaracteresRepetidos(): Boolean {
    return this.groupBy { it }
            .any { it.value.count() > 1 }
}

/**
 * Retorna `true` quando a [String] possuir uma quantidade de caracteres especiais maior ou igual à
 * [quantidadeMinimaCaracteresEspeciais].
 *
 *  Lança um [IllegalArgumentException] caso a [quantidadeMinimaCaracteresEspeciais] seja negativa.
 */
fun String.possuiQuantidadeMinimaCaracteresEspeciais(quantidadeMinimaCaracteresEspeciais: Int) : Boolean {
    if (quantidadeMinimaCaracteresEspeciais < 0) {
        throw IllegalArgumentException()
    }
    val listaCaracteresEspeciais = listOf('!', '@', '#', '$', '%', '^', '&', '*', '(', ')', '-', '+')
    return this.filter { it in listaCaracteresEspeciais }.length >= quantidadeMinimaCaracteresEspeciais
}

/**
 * Retorna `true` quando a [String] possuir uma quantidade de caracteres maior ou igual à [quantidadeMinimaCaracteres].
 *
 * Lança um [IllegalArgumentException] caso a [quantidadeMinimaCaracteres] seja negativa.
 */
fun String.possuiQuantidadeMinimaCaracteres(quantidadeMinimaCaracteres: Int) : Boolean {
    if (quantidadeMinimaCaracteres < 0) {
        throw java.lang.IllegalArgumentException()
    }
    return this.length >= quantidadeMinimaCaracteres
}

/**
 * Retorna `true` quando a [String] possuir uma quantidade de caracteres maior ou igual à [quantidadeMinimaDigitos].
 *
 * Lança um [IllegalArgumentException] caso a [quantidadeMinimaDigitos] seja negativa.
 */
fun String.possuiQuantidadeMinimaDigitos(quantidadeMinimaDigitos: Int): Boolean {
    if (quantidadeMinimaDigitos < 0) {
        throw java.lang.IllegalArgumentException("")
    }
    return this.filter { it.isDigit() }.length >= quantidadeMinimaDigitos
}

/**
 * Retorna `true` quando a [String] possuir uma quantidade de letras maiúsculas maior ou igual à [quantidadeMinimaLetrasMaiusculas].
 *
 * Lança um [IllegalArgumentException] caso em [quantidadeMinimaLetrasMaiusculas] seja negativa.
 */
fun String.possuiQuantidadeMinimaLetrasMaiusculas(quantidadeMinimaLetrasMaiusculas: Int) : Boolean {
    if (quantidadeMinimaLetrasMaiusculas < 0) {
        throw java.lang.IllegalArgumentException()
    }
    return this.filter { it.isUpperCase() }.length >= quantidadeMinimaLetrasMaiusculas
}

/**
 * Retorna `true` quando a [String] possuir uma quantidade de letras minúsculas maior ou igual a [quantidadeMinimaLetrasMinusculas].
 *
 * Lança um [IllegalArgumentException] caso a [quantidadeMinimaLetrasMinusculas] seja negativa.
 */
fun String.possuiQuantidadeMinimaLetrasMinusculas(quantidadeMinimaLetrasMinusculas: Int) : Boolean {
    if (quantidadeMinimaLetrasMinusculas < 0) throw java.lang.IllegalArgumentException("")

    return this.filter { it.isLowerCase() }.length >= quantidadeMinimaLetrasMinusculas
}
