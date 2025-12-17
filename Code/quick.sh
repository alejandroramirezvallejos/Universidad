#!/bin/bash

# ============================================
# COMANDOS RÁPIDOS - SISTEMA UNIVERSIDAD
# ============================================

case "$1" in
  "compile-backend")
    echo "🔨 Compilando Backend..."
    cd Server && ./gradlew build -x test
    ;;
    
  "start-backend")
    echo "🚀 Iniciando Backend..."
    cd Server && ./gradlew bootRun
    ;;
    
  "start-frontend")
    echo "🚀 Iniciando Frontend..."
    cd Client && ng serve
    ;;
    
  "stop-all")
    echo "🛑 Deteniendo todos los servicios..."
    lsof -ti:8080 | xargs kill -9 2>/dev/null || true
    lsof -ti:4200 | xargs kill -9 2>/dev/null || true
    echo "✅ Servicios detenidos"
    ;;
    
  "restart")
    echo "🔄 Reiniciando sistema completo..."
    ./restart-all.sh
    ;;
    
  "clean")
    echo "🧹 Limpiando builds..."
    cd Server && ./gradlew clean
    cd ../Client && rm -rf .angular dist node_modules/.vite
    echo "✅ Builds limpiados"
    ;;
    
  *)
    echo "======================================"
    echo "  COMANDOS DISPONIBLES"
    echo "======================================"
    echo ""
    echo "  ./quick.sh compile-backend   - Compilar solo el backend"
    echo "  ./quick.sh start-backend     - Iniciar solo el backend"
    echo "  ./quick.sh start-frontend    - Iniciar solo el frontend"
    echo "  ./quick.sh stop-all          - Detener todos los servicios"
    echo "  ./quick.sh restart           - Reiniciar sistema completo"
    echo "  ./quick.sh clean             - Limpiar builds"
    echo ""
    echo "======================================"
    ;;
esac
