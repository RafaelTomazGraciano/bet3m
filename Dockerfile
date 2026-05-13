# ─────────────────────────────────────────
# Stage 1: Build
# ─────────────────────────────────────────
FROM eclipse-temurin:25-jdk AS builder

WORKDIR /app

# Copia os arquivos de configuração do Gradle primeiro (cache de dependências)
COPY gradle/ gradle/
COPY gradlew build.gradle settings.gradle ./

# Garante permissão de execução no gradlew
RUN chmod +x gradlew

# Baixa as dependências sem compilar (cache layer)
RUN ./gradlew dependencies --no-daemon

# Copia o restante do código e compila
COPY src/ src/
RUN ./gradlew bootJar --no-daemon -x test

# ─────────────────────────────────────────
# Stage 2: Runtime
# ─────────────────────────────────────────
FROM eclipse-temurin:25-jre

WORKDIR /app

# Copia apenas o jar gerado
COPY --from=builder /app/build/libs/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-Xmx400m", "-Xms128m", "-XX:+UseContainerSupport", "-XX:MaxRAMPercentage=75.0", "-jar", "app.jar"]