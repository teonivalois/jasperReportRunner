package cloud.valois.java.reportRunner.models;

import java.util.HashMap;
import java.util.Map;

public class ReportRequestModel {
	
	private String reportPath;
	private Map<String, Object> parameters;
	private Object jsonDataSource;

	public ReportRequestModel() {
		this.parameters = new HashMap<String, Object>();
	}
	
	public Object getJsonDataSource() {
		return jsonDataSource;
	}

	public void setJsonDataSource(Object jsonDataSource) {
		this.jsonDataSource = jsonDataSource;
	}
	
	public String getReportPath() {
		return reportPath;
	}
	
	public void setReportPath(String reportPath) {
		this.reportPath = reportPath;
	}
	
	public Map<String, Object> getParameters() {
		return parameters;
	}
	
	public void setParameters(Map<String, Object> parameters) {
		this.parameters = parameters;
	}
}
