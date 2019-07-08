package press3AutomationTest;

import java.util.Hashtable;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import press3Helper.Helper;
import press3Helper.Log4Helper;
import press3Helper.ReportHelper;
import press3WebsiteAutomation.Login;

@Listeners(ReportHelper.class)
public class LoginAutoTest extends Helper {
  
  	
	

  
  @Test(dataProviderClass=Helper.class,dataProvider="excelData")
  public void LoginFunction(Hashtable<String,String> LoginCredetials) throws InterruptedException {
	  Login logn =  new Login();
	  System.out.println("login test" );
	  Log4Helper.writeData(LoginAutoTest.class.getName() , "Login Test" , Thread.currentThread().getStackTrace()[1].getLineNumber());
	  System.out.println(LoginCredetials.get("emailId") );
	  System.out.println(LoginCredetials.get("Password") );
	  logn.loginAndLogOut(LoginCredetials.get("emailId") , LoginCredetials.get("Password") ); 
  }
}
