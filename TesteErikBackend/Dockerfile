FROM eclipse-temurin:17.0.8.1_1-jdk-jammy
COPY ./TesteErikBackend .
RUN ./mvnw clean install
ENTRYPOINT ["java", "-jar", "target/TesteErik-0.0.1-SNAPSHOT.jar"]