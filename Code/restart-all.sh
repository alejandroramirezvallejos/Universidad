#!/bin/bash

# Script para reiniciar el sistema completo después de los cambios

echo "======================================"
echo "  REINICIO COMPLETO DEL SISTEMA"
echo "======================================"
echo ""

# Colores
GREEN='\033[0;32m'
BLUE='\033[0;34m'
RED='\033[0;31m'
NC='\033[0m' # No Color

# 1. Detener procesos existentes
echo -e "${BLUE}[1/5] Deteniendo procesos existentes...${NC}"
lsof -ti:8080 | xargs kill -9 2>/dev/null || true
lsof -ti:4200 | xargs kill -9 2>/dev/null || true
sleep 2
echo -e "${GREEN}✓ Procesos detenidos${NC}"
echo ""

# 2. Compilar Backend
echo -e "${BLUE}[2/5] Compilando Backend...${NC}"
cd Server
./gradlew build -x test --no-daemon
if [ $? -eq 0 ]; then
    echo -e "${GREEN}✓ Backend compilado exitosamente${NC}"
else
    echo -e "${RED}✗ Error al compilar el backend${NC}"
    exit 1
fi
cd ..
echo ""

# 3. Iniciar Backend
echo -e "${BLUE}[3/5] Iniciando Backend...${NC}"
cd Server
./gradlew bootRun &
BACKEND_PID=$!
echo -e "${GREEN}✓ Backend iniciado (PID: $BACKEND_PID)${NC}"
cd ..
echo ""

# Esperar a que el backend esté listo
echo -e "${BLUE}[4/5] Esperando a que el backend esté listo...${NC}"
sleep 10
echo -e "${GREEN}✓ Backend listo${NC}"
echo ""

# 4. Iniciar Frontend
echo -e "${BLUE}[5/5] Iniciando Frontend...${NC}"
cd Client
ng serve &
FRONTEND_PID=$!
echo -e "${GREEN}✓ Frontend iniciado (PID: $FRONTEND_PID)${NC}"
cd ..
echo ""

echo "======================================"
echo -e "${GREEN}  SISTEMA INICIADO CORRECTAMENTE${NC}"
echo "======================================"
echo ""
echo "Backend:  http://localhost:8080"
echo "Frontend: http://localhost:4200"
echo ""
echo -e "${RED}IMPORTANTE:${NC} Abre la consola del navegador y ejecuta:"
echo "  localStorage.clear()"
echo "Luego recarga la página y vuelve a hacer login."
echo ""
echo "Para detener el sistema, presiona Ctrl+C"

# Mantener el script corriendo
wait
