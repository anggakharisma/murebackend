# Use an official OpenJDK runtime as the base image
FROM openjdk:17-jdk-slim

# Set the working directory inside the container
WORKDIR /app
RUN apt update
RUN apt install vim -y

COPY . .
RUN ./gradlew bootJar --no-daemon

# Expose the port that your application listens on
EXPOSE 8080

# Run the application
CMD ["java", "-jar", "build/libs/murebackend-0.0.1-SNAPSHOT.jar"]
