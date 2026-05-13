# ─────────────────────────────────────────
# Stage 1: Build
# ─────────────────────────────────────────
FROM eclipse-temurin:25-jdk AS builder

WORKDIR /app

COPY gradle/ gradle/
COPY gradlew build.gradle settings.gradle ./

RUN chmod +x gradlew

RUN ./gradlew dependencies --no-daemon

COPY src/ src/
RUN ./gradlew bootJar --no-daemon -x test

# ─────────────────────────────────────────
# Stage 2: Runtime
# ─────────────────────────────────────────
FROM eclipse-temurin:25-jre

WORKDIR /app

COPY --from=builder /app/build/libs/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]