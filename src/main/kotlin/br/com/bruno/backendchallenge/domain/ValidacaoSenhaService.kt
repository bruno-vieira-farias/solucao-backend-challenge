package br.com.bruno.backendchallenge.domain

interface ValidacaoSenhaService {

    /**
     *  Retorna 'true' apenas quando a [senha]:
     *      - Possuir nove ou mais caracteres e;
     *      - Possuir ao menos 1 dígito e;
     *      - Possuir ao menos 1 letra minúscula e;
     *      - Possuir ao menos 1 letra maiúscula e;
     *      - Possuir ao menos 1 caractere especial e;
     *      - Não possuir caracteres repetidos e;
     *      - Não possuir espaços em branco.
     */
    fun validaSenha(senha: String): Boolean
}