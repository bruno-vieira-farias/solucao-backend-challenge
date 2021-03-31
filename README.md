### Run
A aplicação pode ser executada pelo maven ou exeutando um container Docker.

#### Executando com o maven.
Com o jdk11 instalado no seu sistema operacional, execute o comando na raiz do projeto:  
* No Windows `$ mvnw spring-boot:run`  
* No Linux/Mac `$ ./mvnw spring-boot:run`
 
#### Executando com Docker.
Com o docker e docker-compose disponíveis em seu sistema, execute o comando `$ docker-compose up` na raiz do projeto.

### Documentação da API - Swagger
A documentação da API esta disponível no endpoint `/swagger-ui.html`.

### Um pouco sobre a solução.

#### Validadores em [StringUtils.kt](src/main/kotlin/br/com/bruno/backendchallenge/support/StringUtils.kt).
Foi utilizado o recurso [extensions](https://kotlinlang.org/docs/extensions.html) do kotlin sobre a classe `String` e com isso:
* Podemos chamar as funções a partir de qualquer `String` de forma simples e fluída. Por exemplo `senha.possuiEspacosEmBranco()`.
* Todas as extensões criadas extendem de `String` porque elas tem potencial de serem utilizadas em outros dominios, não apenas na validação da senha.
* O arquivo se encontra no pacote [support](src/main/kotlin/br/com/bruno/backendchallenge/support) justamente para não ficar associado apenas com o dominio de validação de senha.
* Algumas funções criadas, como a `string.possuiQuantidadeMinimaCaracteresEspeciais(quantidadeMinimaCaracteresEspeciais = x)`, possuem um parâmentro `quantidadeMinima...` que promovem reutilização e fácilidade de adaptação a novas demandas de negócio. 

#### Uso do arquivo de propriedades (`application.properties`).
* Permite o ajuste dos parâmetros de forma rápida e sem a necessidade de compilação do código fonte. 
* Remove do código os "números mágicos" (Valores literais "cravados" no código).
* Deixam os testes mais seguros, uma vez que estes parâmetros também são considerados neles.

#### Interface [ValidaçãoSenhaService](src/main/kotlin/br/com/bruno/backendchallenge/domain/ValidacaoSenhaService.kt).
É uma boa prática expor intefaces entre as camadas da aplicação porque deixa claro o contrato entre as camadas, no caso view e domain.  
Ela se encontra na raiz do pacote domain, já a implementação, esta disponível encontra em um pacote interno [validadoresSenha](src/main/kotlin/br/com/bruno/backendchallenge/domain/validadoresSenha).

#### Service [ValidaçãoSenhaServiceImp.](src/main/kotlin/br/com/bruno/backendchallenge/domain/validadoresSenha/ValidaçãoSenhaServiceImp.kt)
Aqui temos a implementação da interface citada acima. Nela foi injetada a classse [ValidacaoSenhaProperties.kt](src/main/kotlin/br/com/bruno/backendchallenge/domain/validadoresSenha/ValidacaoSenhaProperties.kt) que disponibiliza os valores parametrizados no arquivo de propriedade `application.properties`.  
O uso da função [require](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/require.html) deixa a leitura do código mais fluída.

### Rest Controller [ValidacaoSenhaController.kt](src/main/kotlin/br/com/bruno/backendchallenge/endpoint/validacaoSenha/ValidacaoSenhaController.kt)
O controller, foi implementado considerado o padrão REST entendendo que para API desenvolvida o metodo POST é o mais adequando.  
Também foi considerado que tanto a entrada quanto o retorno deveriam em serializados em JSON, isso porque é um padrão muito utilizado além de promover maior estabilidade entre os sistemas.
No retorno de erro, a stacktrace não é exposta por motivos de segurança, mas ela está sendo logada auxíliar na identificação de possiveis problemas em tempo de execução.