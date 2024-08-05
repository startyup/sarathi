# For Java 11, try this
FROM openjdk:17-jre-slim-buster

# Refer to Maven build -> finalName
ARG JAR_FILE=target/sarathi-0.0.1-SNAPSHOT.jar

# cd /opt/app
WORKDIR /opt/app

# cp target/spring-boot-web.jar /opt/app/app.jar
COPY ${JAR_FILE} app.jar

env java_opts="-xx:permsize=4096m -xx:maxpermsize=4096m"
# java -jar /opt/app/app.jar
ENTRYPOINT ["java","-jar","app.jar"]
