package br.com.bruno.backendchallenge.domain

interface ValidacaoSenhaService {

    /**
     *  Valida retornado 'true' apenas quando a [senha]:
     *      - Possuir nove ou mais caracteres.
     *      - Possuir ao menos 1 dígito.
     *      - Possuir ao menos 1 letra minúscula.
     *      - Possuir ao menos 1 letra maiúscula.
     *      - Possuir ao menos 1 caractere especial. ( !@#$%^&*()-+ )
     *      - Não possuir caracteres repetidos.
     *      - Não possuir espaços em branco.
     */
    fun validaSenha(senha: String): Boolean
}