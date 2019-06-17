Esse app possui três telas: 

 - Lista de produtos
 - Lista de produtos do carrinho
 - Checkout

Há algumas camadas notáveis, da mais interna à mais externa:

 - **Model:** aqui cuidamos da regra de negócio do app: `Product`, `Discount` pertencem a essa camada.
 - **API:** controla o acesso ao servidor e tratamento de erros referentes à rede.
 - **Repository:** gerencia o local onde os produtos devem ser buscados - rede ou persistência interna.
 - **View Model:** representa o estado da view. Todos as computações e os dados necessários para uma view estão armazenados nesta camada.
 - **View:** responsável pela exibição e tratamento de eventos de tela.

A modelagem dos descontos é o típico exemplo do padrão _strategy_. Então, temos uma interface comum a todos que quiserem ser `Discount` e um local de registro para todos os `Discounts`. Dessa forma, a inserção de novos descontos é feita de forma simples criando uma classe que possui a nova regra, um teste de unidade e, logo em seguida, registrando-a em `Discounts`.
Neste ponto, poderia ser utilizado algum framework de injeção de dependência ou, de forma mais simplificada algum _service locator_. Para projetos mais elaborados, com grafo de dependências, essas opções devem ser consideradas.

Na camada de API foi criado um desacoplamento entre a representação de um modelo enviado pela rede e o modelo usado internamente pelo app. No modelo da rede temos um  acoplamento com a tecnologia usada para parsing (aqui foi usado o GSON juntamente com o Retrofit) facilmente visto pelas anotações `@SerializedName` do GSON. Já, no modelo interno temos POJOs que são criados por meio de `ProductConverter`. 

Na API também são tratados erros de conexão e falhas de request. No entanto, nem sempre esses erros devem ficar ocultos do usuário (alguns erros podem disparar alertas na tela do aparelho), então há um _delegate_ para outras camadas que saberão lidar com isso. Usando essa separação, as falhas capturadas na camada de API poderiam ser enviadas para algum _crash report_, por exemplo.

A camada `Repository` é o _delegate_ da API. Usando ideias de views reativas, esta camada cria um LiveData para expor os dados obtidos, ou melhor: um `StateLiveData`. O dado obtido é encapsulado juntamente com o seu estado (`LOADING`, `SUCCESS` e `FAILURE`) em um `Resource`. Dessa forma, quem estiver registrado nesse `StateLiveData` conhece mais informações sobre o andamento da sua solicitação ao repositório, melhor do que apenas saber se o dado existe ou não. Numa situação real, essa camada também conheceria camadas de persistência e chamaria o acesso à rede caso não encontrasse os dados internamente (ou qualquer outra regra de expiração de cache fosse satisfeita). Aqui, o Room seria bem utilizado.

Quem aciona a camada de `Repository` é o `ViewModel`. Como o app representa um fluxo de compra simplificado e as três telas pertencem ao mesmo contexto de uso, temos aqui um exemplo de um _shared view model_ representado pela classe `MainViewModel`. Novamente pensando em injeção de dependências, há uma `Factory` para a criação desse `MainViewModel` que sabe qual é o repositório a ser usado por esse view model.
Nesta classe, temos diversos métodos devolvendo um `LiveData`, justamente para que as views tenham a menor quantidade possível de código, pois dessa forma temos uma facilitação na testabilidade do aplicativo, já que o view model pode ser testado de forma unitária sem o uso de um ambiente instrumentado.

Por fim, as views são simplesmente observadoras do `MainViewModel` e possuem apenas código relacionado ao mundo do Android. Uma observação: apesar de temos uma única activity que gerencia dois fragments, o padrão _single activity_ não foi o foco aqui. Caso tivessemos mais telas ou fluxos no aplicativo, certamente teríamos outras activities com outros fragments.