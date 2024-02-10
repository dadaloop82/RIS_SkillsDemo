@echo off
setlocal

REM Save the current JAVA_HOME path
echo Saving the current JAVA_HOME path...
set "OLD_JAVA_HOME=%JAVA_HOME%"

REM Set JAVA_HOME to the path of OpenJDK JDK 21.0.2
echo Setting JAVA_HOME to the path of OpenJDK JDK 21.0.2...
set "JAVA_HOME=C:\dev\RIS_SkillsDemo\open_jdk\jdk-21.0.2"

REM Update PATH to include JDK's binary temporarily
echo Updating PATH to include JDK's binary temporarily...
set "PATH=%JAVA_HOME%\bin;%PATH%"

REM Prompt to continue before restoring JAVA_HOME and PATH
echo Press any key to continue...
pause > nul

REM Restore JAVA_HOME to its original value
if defined OLD_JAVA_HOME (
    echo Restoring JAVA_HOME to its original value...
    set "JAVA_HOME=%OLD_JAVA_HOME%"
)

REM Restore PATH to its original state (if necessary)
if defined OLD_JAVA_HOME (
    echo Restoring PATH to its original state...
    set "PATH=%PATH:;%"
)

endlocal
