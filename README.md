### Run
A aplicação pode ser executada diretamente pelo jdk java ou ainda utilizando container Docker.

#### Executando com o maven e jdk Java.
Com o jdk11 instalado em seu sistema operacional, execute o comando na raiz do projeto:  
* No Windows `$ mvnw spring-boot:run`  
* No Linux/Mac `$ ./mvnw spring-boot:run`
 
#### Executando com Docker.
Com o docker e docker-compose disponíveis em seu sistema, execute o comando `$ docker-compose up` na raiz do projeto.

##Um pouco sobre a solução.

#### Validadores disponíveis em [StringUtils.kt](src/main/kotlin/br/com/bruno/backendchallenge/support/StringUtils.kt).
Foi utilizado o recurso [extensions](https://kotlinlang.org/docs/extensions.html) do kotlin sobre a classe `String`, com isso:
* Podemos chamar as funções a partir de qualquer `String` de forma simples e fluída. Por exemplo `senha.possuiEspacosEmBranco()`.
* Todas as extensões criadas extendem de `String` pois elas tem potencial de serem usadas em outros contextos, não apenas na validação da senha.
* O arquivo se encontra no pacote [support](src/main/kotlin/br/com/bruno/backendchallenge/support) porque existe um grande potencial de reaproveitamento em outros possíveis dominios do sistema.
* Algumas funções criadas, como a `string.possuiQuantidadeMinimaCaracteresEspeciais(quantidadeMinimaCaracteresEspeciais = x)`, possuem um paramentro de `quantidadeMinima...` que promove a reutilização e a fácil adaptação a nova demandas do negócio. 

#### Uso do arquivo de propriedades application.properties.
* Permite o ajuste rápido da aplicação, com segurança, e sem a necessidade de compilação. 
* Remove do código os "números mágicos" (Valores literais "cravados" no código).
* Deixa os testes mais seguros, uma vez que estes parâmetros também são considerados nos testes.

#### Interface [ValidaçãoSenhaService](src/main/kotlin/br/com/bruno/backendchallenge/domain/ValidacaoSenhaService.kt)
É uma boa prática expor intefaces entre as camadas da aplicação. Essa foi a motivação deixando claro o contrato entre as camadas view e domain.  
Também não por acaso ela esta na raiz do pacote domain, já a implementação dela se encontra em um pacote interno.

#### Service [ValidaçãoSenhaServiceImp.](src/main/kotlin/br/com/bruno/backendchallenge/domain/validadoresSenha/ValidaçãoSenhaServiceImp.kt)
Aqui temos a implementação interface citada acima.  
Foi injetada a classse [ValidacaoSenhaProperties.kt](src/main/kotlin/br/com/bruno/backendchallenge/domain/validadoresSenha/ValidacaoSenhaProperties.kt) que disponibiliza os valores do arquivo de propriedade `application.properties`.  
O uso da função [require](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/require.html) deixa a leitura do código mais fluída nestes caso de validação.

### Rest Controller [ValidacaoSenhaController.kt](src/main/kotlin/br/com/bruno/backendchallenge/endpoint/validacaoSenha/ValidacaoSenhaController.kt)
O nosso controller, porta de entrada das mossas requisições http, foi implementado considerado o padrão REST.  
O entendimento é que o metodo POST é o mais adequando para este processamento.
Também considerei que tanto o tipo de entrada a entrada como o retorno seria em serializado em JSON por ser um padrão muito utilizado além de promover maior estabilidade para as aplicações que interagirem com esta API.
No retorno de erro, pptei por não expor a stacktrace por motivos de segurança, mas não abri mão de logar o resultado para o auxílio na identificação de possiveis problemas em tempo de execução.