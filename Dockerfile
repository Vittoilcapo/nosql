FROM openjdk:17
ADD build/libs/nosql-0.0.1-SNAPSHOT.jar "app.jar"
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]

