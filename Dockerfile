FROM eclipse-temurin:21-jre-alpine
WORKDIR /app

COPY build/libs/demo-0.0.1-SNAPSHOT.jar app.jar

RUN addgroup -S spring && \
    adduser -S spring -G spring

USER spring:spring

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]