FROM openjdk:14
VOLUME /tmp
ADD ./target/s-usuarios-0.0.1-SNAPSHOT.jar servicio-usuarios.jar
ENTRYPOINT ["java","-jar","/servicio-usuarios.jar"]
