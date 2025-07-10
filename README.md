# Selenium Automation Assessment Solution

This project is a test automation framework built with Java, Selenium, and Maven. It serves as a comprehensive solution to the Automation Assessment, demonstrating best practices for web UI testing, including the Page Object Model (POM), inheritable test setup for clean code, and integration with GitHub Actions for Continuous Integration (CI).

## 📝 Assessment Overview

The goal of this assessment was to create automated tests for three different web pages, each with a unique challenge. The framework is structured so that each test class corresponds directly to one of the questions in the assessment.

### Question 1: Login Page Scenarios
*   **Challenge:** Test multiple login scenarios, including verifying page elements on load, successful login, and handling different types of error messages for invalid inputs.
*   **Solution:** The tests for this question are located in `src/test/java/tests/LoginTest.java`.

### Question 2: Questionnaire with Radio Buttons
*   **Challenge:** Interact with a form containing multiple groups of radio buttons and select a random option ("Yes" or "No") for each question.
*   **Solution:** The script that solves this challenge is in `src/test/java/tests/QuestionnaireTest.java`.

### Question 3: Dynamic Dropdown List
*   **Challenge:** Select a specific item (the 5th element) from a dynamic list that only appears after a user interaction.
*   **Solution:** The implementation for this test can be found in `src/test/java/tests/EventsTest.java`.

## ✨ Features

*   **Page Object Model (POM):** Clean separation between test logic and page-specific code, making tests easier to read and maintain.
*   **Centralized Driver Management:** A `BaseTest` class handles WebDriver setup and teardown, including configuration for headless execution.
*   **Dynamic Waits:** Uses `WebDriverWait` to handle dynamic elements gracefully, avoiding flaky tests caused by timing issues.
*   **CI/CD Ready:** Integrated with GitHub Actions to automatically run all tests on every `push` and `pull request` to the `main` branch.
*   **Automated Dependency Management:** Utilizes Maven for managing project dependencies and running the test suite.
*   **Automatic Browser Driver Setup:** Incorporates `WebDriverManager` to automatically download and set up the correct ChromeDriver, eliminating manual configuration.

## 🛠️ Tech Stack

*   **Java 21:** The core programming language.
*   **Selenium WebDriver:** For browser automation and UI interaction.
*   **Maven:** For project building and dependency management.
*   **JUnit 4:** As the testing framework for structuring and running tests.
*   **WebDriverManager:** To automate the management of WebDriver binaries.
*   **GitHub Actions:** For Continuous Integration (CI).

## 📂 Project Structure

```
.
├── .github/
│   └── workflows/
│       └── maven-ci.yml    # GitHub Actions workflow for CI
├── src/
│   ├── main/
│   │   └── java/
│   │       └── pages/      # Page Object classes
│   │           ├── EventsPage.java
│   │           ├── LoginPage.java
│   │           └── QuestionnairePage.java
│   └── test/
│       └── java/
│           └── tests/      # Test classes
│               ├── BaseTest.java
│               ├── EventsTest.java
│               ├── LoginTest.java
│               └── QuestionnaireTest.java
├── EventsPage.html         # Local HTML files for testing
├── LoginPage.html
├── QuestionnairePage.html
└── pom.xml                 # Maven project configuration
```

## 🚀 Getting Started

### Prerequisites

To run this project locally, you will need:
*   **Java Development Kit (JDK) 21** or later.
*   **Apache Maven**.
*   A Git client to clone the repository.

### Installation & Running Tests Locally

1.  **Clone the repository:**
    ```sh
    git clone 
    cd 
    ```

2.  **Run the tests:**
    Execute the following command in the root directory of the project. Maven will automatically download all dependencies and run the entire test suite.
    ```sh
    mvn test
    ```
    The results of the test execution will be displayed in your terminal.

## ⚙️ Continuous Integration (CI)

This project is configured to run automatically using **GitHub Actions**. The workflow is defined in the `.github/workflows/maven-ci.yml` file.

**How it works:**
*   **Trigger:** The workflow is triggered on every `push` or `pull_request` to the `main` branch.
*   **Execution:** It sets up an Ubuntu environment, installs JDK 21, and runs the `mvn test` command to execute the test suite in a **headless browser**.
*   **Reporting:** Upon completion, it uploads the test reports as artifacts, which can be downloaded for debugging in case of failures.