FROM amazoncorretto:18-alpine

WORKDIR /app

COPY . .
RUN ./gradlew bootJar --no-daemon

EXPOSE 8080

CMD ["java", "-jar", "build/libs/murebackend-0.0.1-SNAPSHOT.jar"]
