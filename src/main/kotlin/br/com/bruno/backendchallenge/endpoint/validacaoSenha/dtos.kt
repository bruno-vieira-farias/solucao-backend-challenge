package br.com.bruno.backendchallenge.endpoint.validacaoSenha


/**
 * Dto utilizado para no parametro de entrada do endpoint.
 */
data class EntradaDto(
        val senha: String
)

/**
 * Dto utilizado no retorno do endpoint.
 *
 *  O valor de [isSenhaValida] pode ser nulo quando ocorrer algum erro durante o processamento da validação.
 */
data class RetornoDto(
        val isSenhaValida: Boolean?,
        val possuiErro: Boolean
)