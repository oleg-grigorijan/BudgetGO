# BudgetGO REST Server
**BudgetGO** is an application for personal and shared budget control. It stores information about users incomes and outcomes. BudgetGO REST
 Server provides an API for accessing all application features.

Table of contents:
* [Features](#features)
* [Requirements](#requirements)
* [Building](#building)
* [Before running](#before-running)
* [Running](#running)
* [Consuming](#consuming)
* [API](#api)

## Features
* [x] **Users** registration and authentication
* [x] **Currencies**
    * _Please note that only a user with `ROLE_ADMIN` authority can edit the list of currencies. So far, such a user can only be created manually from
     the database in `users` table by setting `is_admin` attribute to `true`_ 
* [x] **Operations** — incomes and outcomes added by the user
* [x] **Storages** — collection of operations. It can be an abstraction of personal card or cash, bank account, list of debts, family budget, etc. Each
 storage is associated with its own currency
    * [x] Automatic storage balance update 
    * [x] Sharing storages among several users (sending invitations)
    * [x] User roles for storages
        * Viewer: viewing storage and operations
        * Editor: viewer's authorities; editing storage; adding, editing and removing operations
        * Admin: editor's authorities; inviting, removing users, editing their roles
        * Creator: admin's authorities; can't be modified by admin
* [x] Operations are divided into **categories**
    * [x] Creating personal collection of categories that user wants to use
    * [x] Specifying user's categories for using only with incomes or only with outcomes 
    * [ ] Setting desired incomes and outcomes limits for user's categories for period of time
* [ ] **Transactions** — shortcut for creating an outcome operation in one storage and an income operation in another

## Requirements
For building and running the application you need:
* [JDK 12](https://www.oracle.com/technetwork/java/javase/downloads/index.html)
* [Maven 3](https://maven.apache.org/download.cgi)
* [Docker Compose](https://docs.docker.com/compose/install/)

## Building
Run the command in the project root directory:
```shell
mvn clean install
```

## Before running
* Run the command in the project root directory:
```shell
docker-compose up
```
* Specify necessary allowed origins in CORS configuration in [`src/main/resources/application.yml`](src/main/resources/application.yml)

## Running
Run [`com.godev.budgetgo.BudgetgoApplication#main()`](src/main/java/com/godev/budgetgo/BudgetgoApplication.java) or execute the command in the project
 root directory:
```shell
mvn spring-boot:run
```

## Consuming
See **[BudgetGO Web Client](https://github.com/oleg-grigorijan/budgetgo-web-client)**.

## API
API documentation in OpenAPI JSON format is available on `/api/docs` endpoint. Use Swagger UI on `/swagger-ui.html` endpoint.
