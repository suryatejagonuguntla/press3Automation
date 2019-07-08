package press3AutomationTest;

import java.awt.AWTException;
import java.io.IOException;
import java.util.Hashtable;

import org.testng.annotations.Test;

import press3Helper.Helper;

public class ScriptLocatorsTest {
	ScriptLocators sl = new ScriptLocators();
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
	
	
	
	@Test(dataProviderClass=Helper.class,dataProvider="excelData")
	public void createScripts(Hashtable<String,String> ScriptsData) throws InterruptedException, AWTException, IOException {
	     
		
		String isCompose = ScriptsData.get("iscompose").toString();
		String data = ScriptsData.get("Data").toString();
		
		sl.fillScriptDetails(ScriptsData.get("titleName").toString() , 
				             ScriptsData.get("skillgroup").toString() , 
				             ScriptsData.get("isDefault").toString());
		
		if(isCompose == "1")
		{
			sl.composeScript(data);
			System.out.println("iscompose 1");
		}
		else if(isCompose == "0")
		{
			sl.uploadScript(data);
			System.out.println("iscompose 0");
		}
		
	}
}
