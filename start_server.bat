@echo off

rem Imposta la variabile JAVA_HOME
set JAVA_HOME=C:\dev\RIS_SkillsDemo\open_jdk\jdk-21.0.2

rem Avvio del server Spring Boot
echo Avvio del server Spring Boot...
start "Spring Boot" cmd /c "apache_maven\apache-maven-3.9.6\bin\mvn spring-boot:run"

rem Attendi che il server Spring Boot abbia avviato
:WAIT_FOR_SERVER
timeout /t 5 >nul
netstat -ano | findstr :8080 | findstr LISTENING >nul
if errorlevel 1 goto WAIT_FOR_SERVER

rem Avvio dell'applicazione React dopo che il server è avviato
echo Avvio dell'applicazione React...
cd frontend-directory
start cmd /c "npm start"
