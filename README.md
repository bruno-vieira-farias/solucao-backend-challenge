### Run
A aplicação pode ser executada pelo maven ou em um container Docker.

#### Executando com o maven.
Com o jdk11 instalado no seu sistema operacional, execute o comando na raiz do projeto:  
* No Windows `$ mvnw spring-boot:run`  
* No Linux/Mac `$ ./mvnw spring-boot:run`
 
#### Executando com Docker.
Com o docker e docker-compose disponíveis no seu sistema, execute o comando `$ docker-compose up` na raiz do projeto.

### Documentação da API - Swagger
A documentação da API esta disponível no endpoint `/swagger-ui/index.html`.

### Um pouco sobre a solução.

#### Validadores em [StringUtils.kt](src/main/kotlin/br/com/bruno/backendchallenge/support/StringUtils.kt).
Foi utilizado o recurso [extensions](https://kotlinlang.org/docs/extensions.html) do kotlin sobre a classe `String` e com isso:
* Podemos chamar as funções a partir de qualquer `String` de forma simples e fluída. Por exemplo `senha.possuiEspacosEmBranco()`.
* Todas as extensões criadas extendem de `String` porque elas teem o potencial de serem reutilizadas em outros dominios, não apenas na validação da senha.
* O arquivo se encontra no pacote [support](src/main/kotlin/br/com/bruno/backendchallenge/support) justamente para não ficar associado apenas com o dominio de validação de senha.
* Algumas funções criadas, como a `string.possuiQuantidadeMinimaCaracteresEspeciais(quantidadeMinimaCaracteresEspeciais = x)`, possuem um parâmentro `quantidadeMinima...` que promovem reutilização e facilidade de adaptação a novas demandas de negócio. 

#### Uso do arquivo de propriedades (`application.properties`).
* Permite o ajuste dos parâmetros de forma rápida e sem a necessidade de compilação do código fonte. 
* Remove do código os "números mágicos" (Valores literais "cravados" no código).
* Deixam os testes mais seguros, uma vez que estes parâmetros também são considerados neles.

#### Interface [ValidaçãoSenhaService](src/main/kotlin/br/com/bruno/backendchallenge/domain/ValidacaoSenhaService.kt).
* É uma boa prática expor intefaces entre as camadas da aplicação porque deixa claro o contrato entre elas.  
* Ela encontra-se na raiz do pacote domain, já sua implementação esta disponível num pacote interno [validadoresSenha](src/main/kotlin/br/com/bruno/backendchallenge/domain/validadoresSenha).

#### Service [ValidaçãoSenhaServiceImp.](src/main/kotlin/br/com/bruno/backendchallenge/domain/validadoresSenha/ValidaçãoSenhaServiceImp.kt)
* Aqui temos a implementação da interface citada acima. Nela foi injetada a classse [ValidacaoSenhaProperties.kt](src/main/kotlin/br/com/bruno/backendchallenge/domain/validadoresSenha/ValidacaoSenhaProperties.kt) que disponibiliza para a aplicacao os valores parametrizados no arquivo de propriedade `application.properties`.  
* O uso da função [require](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/require.html) deixa a leitura do código mais fluída.

### Rest Controller [ValidacaoSenhaController.kt](src/main/kotlin/br/com/bruno/backendchallenge/endpoint/validacaoSenha/ValidacaoSenhaController.kt)
* O controller foi implementado considerado o padrão REST, entendendo que para API desenvolvida o metodo POST é o mais adequado.  
* Também foi considerado que tanto a entrada quanto o retorno deveria ser serializado em JSON, isso porque além de ser um padrão muito comum, promove maior estabilidade e facilidade na definição dos contratos entre os sistemas.
* No retorno de erro, a stacktrace não é exposta por motivos de segurança, mas ela esta sendo logada auxíliando na identificação de possíveis problemas em tempo de execução.
