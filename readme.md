# Getting Started

### Design the reports
To design the reports, use the [Jaspersoft Studio](https://community.jaspersoft.com/project/jaspersoft-studio). There's a community version available for free.

There's a sample report in the *samples* folder.

### Build
```
docker build . -t teonivalois/jasper-report-runner:1.0.0
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
	teonivalois/jasper-report-runner:latest
```
	
### Check
```
http://localhost:8080/?reportPath=Blank_A4.jrxml
```
