package cloud.valois.java.reportRunner.controllers;

import org.springframework.web.bind.annotation.RestController;

import cloud.valois.java.reportRunner.models.ReportRequestModel;
import cloud.valois.java.reportRunner.services.ReportRunnerService;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;

import java.util.HashMap;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class ReportController {

	@Autowired
	private ReportRunnerService service;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public void index(@RequestParam("reportPath") String reportPath, HttpServletResponse response) {
		try {
			response.setContentType("application/pdf");

			JasperPrint print = service.run(reportPath, new HashMap<String, Object>(), null);
			JasperExportManager.exportReportToPdfStream(print, response.getOutputStream());
		} catch (Exception e) {
			e.printStackTrace();
			response.setStatus(500);
		}
	}

	@RequestMapping(value = "/generate", method = RequestMethod.POST)
	public void index(@RequestBody ReportRequestModel requestModel, HttpServletResponse response) {
		try {
			response.setContentType("application/pdf");

			JasperPrint print = service.run(requestModel.getReportPath(), requestModel.getParameters(), requestModel.getJsonDataSource());
			JasperExportManager.exportReportToPdfStream(print, response.getOutputStream());
		} catch (Exception e) {
			e.printStackTrace();
			response.setStatus(500);
		}
	}

}
