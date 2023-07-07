FROM eclipse-temurin:17-jdk-alpine

VOLUME /com/loy/security

COPY target/*.jar app.jar

EXPOSE 8081:8081

ENTRYPOINT ["java","-jar","/app.jar"]