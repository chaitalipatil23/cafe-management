@echo off
rem Compiles the Java sources, copies the webapp into Tomcat 9 and starts the server.
rem Open http://localhost:8080/cafe/Login.jsp afterwards. Needs MySQL running.
set ROOT=%~dp0
set APP=%ROOT%CafeManagementOnline\OnlineCafeManagement
set CATALINA_HOME=%ROOT%apache-tomcat-9.0.99
set TARGET=%CATALINA_HOME%\webapps\cafe

if not exist "%TARGET%\WEB-INF\classes" mkdir "%TARGET%\WEB-INF\classes"
xcopy "%APP%\src\main\webapp" "%TARGET%" /E /I /Y /Q >nul
dir /s /b "%APP%\src\main\java\*.java" > "%ROOT%sources.txt"
javac -encoding UTF-8 -nowarn -cp "%CATALINA_HOME%\lib\servlet-api.jar" -d "%TARGET%\WEB-INF\classes" @"%ROOT%sources.txt"
del "%ROOT%sources.txt"
if errorlevel 1 (echo Compile failed & pause & exit /b 1)

java --add-opens=java.base/java.lang=ALL-UNNAMED --add-opens=java.base/java.io=ALL-UNNAMED --add-opens=java.base/java.util=ALL-UNNAMED --add-opens=java.base/java.util.concurrent=ALL-UNNAMED -Dcatalina.home="%CATALINA_HOME%" -Dcatalina.base="%CATALINA_HOME%" -Djava.util.logging.manager=org.apache.juli.ClassLoaderLogManager -cp "%CATALINA_HOME%\bin\bootstrap.jar;%CATALINA_HOME%\bin\tomcat-juli.jar" org.apache.catalina.startup.Bootstrap start
