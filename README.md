# RIS_SkillsDemo

## Introduction
This document outlines the installation of OpenJDK JDK 21.0.2 for use in the project. Please note that the JDK itself is not included in the repository as it's listed in the `.gitignore` file.

## Prerequisites
Ensure the following software is installed on your system:
- OpenJDK JDK 21.0.2
- Apache Maven 3.9.6
- Node.js (for React frontend)
- Visual Studio Code (or any preferred IDE)

## OpenJDK JDK 21.0.2
- **Version Used:** 21.0.2
- **Download Location:** [Java.net](https://jdk.java.net/21/)

## Apache Maven
- **Version Used:** 3.9.6
- **Download Location:** [Apache Maven](https://maven.apache.org/download.cgi)

## Node.js
- **Download Location:** [Node.js](https://nodejs.org/)

## IDE and Extensions
- **IDE Used:** Visual Studio Code
- **Extensions Recommended:** Java Extension Pack

## Project Structure
- `.mvn`: Maven wrapper directory.
- `apache_maven`: Apache Maven installation directory.
- `open_jdk`: OpenJDK installation directory.
- `src`: Source code directory.
  - `main`: Main source code directory.
    - `frontend`: Frontend source code directory.
    - `java`: Java source code directory.
      - `com`: Main package directory.
        - `dadaloop`: Project package directory.
          - `RISSkillDemo`: Main project directory.
    - `resources`: Resource directory.
      - `static`: Static resources directory.
      - `templates`: Templates directory.
  - `test`: Test source code directory.
    - `java`: Java test code directory.

## Start the project
To start the project, run the `start_dev.ps1` file for development or the `start_prod.ps1` file for production.
