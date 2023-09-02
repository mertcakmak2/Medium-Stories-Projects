FROM maven:3.8.7-openjdk-18-slim AS build
COPY ./src /usr/src/app/src
COPY ./pom.xml /usr/src/app
RUN mvn -f /usr/src/app/pom.xml clean package -D skipTests

FROM openjdk:18.0-jdk-slim
COPY --from=build /usr/src/app/target/*.jar /usr/local/lib/spring-app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/usr/local/lib/spring-app.jar"]
