# Getting Started

### Design the reports
To design the reports, use the [Jaspersoft Studio](https://community.jaspersoft.com/project/jaspersoft-studio). There's a community version available for free.

There's a sample report in the *samples* folder.

### Build
```
docker build . -t teonivalois/jasper-report-runner:latest
```

### Run
```
docker run --rm \
	-p 8080:8080 \
	-v $(pwd)/samples:/opt/jasper/reports \
	--name report-runner \
	teonivalois/jasper-report-runner:latest
```
	
### Check
```
	http://localhost:8080/?reportPath=Blank_A4.jrxml
```
