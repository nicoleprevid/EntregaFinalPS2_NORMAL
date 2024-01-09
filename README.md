# API de Comércio de Computadores

Este repositório contém uma API em Java para um site de comércio de computadores voltado para vendedores. Abaixo, uma breve explicação de cada componente do projeto:
A aplicação estará acessível em https://projeto-ps2.onrender.com/. Use: 
/computadores ;
/contasBancarias ; 
/produtos

## Estrutura do Projeto

1. **Controller:**
   - Diretório que contém os controladores da API. Responsável por receber as requisições HTTP, processar a lógica de negócios e retornar as respostas adequadas.

2. **Entities:**
   - Diretório que contém as entidades do domínio do sistema. No contexto deste projeto, temos as classes `Computador`, `ContaBancaria` e `Produto`.

3. **Repositories:**
   - Diretório destinado aos repositórios, que são responsáveis por realizar operações de persistência no banco de dados para as entidades.

4. **CORS:**
   - Configurações para Cross-Origin Resource Sharing (CORS), permitindo ou restringindo solicitações de diferentes origens.

5. **ProjetoApplication.java:**
   - Arquivo principal que contém o método `main` e inicia a aplicação Spring Boot.

6. **Resources:**
   - Diretório que pode conter arquivos de configuração, como arquivos de propriedades ou YAML.

7. **Dockerfile:**
   - Arquivo usado para construir uma imagem Docker do projeto. Facilita a implantação e execução da aplicação em contêineres.

8. **Conexão com jdbc:postgresql:**
   - A API utiliza uma conexão JDBC com um banco de dados PostgreSQL. As configurações de conexão devem estar presentes nos arquivos de propriedades ou configuração do Spring Boot.

## Funcionalidades Principais

SELECT, PUT, INSERT, DELETE - Nas tabelas:  Computadores, Produtos e ContaBancaria

## Execução com Docker

Para executar a aplicação usando Docker, siga os passos abaixo:

1. Construa a imagem Docker
2. Execute o contêiner
