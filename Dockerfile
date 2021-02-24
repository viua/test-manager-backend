FROM openjdk:11
EXPOSE 8080
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} test-manager.jar
ENTRYPOINT ["java","-jar","/test-manager.jar"]

# docker build -t test-manager:1.0 -f src/main/docker/Dockerfile .
