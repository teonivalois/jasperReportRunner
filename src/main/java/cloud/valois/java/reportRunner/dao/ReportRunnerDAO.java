package cloud.valois.java.reportRunner.dao;

import java.io.ByteArrayInputStream;
import java.sql.Connection;
import java.util.Locale;
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

		Locale locale = Locale.forLanguageTag(parameters.getOrDefault("reportLocale", "en-US").toString());
		parameters.put("REPORT_LOCALE", locale);

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

		parameters.put("REPORT_LOCALE", Locale.forLanguageTag(parameters.getOrDefault("reportLocale", "en-US").toString()));
		parameters.put("JSON_LOCALE", Locale.forLanguageTag(parameters.getOrDefault("jsonLocale", "en-US").toString()));
		
		JsonDataSource jsonDataSource = new JsonDataSource(new ByteArrayInputStream(json.getBytes()));
		jsonDataSource.setDatePattern(parameters.getOrDefault("datePattern", "yyyy-MM-dd'T'HH:mm:ss").toString());
		jsonDataSource.setNumberPattern(parameters.getOrDefault("numberPattern", "#,##0.00#").toString());

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
