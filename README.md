# Java REST API with Testcontainers, Selenium, and Docker

This project demonstrates the implementation of **Testcontainers** and **Selenium** in a Java REST API application. It
was created to be used as a learning resource to explore the integration of these tools in a Spring-based
environment.
---

## Features

- **REST API**: A simple, functional REST API built using Spring MVC.
- **Persistence**: Works with a PostgreSQL database using **Spring Data JPA**.
- **Testcontainers**: Utilized for testing database interactions with an isolated and disposable container environment.
- **Selenium**: Integrated for browser-based end-to-end (E2E) testing to simulate user interactions on a web front-end.
- **Docker Integration**: Used to create and manage containerized environments for both development and testing.

---

## Prerequisites

To run and test this project, ensure the following tools are installed on your system:

- **Java 17** or later
- **Maven 3.8+**
- **Docker** (required for Testcontainers and testing environments)
- **Browser Driver** (e.g., ChromeDriver for Selenium)

---

## Getting Started

1. **Clone the repository**:
   ```bash
   git clone https://github.com/DylanHourigan/SeleniumTestApplication.git
   cd SeleniumTestApplication
   ```

2. **Build the project**:
   ```bash
   mvn clean install
   ```

3. **Run the application with Docker**:
   Ensure Docker is running and execute:
   ```bash
   docker-compose up --build
   ```
   This will create a containerized environment for the application along with any required services (e.g., PostgreSQL).


4. **Access the API**:
   Open your browser or API client, and navigate to:
   ```
   http://localhost:8080/employees
   ```

    This will display all employees in the database.
---

## Running Tests

To run tests, use the following Maven commands:

  ```bash
  mvn test
  ```




---

## Docker Integration

Docker is integral to the project for both runtime environments and testing infrastructure:

1. **Development Environment**: The application and supporting services, such as the PostgreSQL database, are
   containerized using Docker Compose.
    - Docker Compose configuration can be found in the `docker-compose.yaml` file.
    - To start the application with all its dependencies:
      ```bash
      docker-compose up --build
      ```

2. **Testcontainers**: Used for running tests inside Docker-managed containers to ensure reproducible test environments.

3. **Selenium Grid**: A Selenium Grid can also run in a Docker container to provide a scalable
   browser-testing environment.

---

## Dependencies

The key dependencies used in this project include:

- **Spring Boot**: Framework for building the REST API.
- **Spring Data JPA**: Data access layer.
- **Hibernate**: ORM for working with entities.
- **Testcontainers**: Setup and teardown of containerized testing environments.
- **Selenium**: Browser-based test automation.
- **Docker**: Core to containerization and infrastructure orchestration.

---

## Goals of the Project

- Understand how to use **Testcontainers** for isolated database testing.
- Learn to implement end-to-end tests with **Selenium**.
- Gain proficiency in leveraging **Docker** for both development and testing.
- Build a REST API with clean and testable architecture.
- Explore the benefits of containerization in modern application development.
