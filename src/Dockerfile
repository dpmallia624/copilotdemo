FROM --platform=linux/amd64 eclipse-temurin:21-jdk-alpine as builder
WORKDIR /app
COPY . .
RUN ./gradlew clean build -x test

FROM --platform=linux/amd64 eclipse-temurin:21-jre-alpine
WORKDIR /app
COPY --from=builder /app/build/libs/copilotdemo.jar app.jar

# Install Datadog Java Agent
RUN wget -O dd-java-agent.jar https://dtdg.co/latest-java-tracer

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar", "param1=?"]