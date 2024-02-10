@echo off

rem Imposta la variabile JAVA_HOME
set JAVA_HOME=C:\dev\RIS_SkillsDemo\open_jdk\jdk-21.0.2

rem Avvio dell'applicazione Java
echo Avvio dell'applicazione Java...
start "Java Application" cmd /c "open_jdk\jdk-21.0.2\bin\java -jar .\target\RISSkillDemo-1.0.0.jar"

rem Attendi un secondo prima di aprire il browser
timeout /t 10 /nobreak >nul

rem Apri il browser sulla porta 8080
start "" http://localhost:8080
