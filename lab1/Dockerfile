FROM sbtscala/scala-sbt:eclipse-temurin-24.0.1_9_1.11.7_3.7.3

WORKDIR /app

COPY build.sbt ./

RUN sbt update

COPY solutions/ ./solutions/

EXPOSE 8080

ENTRYPOINT ["sbt", "solutions/run"]