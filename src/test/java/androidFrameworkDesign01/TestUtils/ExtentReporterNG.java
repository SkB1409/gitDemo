package androidFrameworkDesign.TestUtils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG {
	
	static ExtentReports extent;
	
	public static ExtentReports getReporterObject() {
		
		String path = System.getProperty("user.dir")+"ExtentReportResults.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		reporter.config().setReportName("Automation Test results");
		reporter.config().setDocumentTitle("Extent Report Results for Automation Tests");

		extent = new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester", "Khadar Basha Sk");
		return extent;
	}

}
