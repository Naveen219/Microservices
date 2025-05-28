#Start with a base image containing Java 17 runtime
FROM openjdk:17-jdk-slim

# Information about the maintainer of this image
MAINTAINER "Naveen Kumar"

# Add the application's jar to the container
COPY target/account-service-0.0.1-SNAPSHOT.jar accounts-0.0.1-SNAPSHOT.jar

# Expose the port on which the application will run
EXPOSE 8080

# Set the command to run the application
ENTRYPOINT ["java", "-jar", "accounts-0.0.1-SNAPSHOT.jar"]