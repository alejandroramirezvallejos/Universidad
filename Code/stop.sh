#!/bin/bash

# 🛑 Script para detener Backend y Frontend

echo "════════════════════════════════════════════════════════════"
echo "🛑 DETENIENDO SISTEMA"
echo "════════════════════════════════════════════════════════════"
echo ""

# Colores
RED='\033[0;31m'
GREEN='\033[0;32m'
BLUE='\033[0;34m'
NC='\033[0m'

# Leer PIDs guardados
if [ -f /tmp/backend.pid ]; then
    BACKEND_PID=$(cat /tmp/backend.pid)
    echo -e "${BLUE}🔧 Deteniendo Backend (PID: $BACKEND_PID)...${NC}"
    kill $BACKEND_PID 2>/dev/null
    echo -e "${GREEN}✓ Backend detenido${NC}"
    rm /tmp/backend.pid
else
    echo -e "${RED}⚠️  No se encontró PID del backend${NC}"
    echo "   Intentando detener por puerto..."
    lsof -ti:8080 | xargs kill -9 2>/dev/null && echo -e "${GREEN}✓ Backend detenido por puerto${NC}"
fi

echo ""

if [ -f /tmp/frontend.pid ]; then
    FRONTEND_PID=$(cat /tmp/frontend.pid)
    echo -e "${BLUE}🎨 Deteniendo Frontend (PID: $FRONTEND_PID)...${NC}"
    kill $FRONTEND_PID 2>/dev/null
    echo -e "${GREEN}✓ Frontend detenido${NC}"
    rm /tmp/frontend.pid
else
    echo -e "${RED}⚠️  No se encontró PID del frontend${NC}"
    echo "   Intentando detener por puerto..."
    lsof -ti:4200 | xargs kill -9 2>/dev/null && echo -e "${GREEN}✓ Frontend detenido por puerto${NC}"
fi

echo ""
echo "════════════════════════════════════════════════════════════"
echo -e "${GREEN}✅ SISTEMA DETENIDO${NC}"
echo "════════════════════════════════════════════════════════════"
