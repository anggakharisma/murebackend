# Use an official OpenJDK runtime as the base image
FROM amazoncorretto:18-alpine

# Set the working directory inside the container
WORKDIR /app

COPY . .
RUN ./gradlew bootJar --no-daemon

# Expose the port that your application listens on
EXPOSE 8080

# Run the application
CMD ["java", "-jar", "build/libs/murebackend-0.0.1-SNAPSHOT.jar"]
