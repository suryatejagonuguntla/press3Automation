package press3WebsiteAutomation;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeMethod;

public class Login {
	
	
	
	public LoginProperties lg = new LoginProperties();
//	public Login(WebDriver webdriver)
//	{
//		System.out.println("inherited class");
//		lg = new LoginProperties(webdriver);
//		
//	}
	
	
	
	public void loginAndLogOut(String Email , String Password) throws InterruptedException 
	{
		
		checkLoginFunctionality( Email ,  Password );
		lg.LogOutClick();
	}
	
	public void checkLoginFunctionality(String Email , String Password ) throws InterruptedException
	{
		System.out.println(lg.txtLoginEmail.getText());
		
		lg.LoginEmailSendKeys(Email);
		lg.LoginPasswordSendKeys(Password);
		
		//lp.txtLoginEmail.sendKeys(Email);
		//lp.txtLoginPassword.sendKeys(Password);
		
		lg.LogInClick();
		
		Thread.sleep(1000);
		
		
	}
	
	public void  logOut ()
	{
		
	}
	

}
