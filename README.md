# TP3 Spring Boot Application 🌱

## Project Overview 📄

This Spring Boot application is built to manage patient data efficiently, featuring a comprehensive user interface for tracking patient details including health metrics. Utilizing Spring Data JPA with Hibernate and an H2 database for seamless development and testing, the application is robust and scalable.

## Key Features 🔑

- **Patient Management**: Efficiently handle patient records, including health statuses and scores. 🏥
- **Security**: Implements Spring Security for authentication and authorization with custom user details. 🔐
- **Repository Layer**: Uses JPA repositories to manage database operations seamlessly. 📊
- **Service Layer**: Contains business logic encapsulated within services like `AccountService` and provides implementation details in `AccountServiceImpl`. 🛠️
- **Web Layer**: Utilizes controllers such as `PatientController` and `SecurityController` to interact with the web interface. 🌐
- **Database**: Configured to use H2 in-memory database for development, easing the testing and development processes. 🗄️

## Getting Started 🚀

### Prerequisites

- Java 11 or newer
- Maven for dependency management

### Setup and Installation

1. **Clone the repository**:
   ```
   git clone https://github.com/anasbounaiim/TP3_java_spring.git
   ```
2. **Navigate into the project directory**:
   ```
   cd TP3_java_spring
   ```
3. **Run the application**:
   ```
   mvn spring-boot:run
   ```
   Your application should be up and running on `http://localhost:8080`.

## Configuration 🛠️

The application's configuration details can be found in `application.properties`, which includes the database settings for H2. Adjust these settings as necessary to suit your environment.

## Usage 📋

- **Login**: Authenticate using predefined credentials through the login page.
- **Patient Records**: Access and manage patient details via the web interface provided under the `Patients` section.

