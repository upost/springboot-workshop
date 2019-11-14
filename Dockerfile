FROM openjdk:8-jdk-alpine
VOLUME /tmp
ARG JAR_FILE
COPY ${JAR_FILE} /app.jar
RUN addgroup bootapp && adduser -D -S -h /var/cache/bootapp -s /sbin/nologin -G bootapp bootapp

ENTRYPOINT ["java","","-jar","/app.jar"]
