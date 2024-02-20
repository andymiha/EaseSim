#!/bin/bash
cd /var/www/html

./gradlew clean bootJar

latest_jar=$(ls -Art build/libs/*.jar | tail -n 1)
cp "$latest_jar" app.jar

touch //var/www/html/.setup_completed
