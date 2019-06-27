# Getting Started

### Build
```
./mvnw clean \
	&& ./mvnw package \
	&& docker build . \
		--build-arg JAR_FILE=./target/reportRunner-1.0.0.jar \
		-t teonivalois/report-runner:1.0.0 \
	&& ./mvnw clean
```

### Run
```
docker run --rm \
	-p 8080:8080 \
	-e DATABASE_JDBC_URL=jdbc:postgresql://host.docker.internal:5432/plan4trip \
	-e DATABASE_DRIVER_CLASSNAME=org.postgresql.Driver \
	-e DATABASE_USERNAME=teoni \
	-e DATABASE_PASSWORD= \
	-v $(pwd)/samples:/opt/jasper/reports \
	--name report-runner \
	teonivalois/report-runner:1.0.0
```
	
### Check
```
http://localhost:8080/?reportPath=Blank_A4.jrxml
```