# define base docker image

FROM openjdk:11
LABEL maintainer="hopechijuka.com"
ADD target/Week-9-task-0.0.1-SNAPSHOT.jar Blog-Api.jar
ENTRYPOINT ["java", "-jar", "Blog-Api.jar"]