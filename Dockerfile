FROM openjdk:17-jdk
LABEL maintainer="sunny"
ARG JAR_FILE=build/libs/karrot-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} karrot-springboot.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/karrot-springboot.jar"]