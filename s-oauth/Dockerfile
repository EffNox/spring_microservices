FROM openjdk:14
VOLUME /tmp
EXPOSE 9100
ADD ./target/s-oauth-0.0.1.jar servicio-oauth.jar
ENTRYPOINT ["java","-jar","/servicio-oauth.jar"]
