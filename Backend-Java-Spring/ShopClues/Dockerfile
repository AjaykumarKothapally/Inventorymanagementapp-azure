# Use OpenJDK 17 as the base image
FROM openjdk:17-jdk-alpine

# Install necessary dependencies including AWS CLI
RUN apk update && \
    apk add --no-cache python3 py3-pip && \
    pip3 install --upgrade pip awscli && \
    apk del py3-pip && \
    rm -rf /var/cache/apk/*

# Set the working directory inside the container
WORKDIR /app

# Copy the packaged JAR file into the container
COPY target/*.jar /app/app.jar

# Expose the port that your Spring Boot application uses
EXPOSE 8080

# Create a non-root user and switch to that user
RUN adduser -D myuser
USER myuser

# Command to run the application when the container starts
CMD ["java", "-jar", "app.jar"]
