package br.com.bruno.backendchallenge.endpoint.validacaoSenha

data class EntradaDto(
        val senha: String
)

data class RetornoSucessoDto(
        val isSenhaValida: Boolean
)

data class RetornoErroDto(
        val mensagem: String
)
