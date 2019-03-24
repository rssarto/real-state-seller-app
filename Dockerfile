FROM openjdk:8-jdk-alpine
VOLUME /tmp
ADD target/real-state-seller-app-0.0.1-SNAPSHOT.jar target/app.jar
RUN /bin/sh -c 'touch target/app.jar'
ENTRYPOINT ["java","-jar","-Dspring.profiles.active=local","target/app.jar"]