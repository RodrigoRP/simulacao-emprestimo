# simulacao-emprestimo


### Technology Stack
Component         | Technology
---               | ---
Frontend          | [Angular 8](https://github.com/angular/angular)
Backend (REST)    | [SpringBoot](https://projects.spring.io/spring-boot) (Java)
REST Documentation| [Swagger UI / Springfox](https://github.com/springfox/springfox) and [ReDoc](https://github.com/Rebilly/ReDoc)
In Memory DB      | H2 
Persistence       | JPA (Using Spring Data)
Client Build Tools| [angular-cli](https://github.com/angular/angular-cli), Webpack, npm
Server Build Tools| Maven(Java) 



## Prerequisites
Ensure you have this installed before proceeding further
- Java 8
- Maven 3.3.9+ or Gradle 3.3+
- Node 6.0 or above,  
- npm 5 or above,   
- Angular-cli 1.6.3



### Accessing Application
Cpmponent         | URL                                      | Credentials
---               | ---                                      | ---
Frontend          |  http://localhost:4200                   | 
H2 Database       |  http://localhost:8080/h2-console        |  Driver:`org.h2.Driver` <br/> JDBC URL:`jdbc:h2:mem:demo` <br/> User Name:`sa`
Swagger (API Ref) |  http://localhost:8080/swagger-ui.html   | 
Swagger Spec      |  http://localhost:8080/api-docs          |




### Screenshots
---
#### Lista de clientes
![Dashboard](https://raw.githubusercontent.com/RodrigoRP/simulacao-emprestimo/master/screenshots/1.PNG)
---
#### Adicionar cliente
![Dashboard](https://raw.githubusercontent.com/RodrigoRP/simulacao-emprestimo/master/screenshots/2.PNG)
---
#### Simular empréstimo
![Dashboard](https://raw.githubusercontent.com/RodrigoRP/simulacao-emprestimo/master/screenshots/3.PNG)
---
#### Lista dos simulados
![Dashboard](https://raw.githubusercontent.com/RodrigoRP/simulacao-emprestimo/master/screenshots/4.PNG)
---




