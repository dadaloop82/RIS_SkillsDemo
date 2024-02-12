# Save the original JAVA_HOME and PATH values
$originalJavaHome = $env:JAVA_HOME
$originalPath = $env:PATH

# Define variables for Maven and JDK versions
$mavenVersion = "3.9.6"
$jdkVersion = "21.0.2"
# Define the frontend directory path
$frontendPath = Join-Path $pwd.Path "src\main\frontend"

# Check if Node.js is installed
$nodeExecutable = Get-Command node.exe -ErrorAction SilentlyContinue
if (-Not $nodeExecutable) {
    Write-Error "❌ Node.js is not installed. Please install Node.js before running this script."
    Read-Host "Press Enter to continue..."
    Exit
} else {
    Write-Output "✔ Node.js executable found."
}


# Set the JAVA_HOME variable
$env:JAVA_HOME = "$($pwd.Path)\open_jdk\jdk-$jdkVersion"
if ($env:JAVA_HOME -eq "$($pwd.Path)\open_jdk\jdk-$jdkVersion") {
    Write-Output "✔ JAVA_HOME variable successfully set to $($env:JAVA_HOME)."
} else {
    Write-Error "❌ Unable to set the JAVA_HOME variable. Check permissions."
    Read-Host "Press Enter to continue..."
    Exit
}

# Add the JDK folder to the PATH
$env:PATH += ";$($pwd.Path)\open_jdk\jdk-$jdkVersion\bin"
if ($env:PATH.Contains("$($pwd.Path)\open_jdk\jdk-$jdkVersion\bin")) {
    Write-Output "✔ JDK folder added to the PATH variable."
} else {
    Write-Error "❌ Unable to add the JDK folder to the PATH. Check permissions."
    Read-Host "Press Enter to continue..."
    Exit
}

# Check if mvn is a valid command
if (-Not (Test-Path -Path ".\apache_maven\apache-maven-$mavenVersion\bin\mvn.cmd")) {
    Write-Error "❌ mvn is not a valid command. Make sure Maven is installed and in the PATH variable."
    Read-Host "Press Enter to continue..."
    Exit
} else {
    Write-Output "✔ mvn executable found."
}

# Execute mvn clean in a separate process
Write-Output "▶ Executing mvn clean install..."
$mvnOutput = & ".\apache_maven\apache-maven-$mavenVersion\bin\mvn.cmd" clean install

# Check if mvn clean completed successfully
if ($LASTEXITCODE -eq 0) {
    Write-Output "✔ mvn clean install completed successfully."
} else {
    Write-Error "❌ mvn clean install failed with exit code $($LASTEXITCODE)"
    Write-Output $mvnOutput
    Read-Host "Press Enter to continue..."
    Exit
}

# Check if mvnOutput contains error messages
if ($mvnOutput -match "BUILD FAILURE" ) {
    Write-Error "❌ mvn clean install failed with the following errors:"
    Write-Output $mvnOutput
    Read-Host "Press Enter to continue..."
    Exit
}

# Execute mvn spring-boot:run in a new separate window
Write-Output "▶ Executing mvn spring-boot:run in a new window..."
$mvnProcess = Start-Process -FilePath ".\apache_maven\apache-maven-$mavenVersion\bin\mvn.cmd" -ArgumentList "spring-boot:run" -PassThru

# Wait for about 10 seconds before checking if Maven process has exited
Write-Output "▪ Waiting 10 secs..."
Start-Sleep -Seconds 10

# Check if Maven process has exited
if (-not $mvnProcess.HasExited) {
    Write-Output "Maven process is still running. Starting npm in a new window on port 8080..."

    # Start npm in a new window on port 8080
    Start-Process -FilePath "npm" -ArgumentList "start --port 8080" -WorkingDirectory $frontendPath
} else {
    # Maven process has exited
    Write-Error "❌ Maven process has exited before npm could start. Check the Maven build logs for errors."
    Read-Host "Press Enter to continue..."
}

# Wait for a key press to exit
Read-Host "Press any key to exit..."

# Terminate all Java and Node processes
Get-Process java | Stop-Process -Force
Get-Process node | Stop-Process -Force
Write-Output "▶ Java & Node killed ..."

# Restore the JAVA_HOME and PATH variables to their original values
$env:JAVA_HOME = $originalJavaHome
$env:PATH = $originalPath
Write-Output "Restored JAVA_HOME and PATH to their original values and terminated Java and Node processes."
