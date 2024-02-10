# Check if the script is running with elevated privileges
if (-not ([Security.Principal.WindowsPrincipal][Security.Principal.WindowsIdentity]::GetCurrent()).IsInRole([Security.Principal.WindowsBuiltInRole]::Administrator)) {
    Write-Warning "This script must be run with elevated privileges (Run as Administrator)."
    Exit 1
}

# Save the current JAVA_HOME path
$OldJavaHome = $env:JAVA_HOME

# Set the desired JDK path
$DesiredJavaHome = "C:\dev\RIS_SkillsDemo\open_jdk\jdk-21.0.2"

# Check if JAVA_HOME is already set and matches the desired path
if ($env:JAVA_HOME -eq $DesiredJavaHome) {
    # If JAVA_HOME is already set to the desired path, no changes are needed
    Write-Host "JAVA_HOME environment variable is already set to the desired path: $env:JAVA_HOME"
} else {
    # Set JAVA_HOME to the desired path
    Write-Host "Setting JAVA_HOME to the desired path: $DesiredJavaHome..."
    [Environment]::SetEnvironmentVariable("JAVA_HOME", $DesiredJavaHome, [EnvironmentVariableTarget]::Machine)
    $env:JAVA_HOME = $DesiredJavaHome

    # Update PATH to include JDK's binary temporarily
    $env:PATH = "$DesiredJavaHome\bin;$env:PATH"

    # Display the new value of JAVA_HOME
    Write-Host ""
    Write-Host "JAVA_HOME environment variable has been changed to: $env:JAVA_HOME"
    Write-Host "Current value of JAVA_HOME: $DesiredJavaHome"

    # Prompt to press any key before restoring JAVA_HOME
    Read-Host -Prompt "Press Enter to restore the original JAVA_HOME..."

    # Restore JAVA_HOME to its original value
    if ($OldJavaHome -ne $null) {
        Write-Host "Restoring JAVA_HOME to its original value: $OldJavaHome"
        [Environment]::SetEnvironmentVariable("JAVA_HOME", $OldJavaHome, [EnvironmentVariableTarget]::Machine)
    } else {
        Write-Host "Removing JAVA_HOME environment variable..."
        [Environment]::SetEnvironmentVariable("JAVA_HOME", $null, [EnvironmentVariableTarget]::Machine)
    }

    # Restore PATH to its original state
    $env:PATH = $env:PATH -replace [Regex]::Escape(";${DesiredJavaHome}\bin"), ""
}   
