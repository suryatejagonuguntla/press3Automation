package press3AutomationTest;

import org.testng.annotations.Test;

import press3Helper.Helper;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;

public class NewTest extends Helper {
  @Test
  public void ftest() {
	  System.out.println("This is TestNG-Maven Example");
  }
  
  @Test
  public void start() {
	  driver.get("https://www.facebook.com/");
	  System.out.println("The current working directory is " + domainUrl);
  }


}
