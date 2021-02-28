# API RESTful com SpringBoot, Kotlin e MongoDB

Foi realizado um projeto com finalidade de criar uma aplicação back-end para registro de ponto de horas. Contendo entidades de funcionario, empresa e lancamento

## Tecnologias utilizadas

```
API desenvolvida em Kotlin
Spring boot
Spring security basic authentication
Mongodb para persistencia dos dados
Testes unitários com JUnit e Mockito
docker para mongodb
```

## Instalacao
Faça o download do repositório 
```bash
git clone git@github.com:igantonio/ponto-eletronico-kotlin-udemy.git
```

Entre na pasta do projeto
```bash
cd ponto-eletronico-kotlin-udemy
```

Execute o comando para executar o banco mongodb
```bash
sudo docker-compose up
```

Execute o comando para executar a api
```bash
./gradlew bootrun
```

O json para importação das requests estão na raiz do projeto com nome (requests_api). Caso queira utilizar importe em sua ferramenta INSOMNIA, POSTMAN, etc.
Caso não queira utilizar as requests já prontas siga os comandos.
OBS. Sempre que for criar uma pessoa física é necessário ter o cnpj da empresa
```bash
CRIAR PESSOA JURIDICA(EMPRESA)
curl --request POST \
  --url http://localhost:8080/api/cadastrar-pj \
  --header 'Content-Type: application/json' \
  --cookie JSESSIONID=449397EC4B47D1CA55D8F04D6CE03C23 \
  --data '{
	"nome":"Admin",
	"email":"administrador@empresa.com",
	"senha":"123456",
	"cpf":"19902349846",
	"cnpj":"50018066000187",
	"razaoSocial":"Empresa"
}'
```

```bash
CRIAR PESSOA FISICA(FUNCIONÁRIO)
curl --request POST \
  --url http://localhost:8080/api/cadastrar-pf \
  --header 'Content-Type: application/json' \
  --cookie JSESSIONID=449397EC4B47D1CA55D8F04D6CE03C23 \
  --data '{
	"nome":"Funcionario",
	"email":"funcionario@dominio.com",
	"senha":"123456",
	"cpf":"30285642898",
	"cnpj":"10443887000146"
}'
```

Para criação dos lançamentos é necessário estar autenticado, ou seja, inserir authentication basic
AO executar a API foi gerado dois usuários. Um com perfil de USUÁRIO e outro ADMIN
```bash
PERFIL DE USUÁRIO
USERNAME: funcionario@empresa.com
PASSWORD: 1234

PERFIL DE ADMIN
USERNAME: admin@empresa.com
PASSWORD: 1234
```

Rota para criação de um lancamento
```bash
***Necessário informar o id do funcionario para criação
CRIAR LANCAMENTOS
curl --request POST \
  --url http://localhost:8080/api/lancamentos \
  --header 'Authorization: Basic ZnVuY2lvbmFyaW9AZW1wcmVzYS5jb206MTIzNA==' \
  --header 'Content-Type: application/json' \
  --cookie JSESSIONID=449397EC4B47D1CA55D8F04D6CE03C23 \
  --data '{
	"data":"2017-10-30 17:00:00",
	"tipo":"TERMINO_TRABALHO",
	"funcionarioId":"5fd02b249bbfa10a03bd71b3"
}'
```

