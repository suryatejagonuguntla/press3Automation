package press3AutomationTest;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import press3Helper.Helper;

public class NewTest extends Helper {

	@Parameters({ "username", "password", "profilename" })
	@Test
	public void ftest(String username, String password, String profilename) throws InterruptedException {
		System.setProperty("webdriver.gecko.driver", projectDirectory + "\\Drivers\\geckodriver.exe");

		FirefoxProfile profile = new FirefoxProfile(
				new File("C:\\Users\\Administrator\\AppData\\Roaming\\Mozilla\\Firefox\\Profiles\\" + profilename));
		FirefoxOptions opts = new FirefoxOptions();

		opts.setProfile(profile);
//	  FirefoxProfile profile =new FirefoxProfile(new File("C:\\Users\\sys\\AppData\\Roaming\\Mozilla\\Firefox\\Profiles\\ejtrne37.QAProfile"));

		WebDriver driver1 = new FirefoxDriver(opts);
		driver1.get("http://qa.press3.com/");
		Thread.sleep(5000);
		DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
		Date dateobj = new Date();
		System.out.println(df.format(dateobj));

		driver1.findElement(By.id("txtName")).sendKeys(username);
		System.out.println("This is TestNG-Maven Example1 message1 " + username);
		Thread.sleep(5000);
		dateobj = new Date();
		System.out.println(df.format(dateobj));
		System.out.println("This is TestNG-Maven Example1 message2 " + username);
		driver1.findElement(By.id("txtPassword")).sendKeys(password);
		Thread.sleep(5000);
		dateobj = new Date();
		System.out.println(df.format(dateobj));
		System.out.println("login done " + username);

		driver1.findElement(By.id("btnLogin")).click();

		Thread.sleep(10000);
		System.out.println("before hover " + username);
		Actions action = new Actions(driver1);
		action.moveToElement(driver1.findElement(By.id("SideBarToggle2"))).perform();
		

		driver1.findElement(By.xpath("//a[@href='/CallHistory.aspx']")).click();
		System.out.println("ftest Example1 complete " + username);
	}

	@Test
	public void start() throws InterruptedException {
//	  driver.get("https://www.facebook.com/");
//		System.out.println("The current working directory is " + domainUrl);
//		driver.get("https://www.facebook.com/");
		Thread.sleep(5000);
	}

}
