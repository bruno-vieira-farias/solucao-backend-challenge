package br.com.bruno.backendchallenge.endpoint.validacaoSenha


/**
 * Utilizado como objeto de entrada.
 */
data class EntradaDto(
        val senha: String
)

/**
 * Utilizado como objeto de sucesso, ou seja, quando idependente do retorno em [isSenhaValida] ser `true` ou `false`,
 *  o processamento não apresentou nenhum erro.
 */
data class RetornoSucessoDto(
        val isSenhaValida: Boolean
)

/**
 * Utilizado no retorno quando ocorre algum erro durante o processamento da requisição.
 */
data class RetornoErroDto(
        val mensagemErro: String
)
