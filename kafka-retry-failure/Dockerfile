FROM adoptopenjdk/openjdk11:alpine

COPY target/*.jar kafka-k8s.jar

ENTRYPOINT ["java","-jar","/kafka-k8s.jar"]