package cloud.valois.java.reportRunner.dao;

import java.io.ByteArrayInputStream;
import java.sql.Connection;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JsonDataSource;

@Transactional
@Repository
public class ReportRunnerDAO {

	@Autowired
	@Qualifier("jdbcTemplate")
	private JdbcTemplate jdbcTemplate;

	public JasperPrint render(String reportPath, Map<String, Object> parameters) throws Exception {
		Connection conn = jdbcTemplate.getDataSource().getConnection();

		JasperPrint print = null;
		if (reportPath.endsWith(".jrxml")) {
			JasperReport jasperReport = JasperCompileManager.compileReport(reportPath);
			print = JasperFillManager.fillReport(jasperReport, parameters, conn);
		} else {
			print = JasperFillManager.fillReport(reportPath, parameters, conn);
		}
		
		conn.close();

		return print;
	}

	public JasperPrint render(String reportPath, Map<String, Object> parameters, Object datasource) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(datasource);

		JsonDataSource jsonDataSource = new JsonDataSource(new ByteArrayInputStream(json.getBytes()));
		
		JasperPrint print = null;
		if (reportPath.endsWith(".jrxml")) {
			JasperReport jasperReport = JasperCompileManager.compileReport(reportPath);
			print = JasperFillManager.fillReport(jasperReport, parameters, jsonDataSource);
		} else {
			print = JasperFillManager.fillReport(reportPath, parameters, jsonDataSource);
		}
		return print;
	}
}
