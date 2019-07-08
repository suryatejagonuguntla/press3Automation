package press3WebsiteAutomation;

 
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import press3Helper.Helper;;

public class LoginProperties extends Helper {

	
	
	@FindBy(how = How.ID, using = "txtName")
    public WebElement txtLoginEmail;
	
	@FindBy(how = How.ID, using = "txtPassword")
    private WebElement txtLoginPassword;
	
	@FindBy(how = How.ID, using = "btnLogin")
	private WebElement btnLogin;
	
	@FindBy(how = How.CLASS_NAME, using = "dropdown-user")
	private WebElement ddlLogOut;
	
	@FindBy(how = How.ID, using = "logout_modal")
	private WebElement btnLogOut;
	
	@FindBy(how = How.NAME ,using = "ctl00$ctl15")
	private WebElement btdModalLogOut;
	
	public LoginProperties()
	{
//		super(webdriver);
//		System.out.println("super class");
//	this.driver =  webdriver;
		PageFactory.initElements(this.driver, this);
		
	}
	
	public void LoginEmailSendKeys(String Email)
	{
		txtLoginEmail.sendKeys(Email);
	}
	public void LoginPasswordSendKeys(String password)
	{
		txtLoginPassword.sendKeys(password);
	}
	
	public void LogInClick()
	{
		btnLogin.click();
	}
	
	public void LogOutClick()
	{
		hover(this.ddlLogOut);
        btnLogOut.click();
        btdModalLogOut.click();
	}
	
	
}

