@echo off
rem Builds a deployable cafe.war from your sources.
rem Run this, then deploy cafe.war on any Tomcat 9 server.
set ROOT=%~dp0
set APP=%ROOT%CafeManagementOnline\OnlineCafeManagement
set BUILD=%ROOT%deploy-build
set TOMCAT9=%ROOT%apache-tomcat-9.0.99
if not exist "%TOMCAT9%\lib\servlet-api.jar" (
  echo Tomcat 9 not found at %TOMCAT9%
  echo Download Tomcat 9 and extract it here as apache-tomcat-9.0.99
  pause & exit /b 1
)
rmdir /s /q "%BUILD%" 2>nul
mkdir "%BUILD%\WEB-INF\classes"
xcopy "%APP%\src\main\webapp" "%BUILD%" /E /I /Y /Q >nul
dir /s /b "%APP%\src\main\java\*.java" > "%ROOT%sources.txt"
javac -encoding UTF-8 -nowarn -cp "%TOMCAT9%\lib\servlet-api.jar" -d "%BUILD%\WEB-INF\classes" @"%ROOT%sources.txt"
del "%ROOT%sources.txt"
if errorlevel 1 (echo Compile failed & pause & exit /b 1)
del "%ROOT%cafe.war" 2>nul
jar -cf "%ROOT%cafe.war" -C "%BUILD%" .
echo.
echo Built: %ROOT%cafe.war
pause
