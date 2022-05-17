# The Community Board Project

## Authorship:

1. author: dnglokpor
2. date: may 2022

## Project Summary:

The Community Board Project consists of a Java Spring Boot backend server and an Android Social Application that communicates with that server. The Backend server is configurated to accept and respond to GraphQL queries, with the extra ability to address a MySQL database. The Community Board Application uses Apollo GraphQL to replicate the server GraphQL schema and link to the server. **This project is currently in development.**

## Project details:

### Java Spring Boot Server [link]:

This server was instantiated via **Java Spring GraphQL Starter** application. It deploys a Tomcat web server that listens to a configurated address and port. It uses the hibernate Java framework to link to a MySQL database at runtime, as well as the JPA framework for processing Java objects into GraphQL objects.

### The Community Board App [link]:

Created to run on Android 23+ as a Fragment Application written in **Kotlin**. This application allows users to register to post notes on boards geolocated at specific locations. The application then connects to the Spring Tomcat server and stores all the user informations on the database.

## Cloning:

Cloning this repos will give you access to most of the required files for running/modifying both projects. The Community Board App can just be opened as a project from **Android Studio 22+**. The Java Spring Server is not specifically created for any IDE, but can easily be ran by opening *"JavaSpringBootServer/graphql-mysql-server"* as a folder in **vs code**. You will need to provide the missing *"JavaSpringBootServer/graphql-mysql-server/src/main/resources/application.properties"* file for it to work. Following is a template for it. Parts marked with **{xxxx-xxxx}** should be replaced by actual values:

```
# mysql
spring.datasource.url=jdbc:mysql://{database-address}/{database-name}?allowPublicKeyRetrieval=true&useSSL={default-is-fault}
spring.datasource.username={database-accessor-account-username}
spring.datasource.password={database-accessor-account-password}
spring.jpa.hibernate.ddl-auto=update
#spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL5InnoDBDialect
# graphql
graphql.servlet.mapping: /thecommunityboard
logging.level.org.springframework.web.filter.CommonsRequestLoggingFilter=DEBUG

```

[//]: # (links)