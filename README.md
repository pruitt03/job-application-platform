# Job Application Microservices Project

This is a Dockerized Spring Boot microservices project designed for managing company data, job listings, and user reviews for comapnies. It includes service discovery, centralized configuration, asynchronous messaging, distributed tracing, and PostgreSQL persistence.

## Project Structure

This project consists of the following microservices:

- **mscompany**: Manages company-related data(id, name, description, rating)
- **msjob**: Handles job listings(id, title, description, minSalary, maxSalary, location, companyId)
- **msreview**: Manages reviews for companies(id,title,description,rating,companyId)
- **config-server**: Centralized configuration management
- **eureka-server**: Service discovery with Eureka
- **api-gateway**: Single entry point for routing requests
- **zipkin**: Distributed tracing
- **rabbitmq**: Message broker for async communication
- **postgres**: Data storage using PostgreSQL

## Technologies Used

- Java 17
- Spring Boot
- Spring Cloud (Eureka, Config Server, OpenFeign)
- Spring Data JPA
- RabbitMQ
- Docker & Docker Compose
- Kubernetes
- PostgreSQL
- Zipkin (for distributed tracing)
- REST APIs


## Features & Learnings

### Microservices Concepts
- Built and managed independent microservices for different business domains
- Service-to-service communication using OpenFeign
- Configuration centralized using Spring Cloud Config Server

### DevOps & Containers
- Dockerized each microservice with its own Dockerfile
- Used Docker Compose for orchestration of services
- Exposed services on custom ports and linked services via container names

### Messaging & Tracing
- Implemented asynchronous messaging using RabbitMQ
- Integrated Zipkin for distributed tracing of microservices

### Code Practices
- Applied layered architecture: Controller, Service, Repository
- Used DTOs and Entity mapping to maintain data encapsulation


