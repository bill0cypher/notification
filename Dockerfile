FROM adoptopenjdk/openjdk15
EXPOSE 8081
COPY ./target/*.jar notification.jar
ENTRYPOINT ["java", "-jar", "/notification.jar"]