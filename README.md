# Job Application Microservices Platform

![Job Application Platform](https://img.shields.io/badge/Job%20Application%20Platform-v1.0-blue.svg)
[![Releases](https://img.shields.io/badge/Releases-Check%20Here-brightgreen)](https://github.com/pruitt03/job-application-platform/releases)

## Overview

This project is a Dockerized Spring Boot microservices platform designed to streamline the management of company data, job listings, and user reviews. It offers a robust architecture that supports service discovery, centralized configuration, asynchronous messaging, distributed tracing, and data persistence using PostgreSQL.

## Project Structure

The architecture consists of several microservices, each serving a specific purpose:

- **mscompany**: This microservice manages all company-related data, including:
  - `id`: Unique identifier for the company.
  - `name`: Name of the company.
  - `description`: Brief description of the company.
  - `rating`: Average rating based on user reviews.

- **msjob**: Responsible for handling job listings, which include:
  - `id`: Unique identifier for the job.
  - `title`: Title of the job position.
  - `description`: Detailed description of the job.
  - `minSalary`: Minimum salary offered for the job.
  - `maxSalary`: Maximum salary offered for the job.
  - `location`: Job location.
  - `companyId`: Identifier linking the job to a specific company.

- **msreview**: This service manages user reviews for companies, with fields such as:
  - `id`: Unique identifier for the review.
  - `title`: Title of the review.
  - `description`: Detailed content of the review.
  - `rating`: Rating given by the user.
  - `companyId`: Identifier linking the review to a specific company.

- **config-server**: Centralized management for configuration settings across all microservices.

- **eureka-server**: Implements service discovery using Eureka, allowing microservices to find each other.

- **api-gateway**: Acts as a single entry point for routing requests to the appropriate microservices.

- **zipkin**: Provides distributed tracing capabilities to monitor and troubleshoot requests across services.

- **rabbitmq**: Functions as a message broker for asynchronous communication between microservices.

- **postgres**: Data storage solution utilizing PostgreSQL for persistence.

## Technologies Used

This project leverages several technologies to build a scalable and efficient platform:

- **Java 17**: The programming language used for developing the microservices.
- **Spring Boot**: Framework that simplifies the setup and development of Java applications.
- **Spring Cloud**: A suite of tools for building cloud-native applications, including:
  - **Eureka**: For service discovery.
  - **Config**: For centralized configuration management.
- **Docker**: Containerization platform for packaging applications and dependencies.
- **RabbitMQ**: Message broker for handling asynchronous communication.
- **PostgreSQL**: Relational database for data persistence.
- **Zipkin**: Distributed tracing system to monitor requests across services.
- **Kubernetes**: Container orchestration platform for automating deployment, scaling, and management of containerized applications.

## Features

### Service Discovery

The platform uses Eureka for service discovery. This allows microservices to register themselves and discover other services easily. It simplifies the communication between different components.

### Centralized Configuration

The config server manages configurations for all microservices. This ensures that changes to configuration settings can be made in one place without needing to redeploy each service.

### Asynchronous Messaging

Using RabbitMQ, the platform supports asynchronous messaging. This allows services to communicate without waiting for a response, improving overall system performance and responsiveness.

### Distributed Tracing

With Zipkin, the platform tracks requests as they move through various microservices. This helps in diagnosing performance issues and understanding the flow of requests.

### PostgreSQL Persistence

The platform uses PostgreSQL for data storage. This ensures reliable and efficient data management across microservices.

### API Gateway

The API gateway acts as a single entry point for all client requests. It routes requests to the appropriate microservices, providing a simplified interface for users.

### Resilience and Fault Tolerance

The platform incorporates resilience patterns, such as circuit breakers and retry mechanisms, to handle failures gracefully. This ensures that the system remains operational even in the face of errors.

## Getting Started

To get started with the Job Application Platform, follow these steps:

1. **Clone the Repository**: Use the following command to clone the repository to your local machine:

   ```
   git clone https://github.com/pruitt03/job-application-platform.git
   ```

2. **Navigate to the Project Directory**:

   ```
   cd job-application-platform
   ```

3. **Build the Project**: Use Maven to build the project. Run the following command:

   ```
   ./mvnw clean install
   ```

4. **Run the Services**: Use Docker Compose to run all the microservices. Execute the following command:

   ```
   docker-compose up
   ```

5. **Access the Application**: Open your web browser and navigate to `http://localhost:8080` to access the API gateway.

## API Endpoints

The API gateway provides several endpoints to interact with the microservices. Here are some of the key endpoints:

### Company Service

- **GET /companies**: Retrieve a list of all companies.
- **POST /companies**: Add a new company.
- **GET /companies/{id}**: Get details of a specific company.
- **PUT /companies/{id}**: Update a company's information.
- **DELETE /companies/{id}**: Remove a company from the system.

### Job Service

- **GET /jobs**: Retrieve a list of all job listings.
- **POST /jobs**: Add a new job listing.
- **GET /jobs/{id}**: Get details of a specific job.
- **PUT /jobs/{id}**: Update a job listing.
- **DELETE /jobs/{id}**: Remove a job listing from the system.

### Review Service

- **GET /reviews**: Retrieve a list of all reviews.
- **POST /reviews**: Add a new review.
- **GET /reviews/{id}**: Get details of a specific review.
- **PUT /reviews/{id}**: Update a review.
- **DELETE /reviews/{id}**: Remove a review from the system.

## Deployment

To deploy the Job Application Platform, you can use Kubernetes. Here’s a brief overview of the deployment process:

1. **Create Kubernetes Deployment Files**: Define deployment configurations for each microservice.

2. **Apply the Configurations**: Use `kubectl` to apply the configurations to your Kubernetes cluster.

   ```
   kubectl apply -f deployment.yaml
   ```

3. **Expose Services**: Use Kubernetes services to expose your microservices to the internet.

4. **Monitor the Deployment**: Use tools like Prometheus and Grafana to monitor the health and performance of your microservices.

## Testing

To ensure the platform functions correctly, a suite of unit and integration tests is included. Run the tests using Maven:

```
./mvnw test
```

## Contributing

Contributions are welcome! If you would like to contribute to the Job Application Platform, please follow these steps:

1. **Fork the Repository**: Click on the "Fork" button at the top right corner of the page.

2. **Create a New Branch**: Create a new branch for your feature or bug fix.

   ```
   git checkout -b feature/your-feature-name
   ```

3. **Make Your Changes**: Implement your changes and commit them.

   ```
   git commit -m "Add your message here"
   ```

4. **Push Your Changes**: Push your changes to your forked repository.

   ```
   git push origin feature/your-feature-name
   ```

5. **Create a Pull Request**: Go to the original repository and create a pull request.

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.

## Contact

For any inquiries or issues, please contact the repository owner at:

- GitHub: [pruitt03](https://github.com/pruitt03)
- Email: pruitt03@example.com

For more information and updates, check the [Releases](https://github.com/pruitt03/job-application-platform/releases) section.