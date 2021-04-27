@echo off

call :MavenInstall microservice-world-bom
call :MavenInstall microservice-world-parent
call :MavenInstall microservice-world-core-library
call :MavenInstall api-gateway
call :MavenInstall config-server
call :MavenInstall discovery-server-eureka
call :MavenInstall movie-catalog-service
call :MavenInstall movie-info-service
call :MavenInstall movie-rating-service

echo *********************************************************************
echo mvn-clean-install is completed.
EXIT /B %ERRORLEVEL%


:MavenInstall
echo *********************************************************************
echo Installing %~1
call mvn -f ..\%~1\pom.xml clean install -U -DskipTests
echo.
EXIT /B 0