# RIS_SkillsDemo

## Introduction
This document outlines the installation of OpenJDK JDK 21.0.2 for use in the project. Please note that the JDK itself is not included in the repository as it's listed in the `.gitignore` file.

## OpenJDK JDK 21.0.2
- **Version Used:** 21.0.2
- **Download Location:** [Java.net](https://jdk.java.net/21/)

## IDE and Extensions
- **IDE Used:** Visual Studio Code
- **Extensions Installed:** Java Extension Pack
  - This includes all necessary extensions for Java development in Visual Studio Code, such as "Language Support for Java(TM) by Red Hat" and "Debugger for Java".

## Project Initialization and Configuration
- **Build Tool:** Apache Maven
  - Apache Maven was used for project initialization and configuration. It provides a convenient way to manage dependencies, build configurations, and project structure.

## Using Apache Maven
To use Apache Maven for this project, follow these steps:
1. Open a PowerShell or Command Prompt window.
2. Set the `JAVA_HOME` environment variable to point to the OpenJDK JDK 21.0.2 installation directory by running the following command:
   
   ```powershell
   $env:JAVA_HOME = "C:\dev\RIS_SkillsDemo\open_jdk\jdk-21.0.2"
   cd \src
   .\bin\mvn archetype:generate
    ```