FROM fabric8/java-centos-openjdk11-jre
EXPOSE 8080
ADD target/ayo-conversion-0.0.1-SNAPSHOT.jar ayo-conversion.jar
ENTRYPOINT ["java", "-jar", "ayo-conversion.jar"]
