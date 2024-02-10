@echo off
REM This batch script is used to set or temporarily modify the JAVA_HOME environment variable
REM and the PATH variable to use a specific JDK version. This is useful for managing different
REM Java installations on your system and ensuring that the correct version is used for a 
REM particular project or task.
setlocal

REM Set the desired JDK path
set "DESIRED_JAVA_HOME=C:\dev\RIS_SkillsDemo\open_jdk\jdk-21.0.2"

REM Check if JAVA_HOME is already set and matches the desired path
if "%JAVA_HOME%"=="%DESIRED_JAVA_HOME%" (
    REM If JAVA_HOME is already set to the desired path, no changes are needed
    echo JAVA_HOME environment variable is already set to the desired path: %JAVA_HOME%
) else (
    REM Save the current JAVA_HOME path
    set "OLD_JAVA_HOME=%JAVA_HOME%"
    
    REM Set JAVA_HOME to the desired path
    echo Setting JAVA_HOME to the desired path: %DESIRED_JAVA_HOME%...
    setx JAVA_HOME "%DESIRED_JAVA_HOME%" > nul
    set "JAVA_HOME=%DESIRED_JAVA_HOME%"
    
    REM Update PATH to include JDK's binary temporarily
    set "PATH=%JAVA_HOME%\bin;%PATH%"
    
    REM Display the new value of JAVA_HOME
    echo.
    echo JAVA_HOME environment variable has been changed to: %JAVA_HOME%
    echo Current value of JAVA_HOME: %DESIRED_JAVA_HOME%
    
    REM Prompt to press any key before restoring JAVA_HOME
    echo Press any key to restore the original JAVA_HOME...
    pause > nul
    
    REM Restore JAVA_HOME to its original value
    set "JAVA_HOME=%OLD_JAVA_HOME%"
    
    REM Restore PATH to its original state
    set "PATH=%PATH:;%"
    echo.
    echo JAVA_HOME environment variable has been restored to: %DESIRED_JAVA_HOME%

)

endlocal
