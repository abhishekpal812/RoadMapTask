FROM eclipse-temurin:17-jdk-focal
EXPOSE 8080
WORKDIR /app
ARG JAR_FILE=target/roadmap-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app/app.jar
ENTRYPOINT ["java","-jar","app/app.jar"]
