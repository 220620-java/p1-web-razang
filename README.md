# Project 1 Web App - Team Razang
A simple Java web application developed for Apache Tomcat v10.0. This application is for demonstrating basic CRUD operations using an in-house Object Relational Mapper (ORM).
## Description

Project 1 is part of a Revature curriculum to learn basic web application structure through various concepts and technologies such as REST APIs, Servlets, PostgreSQL, Amazon Web Services, Jackson, ORM, and Java. Our [Object Relational Mapper](https://github.com/220620-java/p1-orm-razang) is developed along side this web application and is part of the dependencies.

## Getting Started

### Dependencies

* Java 8 JDK
* [Razang ORM](https://github.com/220620-java/p1-orm-razang)
* Maven
* JUnit 5
* PostgreSQL
* Mockito
* Jackson
* Apache Tomcat v10.0

### Installing

* Compile and deploy the .war in Apache Tomcat
* Database credentials must be put in src/main/resources/ as database.properties with the following fields inside:
```
url=jdbc:postgresql://????:5432/postgres?currentSchema=myBank
usr=????
pwd=????
```

### Usage

* Make GET/POST requests to the endpoint razang-web
```
GET REQUEST: http://localhost:8080/razang-web/Greet
```

## Authors

Contact us for any support or questions:

* [Raza Ghulam](https://github.com/raza-bot)
* [Colby Tang](https://github.com/colbyktang/windtang) colby051@revature.net