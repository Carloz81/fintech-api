# FINTECH-API

## Description
Repo for a demo project about the fintech world.

## Requirements
* The API will expose an endpoint which accepts the user information (customerID, initialCredit).
* Once the endpoint is called, a new account will be opened connected to the user whose ID is customerID.
* Also, if initialCredit is not 0, a transaction will be sent to the new account.
* Another Endpoint will output the user information showing Name, Surname, balance, and transactions of the accounts.

## Constraints
For storing the information, the data can be saved in memory and not actually persisted to an external database.

## Features

##### Spring Boot 2.3.0.M3
##### RESTful web service
##### Spring Data JPA
##### H2 in memory Database
##### Business Services
##### DTO pattern with ModelMapper
##### Transaction management
##### Unit test (JUnit-Mockito)
##### Integration test (MockMVC)
##### Actuator for monitoring
##### Swagger2 Documentation
##### Lombok for less boilerplate code
##### TravisCI pipeline
##### Docker dockerfile to build and run image

### Travis-CI 
Master release 
[![Build Status](https://travis-ci.com/Carloz81/fintech-api.svg?branch=master)](https://travis-ci.com/Carloz81/fintech-api)

Progress release 
[![Build Status](https://travis-ci.com/Carloz81/fintech-api.svg?branch=develop)](https://travis-ci.com/Carloz81/fintech-api)

### Build and run instructions:

* First Clean-Compile-Install with Maven

* After you can run the app with java -jar <jar name>

* Open the browser and type: http://localhost:8080/swagger-ui.html
From the swagger interface you can see the 2 endpoint exposed 
under account-controller and you can test them.

You can run the app with docker too.<br> 
First build the app with maven and after execute the command below

### Docker Instructions:

Build the container<br>
-> docker build -t fintechapi .

Run the container<br>
-> docker run -t -p 8080:8080 fintechapi

Start/Restart the container<br>
-> docker cp [container ID]