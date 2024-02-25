# Use an official OpenJDK runtime as a base image
FROM openjdk:17

# Set the working directory in the container
WORKDIR /app

# Copy the packaged JAR file into the container
COPY target/enseignement-0.0.1-SNAPSHOT.jar /app/app.jar

# Expose the port that the application will run on
EXPOSE 8082

# Specify the command to run on container startup
CMD ["java", "-jar", "app.jar"]
#sleepy joiley