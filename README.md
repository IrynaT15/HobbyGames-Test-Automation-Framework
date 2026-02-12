# Test Automation Framework
## UI & API Automated Testing Project
### hobbygames.by

---

## Author

Author: Iryna Tainikava  
Course: Automated Testing with Java  
Year: 2026  
GitHub: https://github.com/IrynaT15/HobbyGames-Test-Automation-Framework

---

## Project Overview

This project represents a **Test Automation Framework (TAF)** developed for automated testing of the website **hobbygames.by**.

**hobbygames.by** is an online store of the Hobby Games retail network specializing in board games and related products. The website provides:

- Login and authorization functionality
- Product search functionality
- Display of search results

Within this project, both **UI** and **API** automated testing were implemented.

---


## Technologies Used

- **Java**
- **Selenium WebDriver**
- **JUnit 5**
- **REST Assured**
- **Maven**
- **Log4j**
- **JavaFaker**
- **JSoup**
- **IntelliJ IDEA**
- **Git / GitHub**
- **Jenkins**
- **Postman**

---

## Project Structure

The project follows a layered structure separating framework logic and test implementation.
```
src
├── main
│   └── java
│       └── by.hobbygames
│           ├── api            # API client classes and request logic
│           ├── driver         # WebDriver configuration (Singleton)
│           ├── pages          # Page Object classes for UI
│           └── utils          # Utility classes (Waits with explicit waits)
│
└── test
    └── java
        └── by.hobbygames
            ├── api
            │   ├── assertions # Custom API assertions
            │   └── tests      # API test classes
            │
            ├── ui
            │   ├── assertions # UI-specific assertions
            │   └── tests      # UI test classes
            │
            └── testdata
                ├── credentials # Login test data
                ├── errors      # Expected error messages
                ├── search      # Search test data
                └── urls        # Website URLs

```
---

## Implemented Test Coverage

### UI Testing

- Verification of login form visibility
- Negative authorization scenarios
- Validation of error messages
- Verification of search input functionality
- Validation of search results display

### API Testing

- POST request to `/login`
- GET request to `/search`
- Status code validation
- Response structure validation
- Validation of the `success` flag
- Validation of error messages  

---

## Logging and Reporting

Logging is implemented using **Log4j**.  
Logs are saved to a file during test execution.

Test results are generated when running Maven build commands.

---

## CI/CD

A Jenkins job is configured to:

- Pull the project from the remote repository
- Execute automated tests
- Generate test reports

---

## How to Run the Project

### Clone the repository

```
bash
git clone <repository_url>
```

---

### Run tests

```
bash
mvn clean test
```
---

## Environment Requirements

- Java 17+
- Maven
- Google Chrome browser
- Internet connection

