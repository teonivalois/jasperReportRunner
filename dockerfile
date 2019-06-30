FROM openjdk:13-oracle as builder
COPY . /src
WORKDIR /src
RUN ./mvnw clean \
	&& ./mvnw package

FROM openjdk:13-oracle
VOLUME /tmp
COPY --from=builder /src/target/reportRunner.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]