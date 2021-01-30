# Contact List

Contact list is a simple Spring Boot WEB application. 
That allows to retrieve a list of usernames, and their images by REST calls.
It uses a Liquibase to provide database migration, initially creating the relevant
tables in a DB. Currently, in-memory H2 storage is used that starts on application start-up and
loads initial data from [people.csv](contact-list-service/src/main/resources/static/people.csv).

Angular is used on UI. It consists of single page, that makes REST calls to the backend. 
It provides with paging control, filtering and sorting of the data. The UI is available on the root
path of an application when it's started.

## Built with

- Spring Boot
- Angular CLI
- Liquibase
- H2
- JPA
- Maven

## Getting started

Instructions to build and start an application.

### Prerequisites

- JDK 15
- Maven  
- npm `npm install npm@latest -g`

### Installation

Build a project with Maven:

- Full build with tests `mvn clean install`
- Fast build `mvn clean install -DskipTests -T2`

### Usage

Run an already built application from [contact-list-service](contact-list-service) with Maven.

`mvn spring-boot:run`

The application is started on [localhost](http://localhost:8080) and port _8080_.
