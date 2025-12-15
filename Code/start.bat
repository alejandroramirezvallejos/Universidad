@echo off
echo ========================================
echo   Iniciando Full Stack Universidad
echo ========================================
echo.

echo [1/2] Iniciando servidor Spring Boot...
start "Spring Boot Server" cmd /k "cd Server && gradlew bootRun"

timeout /t 5 /nobreak > nul

echo [2/2] Iniciando cliente Angular...
start "Angular Client" cmd /k "cd Client && npm start"

echo.
echo ========================================
echo   Aplicacion iniciada!
echo   - Servidor: http://localhost:8080
echo   - Cliente: http://localhost:4200
echo ========================================

