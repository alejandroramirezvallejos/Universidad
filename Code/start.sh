#!/bin/bash

# 🚀 Script para iniciar Backend y Frontend
# Universidad - Sistema de Gestión Académica

echo "════════════════════════════════════════════════════════════"
echo "🚀 INICIANDO SISTEMA COMPLETO"
echo "════════════════════════════════════════════════════════════"
echo ""

# Colores
GREEN='\033[0;32m'
BLUE='\033[0;34m'
YELLOW='\033[1;33m'
NC='\033[0m' # No Color

# 1. Verificar Java
echo -e "${BLUE}📋 Verificando requisitos...${NC}"
if ! command -v java &> /dev/null; then
    echo -e "${YELLOW}⚠️  Java no encontrado. Instala Java 17 o superior.${NC}"
    exit 1
fi
echo -e "${GREEN}✓ Java instalado${NC}"

# 2. Verificar Node
if ! command -v node &> /dev/null; then
    echo -e "${YELLOW}⚠️  Node.js no encontrado. Instala Node.js 18 o superior.${NC}"
    exit 1
fi
echo -e "${GREEN}✓ Node.js instalado${NC}"
echo ""

# 3. Iniciar Backend
echo -e "${BLUE}🔧 Iniciando Backend (Spring Boot)...${NC}"
cd Server
chmod +x gradlew
nohup ./gradlew bootRun > /tmp/backend.log 2>&1 &
BACKEND_PID=$!
echo -e "${GREEN}✓ Backend iniciado en PID: $BACKEND_PID${NC}"
echo -e "  Logs: tail -f /tmp/backend.log"
echo -e "  Puerto: http://localhost:8080"
echo ""

# 4. Esperar que el backend arranque
echo -e "${YELLOW}⏳ Esperando que el backend arranque (30 segundos)...${NC}"
sleep 30

# 5. Verificar backend
echo -e "${BLUE}🔍 Verificando backend...${NC}"
if curl -s http://localhost:8080/api/gestiones > /dev/null; then
    echo -e "${GREEN}✓ Backend respondiendo correctamente${NC}"
else
    echo -e "${YELLOW}⚠️  Backend no responde. Verifica los logs: tail -f /tmp/backend.log${NC}"
fi
echo ""

# 6. Iniciar Frontend
echo -e "${BLUE}🎨 Iniciando Frontend (Angular)...${NC}"
cd ../Client
npm install > /dev/null 2>&1
nohup npm start > /tmp/frontend.log 2>&1 &
FRONTEND_PID=$!
echo -e "${GREEN}✓ Frontend iniciado en PID: $FRONTEND_PID${NC}"
echo -e "  Logs: tail -f /tmp/frontend.log"
echo -e "  URL: http://localhost:4200"
echo ""

# 7. Resumen
echo "════════════════════════════════════════════════════════════"
echo -e "${GREEN}✅ SISTEMA INICIADO CORRECTAMENTE${NC}"
echo "════════════════════════════════════════════════════════════"
echo ""
echo "📋 PIDs de procesos:"
echo "   Backend:  $BACKEND_PID"
echo "   Frontend: $FRONTEND_PID"
echo ""
echo "🌐 URLs:"
echo "   Backend API:  http://localhost:8080/api"
echo "   Frontend App: http://localhost:4200"
echo ""
echo "📝 Logs:"
echo "   Backend:  tail -f /tmp/backend.log"
echo "   Frontend: tail -f /tmp/frontend.log"
echo ""
echo "🛑 Para detener:"
echo "   kill $BACKEND_PID $FRONTEND_PID"
echo "   o ejecuta: ./stop.sh"
echo ""
echo "👥 Usuarios de prueba:"
echo "   Estudiante: juan.perez@ucb.edu.bo"
echo "   Docente:    maria.gonzalez@ucb.edu.bo"
echo "   Director:   carlos.rodriguez@ucb.edu.bo"
echo "   Password:   password123 (cualquiera)"
echo ""
echo "════════════════════════════════════════════════════════════"

# Guardar PIDs para el script de detención
echo "$BACKEND_PID" > /tmp/backend.pid
echo "$FRONTEND_PID" > /tmp/frontend.pid

echo ""
echo "💡 Abriendo navegador en 5 segundos..."
sleep 5
open http://localhost:4200 2>/dev/null || xdg-open http://localhost:4200 2>/dev/null || echo "Abre manualmente: http://localhost:4200"
