package press3Helper;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Hashtable;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.openqa.selenium.Alert;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

import press3Initialzers_Processors.ExtentReportIntializer;
import press3Initialzers_Processors.GenericExcelProcesser;

public class Helper {

	public static String projectDirectory = System.getProperty("user.dir");
	public static String domainUrl = "";
	public static WebDriver driver;
	public static String className = "";
	public ExtentReports exReportMessenger = ExtentReportIntializer.getInstance();

	public static ExtentTest logReportTestCase;
	public static String isLoggedIn = "0";

	Properties prop = new Properties();

	String globalPropertiesFile = projectDirectory + "\\src\\test\\Config\\globalConfig.properties";
	FileInputStream inputStream;

	@BeforeMethod
	public void beforeMethod() {
		
	}

	@AfterMethod
	public void afterMethod() {
	}

	@BeforeClass
	public void beforeClass() {
		className = this.getClass().getName().toString();

	}

	@AfterClass
	public void afterClass() {
	}

	@BeforeTest
	public void beforeTest() {
		System.setProperty("webdriver.gecko.driver", projectDirectory + "\\Drivers\\geckodriver.exe");
		// System.setProperty("webdriver.chrome.driver",
		// projectDirectory+"\\Drivers\\chromedriver.exe" );
		System.out.println("Start before method");
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		driver.get(Helper.domainUrl);
	}

	@AfterTest
	public void afterTest() {
	}

	@BeforeSuite
	public void beforeSuite() throws IOException {
		Log4Helper.log4Initializer();
		inputStream = new FileInputStream(globalPropertiesFile);
		if (inputStream != null) {
			prop.load(inputStream);
			domainUrl = prop.getProperty("domainUrl");

		} else {
			throw new FileNotFoundException("property file '" + globalPropertiesFile + "' not found in the classpath");
		}

	}

	@AfterSuite
	public void afterSuite() {

	}

	public static void hover(WebElement element) {
		Actions action = new Actions(driver);
		action.moveToElement(element).perform();
	}

	@DataProvider(name = "excelData")
	public Object[][] getExcelData(Method m) throws IOException {

		String repClass = className.replace(".", ",");

		String[] fileArray = repClass.split(",", 0);

		String filePath = projectDirectory + "\\src\\test\\resources\\ExcelFiles\\" + fileArray[1] + ".xlsx";
		System.out.println(filePath);
		GenericExcelProcesser excel = new GenericExcelProcesser(filePath);
		System.out.println("Enter Data Provider");

		int rows = excel.readRowsCount();
		int cols = excel.readColsCount();

		String[] ColoumnsArray = excel.getColoumnsArray();
		Object[][] data = new Object[rows][1];

		Hashtable<String, String> table = null;

		for (int rowNum = 1; rowNum <= rows; rowNum++) { // 2

			table = new Hashtable<String, String>();

			for (int colNum = 0; colNum < cols; colNum++) {

				System.out.println(ColoumnsArray[colNum]);
				System.out.println(excel.formatCell(rowNum, colNum).toString());

				table.put(ColoumnsArray[colNum], excel.formatCell(rowNum, colNum).toString());
				System.out.println("after adding Value");
			}
			data[rowNum - 1][0] = table;

		}

		return data;

	}

	public void selectDropDown(WebElement ddlElement , String type ,String param)
	{
		
		 Select drop = new Select(ddlElement);
		 if(type.equalsIgnoreCase("text"))
		 {
			 drop.selectByVisibleText(param); 
		 }
	        
	}
	
	public void alertsProcess(String type)
	{
		Alert alert = driver.switchTo().alert();
		if(type.equalsIgnoreCase("ok"))
		{
			alert.accept();

		}
	}
	// take screen shot
	public static void takeSnapShot(String fileWithPath) throws Exception {

		// Convert web driver object to TakeScreenshot
		TakesScreenshot scrShot = ((TakesScreenshot) driver);
		// Call getScreenshotAs method to create image file
		File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);
		// Move image file to new destination
		File DestFile = new File(fileWithPath);
		// Copy file at destination
		FileUtils.copyFile(SrcFile, DestFile);

	}
	
	public  JsonObject ConvertExcelJson(Hashtable<String, String> excelInfo  ) throws IOException
	{
		JsonObject resJson = new JsonObject(); 
		String filePath = "",hasHeaders ="0" ,ignoreSpacesAndAddToPreviousObj = "0";
		String isAddChildObjectNameInArrays = "0", arraysForWhichColoumns="";
		
		String[] arraysForWhichColoumnsArray = new String[100];
		
		if(excelInfo.containsKey("filepath"))
		{
			filePath =	excelInfo.get(filePath).toString();
		}
		if(excelInfo.containsKey("hasHeader"))
		{
			hasHeaders =	excelInfo.get("hasHeaders").toString();
		}
		if(excelInfo.containsKey("ignoreSpacesAndAddToPreviousObj"))
		{
			ignoreSpacesAndAddToPreviousObj = excelInfo.get("ignoreSpacesAndAddToPreviousObj").toString();
		}
		if(excelInfo.containsKey("isAddChildObjectNameInArrays"))
		{
			isAddChildObjectNameInArrays = excelInfo.get("isAddChildObjectNameInArrays").toString();
		}
		if(excelInfo.containsKey("isAddChildObjectNameInArrays"))
		{
			isAddChildObjectNameInArrays = excelInfo.get("isAddChildObjectNameInArrays").toString();
		}
		if(excelInfo.containsKey("arraysForWhichColoumns"))
		{
			arraysForWhichColoumns = excelInfo.get("arraysForWhichColoumns").toString();
			arraysForWhichColoumnsArray = arraysForWhichColoumns.split(",");
		}
		
		GenericExcelProcesser excel = new GenericExcelProcesser(filePath);
		System.out.println("Enter Data Provider");
		
		int rows = excel.readRowsCount();
		int cols = excel.readColsCount();

		String[] ColoumnsArray = excel.getColoumnsArray();

		JsonArray resArray = null; 
		JsonObject rowObj = new JsonObject(); 
		for (int rowNum = 0; rowNum < rows; rowNum++) { // 2
            
			String isFirstColNull = "0";
			if((excel.formatCell(rowNum, 0).toString().isEmpty() || excel.formatCell(rowNum, 0).toString() == null ))
			{
				isFirstColNull = "1";
				
			}
			else
			{
				rowObj = new JsonObject(); 
			}
			

			for (int colNum = 0; colNum < cols; colNum++) {
          
				if(isFirstColNull != "1")
				{
					System.out.println(ColoumnsArray[colNum]);
					System.out.println(excel.formatCell(rowNum, colNum).toString());

				//	table.put(ColoumnsArray[colNum], excel.formatCell(rowNum, colNum).toString());
					System.out.println("after adding Value");
				}
				else 
				{
					
				}
           }
			
			
			

		}

		return resJson;
	}

}
