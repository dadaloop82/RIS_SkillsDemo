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

## Execute mvn spring-boot:run in a new separate window
Write-Output "▶ Executing mvn spring-boot:run in a new window..."
Start-Process -FilePath ".\apache_maven\apache-maven-$mavenVersion\bin\mvn.cmd" -ArgumentList "spring-boot:run"

# Wait for about 10 seconds before checking npm install
Start-Sleep -Seconds 10

# Check if 'node_modules' directory exists in the frontend path
if (-Not (Test-Path -Path (Join-Path $frontendPath "node_modules"))) {
    Write-Output "▶ 'node_modules' directory not found. Running npm install..."
    Start-Process -FilePath "npm" -ArgumentList "install" -WorkingDirectory $frontendPath -Wait
    Write-Output "✔ npm install completed."
}

# Start npm in a new window
Write-Output "▶ Starting npm in a new window..."
Start-Process -FilePath "npm" -ArgumentList "start" -WorkingDirectory $frontendPath

# Wait for a key press to exit
Read-Host "Press any key to exit..."

# Restore the JAVA_HOME and PATH variables to their original values
$env:JAVA_HOME = $originalJavaHome
$env:PATH = $originalPath
Write-Output "Restored JAVA_HOME and PATH to their original values."
