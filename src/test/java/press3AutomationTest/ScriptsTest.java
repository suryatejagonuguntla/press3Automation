package press3AutomationTest;

import java.awt.AWTException;
import java.io.IOException;
import java.util.Hashtable;

import org.openqa.selenium.By;
import org.openqa.selenium.By.ById;
import org.testng.annotations.Test;

import press3Helper.Helper;
import press3WebsiteAutomation.Login;

public class ScriptsTest extends Helper {

//	static Login logn =  new Login(driver);
//	static 
//	{
//		try {
//			logn.checkLoginFunctionality("suryateja.gonuguntla@smscountry.com", "Surya@440" );
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}

	@Test(dataProviderClass = Helper.class, dataProvider = "excelData")
	public void createScripts(Hashtable<String, String> ScriptsData) throws InterruptedException, AWTException, IOException {
        Thread.sleep(2000);
		ScriptLocators sl = new ScriptLocators();
		if (isLoggedIn.equalsIgnoreCase("0")) {
			Login lg = new Login();
			lg.checkLoginFunctionality("suryateja.gonuguntla@smscountry.com", "Surya@440");
			Thread.sleep(5000);
			hover(driver.findElement(By.id("SideBarToggle4")));
			
			driver.findElement(By.xpath("//a[@href='/Scripts.aspx']")).click();
			isLoggedIn = "1";
		} else {

		}
		String isCompose = ScriptsData.get("iscompose").toString();
		String data = ScriptsData.get("Data").toString();

		sl.fillScriptDetails(ScriptsData.get("titleName").toString(), ScriptsData.get("skillgroup").toString(),
				ScriptsData.get("isDefault").toString());

		if (isCompose.equalsIgnoreCase("1")) {
			System.out.println("iscompose 1");
			sl.composeScript(data);

		} else if (isCompose.equalsIgnoreCase("0")) {
			System.out.println("iscompose 0");
			sl.uploadScript(data);

		}

	}
}
