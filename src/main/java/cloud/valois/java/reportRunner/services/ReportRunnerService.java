package cloud.valois.java.reportRunner.services;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cloud.valois.java.reportRunner.dao.ReportRunnerDAO;
import net.sf.jasperreports.engine.JasperPrint;

@Service
public class ReportRunnerService {
	 
	@Autowired
	 private ReportRunnerDAO reportRunnerDAO;
	 
	 public JasperPrint run(String reportPath, Map<String, Object> parameters) throws Exception {
		 Path path = Paths.get("/opt/jasper/reports", reportPath);
		 return reportRunnerDAO.render(path.toString(), parameters);
	 }
}
