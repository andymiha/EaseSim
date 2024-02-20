# Use an official OpenJDK 17 image as the base image
FROM openjdk:17-slim

# WORKDIR /var/www/html
# CMD ["./gradlew", "clean", "bootJar"]

# COPY build/libs/*.jar app.jar
COPY ./start.sh /start.sh
RUN chmod 755 /start.sh

EXPOSE 4024

ENTRYPOINT ["./start.sh"]
# ENTRYPOINT ["java", "-jar", "./app.jar"]