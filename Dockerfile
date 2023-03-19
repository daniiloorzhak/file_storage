FROM openjdk:17
COPY build/libs/template-0.0.1.jar application.jar
CMD ["java", "-jar", "application.jar"]