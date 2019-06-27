package cloud.valois.java.reportRunner.dao;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

@Transactional
@Repository
public class ReportRunnerDAO {
	
	 @Autowired
	 @Qualifier("jdbcTemplate")
	 private JdbcTemplate jdbcTemplate;

	 public JasperPrint render(String reportPath, Map<String, Object> parameters) throws Exception {
		 JasperReport jasperReport = JasperCompileManager.compileReport(reportPath);
		
		  if(parameters == null)
			  parameters = new HashMap<String, Object>();
		
		  Connection conn = jdbcTemplate.getDataSource().getConnection();
		  JasperPrint print = JasperFillManager.fillReport(jasperReport, parameters, conn);
		  conn.close();
		  
		  return print;
	 }
}
