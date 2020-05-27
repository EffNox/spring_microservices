FROM openjdk:14
VOLUME /tmp
ADD ./target/s-productos-0.0.1.jar servicio-productos.jar
ENTRYPOINT ["java","-jar","/servicio-productos.jar"]
