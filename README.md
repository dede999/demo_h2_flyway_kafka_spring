# Spring Boot Sandbox Project

A sandbox project for experimenting with Apache Kafka event coordination and Flyway database migrations.

## Prerequisites

- Java 17 or higher
- Maven
- Docker and Docker Compose
- PostgreSQL
- Apache Kafka

## Setup

1. Clone the repository
2. Run `docker-compose up` to start PostgreSQL
3. Build the project: `mvn clean install`
4. Run the application: `mvn spring-boot:run`

## Technology Stack

- Spring Boot 3.x
- Spring Data JPA
- Spring MVC
- Apache Kafka
- Flyway Migration
- PostgreSQL
- Lombok
- Resend Email Service

## Development Notes

This is a development/sandbox environment with:

- Database credentials exposed in application.properties
- Basic user authentication system
- Email sending capabilities using Resend
- GraphQL support enabled

**Disclaimer:** This project is for development purposes only. Security measures and credentials are not
production-ready.
