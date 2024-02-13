# RIS_SkillsDemo

## Introduction
This document outlines the installation and setup of the RIS_SkillsDemo project. It includes information about the technologies used, project structure, and how to start the project.

## Prerequisites
Ensure the following software is installed on your system:
- OpenJDK JDK 21.0.2
- Apache Maven 3.9.6
- Node.js (for React frontend)
- Visual Studio Code (or any preferred IDE)

## Technologies Used
- **Backend Framework:** Apache Maven with Spring Boot
- **Backend Language:** Java
- **Frontend Framework:** React (Node.js)
- **IDE:** Visual Studio Code

## Project Structure
The project structure is organized as follows:
- `.mvn`: Maven wrapper directory.
- `apache_maven`: Apache Maven installation directory.
- `open_jdk`: OpenJDK installation directory.
- `src`: Source code directory.
  - `main`: Main source code directory.
    - `frontend`: Frontend source code directory (React).
    - `java`: Java source code directory.
      - `com.dadaloop.RISSkillDemo`: Main package directory for the project.
        - `controller`: Controller classes for handling HTTP requests.
        - `model`: Model classes representing data entities.
        - `repository`: Repository classes for database operations.
        - `service`: Service classes for business logic.
    - `resources`: Resource directory containing static files and templates.
      - `static`: Static resources directory for frontend assets.
      - `templates`: Templates directory for server-side rendering.
  - `test`: Test source code directory.

## Start the Project
To start the project:
- Run `start_dev.ps1` for development mode.
- Run `start_prod.ps1` for production mode.

## Project Description
The RIS_SkillsDemo project is a note management application that allows users to add, edit, and delete annotations without authentication. It utilizes Apache Maven with Spring Boot as the backend framework, Java as the backend language, and React (Node.js) for the frontend. Visual Studio Code was used as the Integrated Development Environment (IDE).

The backend utilizes Spring Boot's capabilities to handle HTTP requests with controller classes, perform database operations with repository classes, and implement business logic with service classes. The project uses H2, an embedded Java database, for persistence.

The frontend provides a user-friendly interface for managing notes, implemented with React components. The application aims to be robust, maintainable, and understandable, with extensive comments provided throughout the codebase. Additionally, the current state of the H2 database is displayed at the bottom of the frontend interface for reference.
