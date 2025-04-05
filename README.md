## Seplag-MT
### Projeto prático para Seplag-MT

## Spring MVC Project
    - Java 17
    - Docker
    - PostgreSQL
    - Minio

### Endpoints
Endpoints disponíveis: 
    - v1/unidades
    - v1/lotacoes
    - v1/servidores-efetivos
    - v1/servidores-temporarios

    Com métodos para edição de registros:
    - FindAll
    - FindById
    - Save
    - Update
    - Delete

    - v1/unidades/busca/{pesNome}
    Sendo 'pesNome' uma string para buscar pelo nome do servidor

    - v1/servidores-efetivos/lotados/{unidId}
    Sendo 'unidId' um integer para buscar pelo id da unidade

### Segurança
Endpoints protegidos com segurança (autorização e autenticação).
Desabilitados no código para facilitar o acesso:
    - v1/unidades/busca/{pesNome}
    - v1/servidores-efetivos/lotados/{unidId}

### Características
    - Paginação para retorno de FindAll e para as buscas específicas com parâmetros
    - Mapstruct para DTOs na camada Controller
    - Global Exception Handler
    - Cobertura de testes unitários acima de 90% em Unidades, como exemplo

 ### Instruções
 Para rodar o projeto, rodar o Docker com o comando: 
    docker-compose up -d
    
 Para rodar a aplicação, clone este repositório e rode o comando:
    mvn spring-boot:run

 ### Outras informações
 Relação das entidades no banco de dados solicitado no projeto:
 ![image](https://github.com/user-attachments/assets/0f171e75-54b3-44db-be26-7bcd8dd18602)




