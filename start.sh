#!/bin/bash

# DistriCore Application Startup Script
# This script helps you run the DistriCore API with external PostgreSQL and Redis

set -e

echo "=========================================="
echo "DistriCore API - Startup Script"
echo "=========================================="
echo ""

# Check if Docker is available and has proper permissions
check_docker() {
    if ! command -v docker &> /dev/null; then
        echo "⚠️  Docker not found. Please install Docker or start PostgreSQL/Redis manually."
        return 1
    fi
    
    # Try to run a simple docker command
    if ! docker ps &> /dev/null; then
        echo "⚠️  Docker permission denied. Run:"
        echo "  sudo usermod -aG docker \$USER && newgrp docker"
        return 1
    fi
    
    return 0
}

# Start services with Docker Compose
start_docker_services() {
    echo "🐳 Starting PostgreSQL and Redis with Docker Compose..."
    docker compose up -d
    
    # Wait for services to be ready
    echo "⏳ Waiting for services to be ready..."
    sleep 5
    
    # Check if healthy
    if docker compose ps | grep -q "healthy"; then
        echo "✅ Services are ready!"
    else
        echo "⏳ Waiting a bit more for database to initialize..."
        sleep 5
    fi
    
    return 0
}

# Check if services are running
check_services() {
    echo ""
    echo "📋 Checking service status..."
    docker compose ps
    echo ""
}

# Run the application
run_application() {
    echo "🚀 Starting DistriCore API..."
    echo "   Waiting for Spring Boot to initialize..."
    java -jar target/districore-api-0.1.0.jar
}

# Main execution
main() {
    # Check if JAR exists
    if [ ! -f "target/districore-api-0.1.0.jar" ]; then
        echo "❌ JAR file not found! Run: mvn clean package -DskipTests"
        exit 1
    fi
    
    # Try to check and start Docker services
    if check_docker; then
        start_docker_services
        check_services
        run_application
    else
        echo ""
        echo "⚠️  Docker unavailable. Please start PostgreSQL and Redis manually, then run:"
        echo "  java -jar target/districore-api-0.1.0.jar"
        exit 1
    fi
}

# Run main function
main