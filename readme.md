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
* [Tomcat 9](https://tomcat.apache.org/download-90.cgi)
* [MySQL Server 8](https://dev.mysql.com/downloads/mysql/)

## Building
Run the command in the project root directory:
```shell
mvn clean install
```

## Before running
* Configure MySQL Server according to the 
 [`src/main/resources/jdbc.properties`](src/main/resources/jdbc.properties). The database schema
  and tables will be created automatically.
* Specify necessary allowed origins in CORS configuration in [`src/main/resources/cors.properties`](src/main/resources/cors.properties)
* Execute queries from [`src/main/resources/sql/currencies_dump.sql`](src/main/resources/sql/currencies_dump.sql) to restore currencies records in database.

## Running
Configure `budgetgo:war` artifact deployment to Tomcat Server on the `8080` HTTP port and set application context to `/`. Then run Tomcat.

## Consuming
See **[BudgetGO Web Client](https://github.com/oleg-grigorijan/budgetgo-web-client)**.
