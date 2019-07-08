package press3Initialzers_Processors;

import java.io.File;

import com.relevantcodes.extentreports.DisplayOrder;
import com.relevantcodes.extentreports.ExtentReports;

public class ExtentReportIntializer {
    
	private static ExtentReports extent;
	
	public static ExtentReports getInstance(){
		
		if(extent==null){
			
			
			extent = new ExtentReports(System.getProperty("user.dir")+"\\test-output\\extentReport.html",true,DisplayOrder.OLDEST_FIRST);
			extent.loadConfig(new File(System.getProperty("user.dir")+"\\Configs\\ReportsConfig.xml"));
			
		}
		
		return extent;
		
	}

}
