# apartamento
API para permitir CRUD de apartamentos

# Projeto Desenvolvedor Fullstack

## Sobre 
Criar uma API e uma interface que permita criar, consultar, atualizar e deletar apartamentos.

### Tecnologias

- Java;
- Spring Boot;
- Hibernate;
- PostgreSQL;
- Frontend: (e.g. React, AngularJS, Vue.js).

### Requisitos

A entidade `Apartamento` deve conter os atributos especificados na tabela abaixo:

| Atributo | Descricao                    |
|----------|--------------------------------|
| `id`     | Identificador unico da tabela. |
| `numero` | Numero do apartamento.         |
| `estado` | Estado do apartamento.         |

O numero e o estado do apartamento sao obrigatarios. O numero deve ser unico e o estado limitado aos seguintes
valores: **LIVRE** e **LOCADO**.

A API deve conter as seguintes rotas:

| Metodo   | Endpoint           | Descricao                                                   |
|----------|--------------------|-------------------------------------------------------------|
| `GET`    | `/api/apartamento` | Retorna todos os apartamentos.                              |
| `POST`   | `/api/apartamento` | Cria um apartamento com numero e o estado LIVRE por padrao. |
| `PUT`    | `/api/produto/:id` | Atualiza o numero e o estado de um apartamento.             |
| `DELETE` | `/api/produto/:id` | Deleta um apartamento.                                      |
