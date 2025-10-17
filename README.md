Projeto Biblioteca de Livros 

Sobre o projeto
Esse projeto foi feito para a matéria de desenvolvimento backend com Spring Boot.  
A ideia era criar um sistema simples de biblioteca, com cadastro de livros e suas publicações, e testar tudo pelo Postman.  
O banco usado foi o H2 (memória), e o projeto foi feito e rodado no Eclipse.

Estrutura do projeto

- Entidade Livro
  - id
  - título (obrigatório, até 40 caracteres)
  - quantidade de páginas (obrigatório)
  - publicação (outra classe)

- Entidade Publicacao
  - autor (obrigatório, até 25 caracteres)
  - dataPublicacao
  - editora

- Camadas criadas
  - `model` → onde ficam as classes Livro e Publicacao
  - `repository` → interface que conversa com o banco
  - `controller` → onde estão os endpoints (GET, POST, PUT, DELETE)
  - `exception` → tratamento de erros e mensagens personalizadas

Dependências usadas
No arquivo `pom.xml` foram adicionadas:

- spring-boot-starter-data-jpa  
- spring-boot-starter-web  
- spring-boot-devtools  
- h2  
- spring-boot-starter-validation  

Essas bibliotecas são o que permitem rodar o servidor, salvar dados e validar os campos obrigatórios.


application.properties

Configuração para o banco H2 funcionar direitinho:

properties
spring.datasource.url=jdbc:h2:mem:livrosdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console
spring.jpa.hibernate.ddl-auto=update

 Como acessar o banco H2

1. Com o projeto rodando no Eclipse, abra o navegador e digite:
http://localhost:8080/h2-console

2. Vai abrir a tela de login do H2.

3. Preencha assim:
- **JDBC URL:** `jdbc:h2:mem:livrosdb`
- **User Name:** `sa`
- **Password:** (deixe em branco)
- Clique em **Connect**

4. Pronto! Você vai ver as tabelas criadas automaticamente (`livro` e `publicacao`).

Como testar o projeto no Postman

O Postman é uma ferramenta usada para testar as requisições da API (GET, POST, PUT, DELETE) e ver se o backend está funcionando direitinho.  
Abaixo estão os passos completos de como usar ele com o projeto da Biblioteca.

Passo a passo

1. Abra o Postman (pode ser o aplicativo ou versão web).
2. Clique em New → Collection e dê o nome de `Biblioteca`.
3. Dentro dessa collection, você vai criar uma requisição para cada tipo de operação.
4. Lembre-se de deixar o projeto rodando no Eclipse (precisa aparecer a mensagem `Started LivroApplication` no console).
5. O servidor roda por padrão em: http://localhost:8080


**Body → selecione "raw" e escolha "JSON" → cole o conteúdo abaixo:**

```json
{
  "titulo": "Harry Potter e a Pedra Filosofal",
  "qtdPaginas": 264,
  "publicacao": {
    "autor": "J.K. Rowling",
    "dataPublicacao": "1997-06-26",
    "editora": "Rocco"
  }
}

GET http://localhost:8080/livros

O que acontece:
Mostra uma lista de todos os livros cadastrados no sistema.
[
  {
    "id": 1,
    "titulo": "Harry Potter e a Pedra Filosofal",
    "qtdPaginas": 264,
    "publicacao": {
      "autor": "J.K. Rowling",
      "dataPublicacao": "1997-06-26",
      "editora": "Rocco"
    }
  }
]

GET http://localhost:8080/livros/1
O que acontece:
Mostra apenas o livro com o ID escolhido (no exemplo, o ID 1).

{
  "id": 1,
  "titulo": "Harry Potter e a Pedra Filosofal",
  "qtdPaginas": 264,
  "publicacao": {
    "autor": "J.K. Rowling",
    "dataPublicacao": "1997-06-26",
    "editora": "Rocco"
  }
}
PUT http://localhost:8080/livros/1
O que acontece:
Atualiza as informações do livro com o ID informado (nesse caso, o ID 1)
Body → raw → JSON:
{
  "titulo": "Harry Potter e a Câmara Secreta",
  "qtdPaginas": 320,
  "publicacao": {
    "autor": "J.K. Rowling",
    "dataPublicacao": "1998-07-02",
    "editora": "Rocco"
  }
}

Exemplo de resposta:
{
  "id": 1,
  "titulo": "Harry Potter e a Câmara Secreta",
  "qtdPaginas": 320,
  "publicacao": {
    "autor": "J.K. Rowling",
    "dataPublicacao": "1998-07-02",
    "editora": "Rocco"
  }
}

DELETE http://localhost:8080/livros/1
O que acontece:
Remove o livro com o ID informado do banco de dados.
Status: 204 No Content

Tratamento de erros

Caso algum campo obrigatório não seja preenchido (como título ou autor), o sistema devolve uma mensagem de erro com status 400:

Exemplo:
{
  "status": 400,
  "mensagem": "O campo título é obrigatório"
}
Essas validações são tratadas automaticamente pela classe ControllerExceptionHandler do projeto.

Sempre envie o body como JSON no Postman.

Se reiniciar o servidor, o banco H2 é apagado (porque ele fica na memória), então será preciso recriar os livros pelo POST.

Você pode testar vários livros da saga Harry Potter para completar o banco.

Acabou rs.



