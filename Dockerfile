FROM openjdk:17
WORKDIR /app
COPY target/estoque-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "--add-opens", "java.base/jdk.internal.platform=ALL-UNNAMED", "-jar", "app.jar"]

