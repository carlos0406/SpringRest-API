# API para cadastro e manuseio de Tarefas

## Funcionalidades
###
    As funcionalidades do projeto

* Criar usuario
* Autenticacao
* Criar tarefa
* Alterar tarefa
* Deletar Tarefa
* Listar e filtrar tarefas

#
    O projeto foi feito usando springRest com persistencia JPA usando banco de dados postgres
    durante esse arquivo veremos como rodar localmente o projeto e tambem teremos acesso a documentacao do projeto,onde entederemos as rotas, para o teste de rotas recomendo o uso do insominia ou postman os endpoints nescessarios tambem estarao disponiveis ao longo deste arquivo
### A- Instalação

Primeiro precisamos baixar e instalar o [JDK(Java Development Kit)](https://www.oracle.com/br/java/technologies/javase/javase-jdk8-downloads.html)

depois devemos baixar o [git ](https://git-scm.com/)para poder baixar o projeto

Como SGBD usaremos o [Postgresql](https://www.postgresql.org/download/)

e por ultimo o [STS (Spring tools suite)](https://spring.io/tools) onde executaremos e tambem poderão editar o projeto quando nescessario

### B - downloado do projeto e configuração

    1- Depois da instalação das ferramentas acima siga o passo a passo a baixo.
    Para fazer o donwload do projeto primeiro vá até o diretorio que deseja que o projeto fique e execute o comando 'git clone https://github.com/carlos0406/SpringRest-API.git' dentro do seu terminal

![Imgur](https://i.imgur.com/Ctn8wfX.png)

####

    2- Depois abra o seu STS e siga o passo a passo das imagens

Clique em file e depois import
![Imgur](https://i.imgur.com/C3Xen7U.png)

Nas opções de import va ate maven e clique em em Existing maven projects

![Imgur](https://i.imgur.com/LqeE3WH.png)

Em seguida clique em browse e escolha o diretorio do seu projeto
![Imgur](https://i.imgur.com/Yme3vvF.png)

Em seguida clique em Finish.

agora vá até a classe JWTutils no diretorio SpringRest-API\src\main\java\br\com\carlos
e altere as linhas 22 e 23 de "@Value("${SECRET_KEY}")private String secretKey" para  "private String secretKey="1234""; ( o valor 1234 pode ser subistuido por qualquer valor de sua preferência, para fazer o deploy dessa aplicação é recomentavel a criação de uma variavel de configuração com o nome SECRET_KEY e deixar as linhas 22 e 23 como antes)
![Imgur](https://i.imgur.com/8c7X6nW.png)
Agora você deve ir no arquivo application.properties no diretorio SpringRest-API\src\main\resources
e substituir a primeira linha com o código "spring.datasource.url=${DATABASE_URL}" para

"spring.datasource.url=jdbc:postgresql://localhost:5432/nome_do_seu_banco_de_dados?createDatabaseIfNotExist=true&useTimezone=true&serverTimezone=UTC
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha"
![Imgur](https://i.imgur.com/kK4MeOi.png)

Depois disso seu projeto já está pronto para rodar no STS e para isso acontecer basta clicar no nome do projeto no boot Dashboard e em seguida clicar no primeiro botão do menu do boot dashboard para iniciar o projeto
![Imgur](https://i.imgur.com/Qkwyty3.png)

####
      3- Possiveis erros
      Caso o banco de dados não esteja disponivel recomendo que abra o pgadmin que vem junto da instalação do do postgresql e crie um banco de dados com o nome que voce colocou na configuracao spring.datasource.url, caso o problema seja relacionado na porta do servidor no mesmo arquivo que configurou o banco de dados coloque a seguinte propiedade :server.port=* valor da porta que deseja colocar*

## Dados sobre o projeto

####
      A documentação da api, onde explicamos as rotas esta disponivel em :
      
[documentação](https://projeto-api-tarefas.herokuapp.com/swagger-ui/index.html#/usuarios-controller)

 Assim como as rotas estão disponiveis publicamente em:
      
[rotas](hhttps://projeto-api-tarefas.herokuapp.com/)

####
    na documentação voce tera uma melhor noção de como as rotas funcionam é preciso lembrar que a autenticacao e feita atraves de um JWT que so e possivel obter depois de criar um usuario e usar a rota de autenticacao para receber o token

# Exemplos dos endpoints
    Criacao de usuario
![Imgur](https://i.imgur.com/0SUz48L.png)

    Autenticacao
![Imgur](https://i.imgur.com/YiHDuxv.png)
    
    Criacao de tarefa

![Imgur](https://i.imgur.com/5Ex3fhO.png)

    Listagem de tarefas

![Imgur](https://i.imgur.com/MhIJjh2.png)
    
    Alem desses todos os outros enpoints estao disponiveis na documentacao do projeto 
