package keerthanaAutomation.resources;


import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportsNG {
	
	public static ExtentReports getTestReport() {
		
		String path= System.getProperty("user.dir")+"//reports//index.html";
		
		ExtentSparkReporter report= new ExtentSparkReporter(path);
		report.config().setReportName("Web Let's Shop Automation");
		report.config().setDocumentTitle("Test Report");
		
	  ExtentReports extent=new ExtentReports();
	  extent.attachReporter(report);
	  extent.setSystemInfo("Tester","Keerthana");
	  
	       return extent;
		
	
	}

}
