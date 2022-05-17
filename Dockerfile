FROM openjdk:17
VOLUME /tmp
COPY target/*.jar consumer.jar
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/urandom", "-jar", "/consumer.jar"]