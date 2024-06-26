# MUREBACKEND

[![License](https://img.shields.io/badge/license-MIT-blue.svg?style=flat-square)](https://github.com/anggakharisma/murebackend/blob/main/LICENSE)

This project is built using Java and Spring Boot framework to provide a robust and secure RESTful API. It includes features such as JWT authentication, file upload (supporting both images and audio files), secure routes, and integration testing. 🚀

## Features

- **JWT Authentication**: Protects routes using JSON Web Tokens (JWT) to ensure secure access to the API endpoints.
- **File Upload**: Supports uploading image and audio files, making it easy to handle multimedia content.
- **Secure Routes**: Implements secure routes that require authentication to access sensitive information.
- **Integration Testing**: Includes comprehensive integration tests to ensure the stability and reliability of the API.

## Prerequisites

Before running this project locally, ensure you have the following installed:

- Java Development Kit (JDK) 18 or later
- PostgreSQL

## Development
use inteliJ or via terminal you can run these commands:
```
./gradlew bootJar --continuous

and on another terminal

./gradlew bootRun

```

## Docker 

#### Development
```
docker run -dit --name mure-api --env DB_CONFIG='jdbc:postgresql://pg-local:5432/mure' -w /app -v $(pwd):/app -p 3241:8080 -u $(id -u):$(id -g) --network docker-net amazoncorrett
o:18

./gradlew bootJar --continuous

open another terminal

./gradlew bootRun
```


#### Production
