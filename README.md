## Run
Para executar a aplicação rode o comando `$ mvn spring-boot:run` no diretorio raiz do projeto.

#### Sobre os validadores disponíveis em [StringUtils.kt](src/main/kotlin/br/com/bruno/backendchallenge/support/StringUtils.kt).
Foi utilizado o recurso [extensions](https://kotlinlang.org/docs/extensions.html) do kotlin que extende funcionalidades sobre a classe `String`, com isso:
- Podemos chamar as funções a partir de qualquer `String` de forma simples e fluída. Como por exemplo `senha.possuiEspacosEmBranco()`.
- Todas as extensions criadas extendem de `String` e estão em um subdiretório [support](src/main/kotlin/br/com/bruno/backendchallenge/support) porque existe um grande potencial de reaproveitamento em outros pontos do sistema.
- Algumas funções criadas, como a `string.possuiQuantidadeMinimaCaracteresEspeciais(quantidadeMinimaCaracteresEspeciais = x)` possuem um paramentro de `quantidadeMinima...` que promove:
  - Uma alteração suave e segura caso exista alguma alteração de regras de negócio.
  - A não necessidade de alteração de código fonte de produção e de testes.
  - Combinado com a funcionalidade de obter estes parametros de um arquivo de configurações `application.properties`, não é sequer necessário recompilar a aplicação.    

#### Uso de parâmetros com o arquivo application.properties.
- Remove do código os "números mágicos" .
- Facilita o atendimento ágil a demandas de negócio pois o código sequer precisa ser recompilado.

#### Interface [ValidaçãoSenhaService](src/main/kotlin/br/com/bruno/backendchallenge/domain/ValidacaoSenhaService.kt)
Foi exposta interface para deixar claro o contrato entre as camadas view e domain, fazendo com que as dependencias sejam sobre abstrações.

### Service [ValidaçãoSenhaServiceImp.](src/main/kotlin/br/com/bruno/backendchallenge/domain/validadoresSenha/ValidaçãoSenhaServiceImp.kt)
Neste serviço, que é a implentação da interface acima temos:
    - A injeção da classe [ValidacaoSenhaProperties.kt](src/main/kotlin/br/com/bruno/backendchallenge/domain/validadoresSenha/ValidacaoSenhaProperties.kt) que disponibiliza os parametros informados no arquivo `application.properties`
    - O uso da função `require` que deixa a leitura do código mais fluída.

### Rest Controller [ValidacaoSenhaController.kt](src/main/kotlin/br/com/bruno/backendchallenge/endpoint/validacaoSenha/ValidacaoSenhaController.kt)
- Utilizando o padrao REST entendo que o metodo http post é o mais adequando para este processamento.
- Foi disponiblizado tanto o corpo da entrada como o retorno um objeto no formato JSON, é um padrão muito utilizado em desenvolvimento de APIs que além de ser simples deixa os contratos mais fortes e mais confiáveis.
- Optei por não expor a stacktrace de um possível erro por motivos de segurança, mas não abri mão de logar o resultado para o auxílio na identificação de possiveis problemas em tempo de execução.