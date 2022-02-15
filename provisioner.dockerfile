FROM openjdk:17-alpine

ARG APP_NAME=provisioner
ARG VERSION=0.0.1-SNAPSHOT
ARG JAR_FILE=./build/libs/${APP_NAME}-${VERSION}.jar

WORKDIR /usr/local/runme

COPY ${JAR_FILE} app.jar

ENTRYPOINT ["java","--enable-preview","-jar","app.jar"]