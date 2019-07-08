package press3AutomationTest;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import press3Helper.Helper;
import press3Initialzers_Processors.GenericExcelProcesser;
import press3WebsiteAutomation.Login;

public class ScriptLocators extends Helper {

	@FindBy(how = How.XPATH, using = "//a[@id='createScript']")
	public WebElement btnCreateScript;

	@FindBy(how = How.XPATH, using = "//input[@type='file' and @value='Browse']")
	public WebElement btnUploadExcelFile;

	@FindBy(how = How.ID, using = "txtscriptTitle")
	public WebElement txtScriptTitle;

	@FindBy(how = How.ID, using = "selSkillGroup")
	public WebElement ddlSelctSkillGroup;

	@FindBy(how = How.ID, using = "defaultscript")
	public WebElement chkDefaultScript;

	@FindBy(how = How.XPATH, using = "//a[@id='composeScript']")
	public WebElement ancrComposeScript;

	@FindBy(how = How.XPATH, using = "//a[@id='upload']")
	public WebElement ancrUpload;
	@FindBy(how = How.XPATH, using = "//input[@value='Browse']")
	public WebElement BtnUploadExcel;

	@FindBy(how = How.CLASS_NAME, using = "newSectionClose")
	public WebElement imgSectionClose;

	@FindBy(how = How.ID, using = "addSection")
	public WebElement ancrAddSection;

	@FindBy(how = How.ID, using = "saveExcelfile")
	public WebElement btnSaveExcelFile;

	@FindBy(how = How.CLASS_NAME, using = "newTopic")
	public WebElement ancrNewTopic;

	@FindBy(how = How.ID, using = "sections")
	public WebElement divSections;

	@FindBy(how = How.ID, using = "saveScript")
	public WebElement saveComposeData;

	@FindBy(how = How.ID, using = "ddlsection_Sheet1")
	public WebElement ExcelSectionHeader;

	@FindBy(how = How.ID, using = "ddltopic_Sheet1")
	public WebElement ExcelTopicHeader;

	@FindBy(how = How.ID, using = "ddldescription_Sheet1")
	public WebElement ExcelDescHeader;

	@FindBy(how = How.XPATH, using = "//table[@class='script_list']/tbody")
	public WebElement ScriptsListBody;

	@FindBy(how = How.XPATH, using = "//table[contains(@class, script_list)]/tbody/tr")
	public List<WebElement> ScriptsListBodyTr;

	@FindBy(how = How.XPATH, using = "//div[@class='compose_panel']/div")
	public List<WebElement> divComposePanel;

	private String scriptName = "";
	int stTitleIndex = 0;
	int stSkillGroupIndex = 3;
	int stTotSectionCntIndex = 4;
	int stTottopicCntIndex = 5;
	int stActionIndex = 6;

	public ScriptLocators() {
		System.out.println("script constructor");
		PageFactory.initElements(driver, this);

	}

	public void fillScriptDetails(String titleName, String SkillGroup, String isDefault) throws InterruptedException {
		System.out.println("script fillScriptDetails");
		this.scriptName = titleName;
		Thread.sleep(3000);
		
		btnCreateScript.click();
		txtScriptTitle.sendKeys(titleName);
		if (isDefault == "1") {
			chkDefaultScript.click();
		}

		Select drop = new Select(ddlSelctSkillGroup);
		drop.selectByVisibleText(SkillGroup);

	}

	public void composeScript(String JsonData) throws InterruptedException {
		int topicCounts = 0;
		ancrComposeScript.click();
//		List<WebElement> sections =  divSections;

		Gson gson = new Gson();
		gson.toJson(JsonData);
		JsonParser parser = new JsonParser();
//		JsonObject rootObj = parser.parse(json).getAsJsonObject();
		JsonArray sectionArray = parser.parse(JsonData).getAsJsonArray();

		System.out.println(sectionArray.size());
		for (int index = 0; index < sectionArray.size(); index++) {

			JsonObject sectionJobj = sectionArray.get(index).getAsJsonObject();

			String section = sectionJobj.get("section").getAsString();

			WebElement sectionElement = divSections.findElements(By.className("newSection")).get(index);
			sectionElement.findElement(By.className("sectionName")).sendKeys(section);
			System.out.println("sectionJobj");
			System.out.println(sectionJobj.toString());
			JsonArray tokenArray = sectionJobj.get("topic").getAsJsonArray();
			JsonArray DescriptionArray = sectionJobj.get("Description").getAsJsonArray();
			int jIndex = 0;
			for (jIndex = 0; jIndex < tokenArray.size(); jIndex++) {
				topicCounts++;
				System.out.println("token array");
				System.out.println(jIndex);
				System.out.println(tokenArray.size());
				String token = tokenArray.get(jIndex).toString();
				token = token.substring(1, token.length());
				token = token.substring(0, token.length() - 1);

				String description = DescriptionArray.get(jIndex).toString();
				description = description.substring(1, description.length());
				description = description.substring(0, description.length() - 1);

				System.out.println(" get new element");
				WebElement topicElement = sectionElement.findElements(By.className("newTopics")).get(jIndex);

				System.out.println("after get new element");
				topicElement.findElement(By.className("topicName")).sendKeys(token);
				System.out.println(token);
				System.out.println(description);
				topicElement.findElement(By.className("topicDesc")).sendKeys(description);
				int tkIndex = jIndex;
				if (tkIndex + 1 < tokenArray.size()) {
					System.out.println("token array inside ");
					Thread.sleep(3000);
					sectionElement.findElement(By.className("newTopic")).click();
				}
			}

			if (index + 1 < sectionArray.size()) {
				ancrAddSection.click();
			}

		}

		saveComposeData.click();
		Thread.sleep(5000);

		alertsProcess("ok");
		
		ViewOrVerifyScript(scriptName,sectionArray , topicCounts);
	}

	public void uploadScript(String filepath) throws AWTException, InterruptedException, IOException {
		ancrUpload.click();
		Thread.sleep(5000);
		BtnUploadExcel.click();
		StringSelection strSel = new StringSelection(filepath);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(strSel, null);
		Thread.sleep(2000);
		// Create an object for robot class

		// Control key in the keyboard
		// Ctrl+V
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);

		Thread.sleep(2000);
		String SectionHeaderInExcel = "";
		String TopicHeaderInExcel = "";
		String DescHeaderInExcel = "";
		GenericExcelProcesser excel = new GenericExcelProcesser(filepath);
		SectionHeaderInExcel = excel.formatCell(0, 0);
		TopicHeaderInExcel = excel.formatCell(0, 1);
		DescHeaderInExcel = excel.formatCell(0, 2);

		selectDropDown(ExcelSectionHeader, "text", SectionHeaderInExcel);
		selectDropDown(ExcelTopicHeader, "text", TopicHeaderInExcel);
		selectDropDown(ExcelDescHeader, "text", DescHeaderInExcel);

		Thread.sleep(1000);

		btnSaveExcelFile.click();

		Thread.sleep(5000);

		alertsProcess("ok");
	}

	public void ViewOrVerifyScript(String title, JsonArray ScriptArray, int topicCount)
			throws InterruptedException {
		Thread.sleep(5000);
		List<WebElement> ScriptTrs = ScriptsListBodyTr;

		int trsSize = ScriptTrs.size();
		System.out.println("enter for ViewOrVerifyScript ");
		System.out.println(trsSize);
//		System.out.println(ScriptTrs);
		for (int trIndex = 0; trIndex < trsSize; trIndex++) {
			WebElement scriptTr = ScriptTrs.get(trIndex);
            System.out.println(scriptTr.findElements(By.tagName("td")).get(stTitleIndex).getText());
			if (scriptTr.findElements(By.tagName("td")).get(stTitleIndex).getText().equals(title)) {
				System.out.println("tr matched");
				List<WebElement> selectedScript = scriptTr.findElements(By.tagName("td"));
				String tdskillGroup = selectedScript.get(stSkillGroupIndex).getText();
				String tdSectionCnt = selectedScript.get(stTotSectionCntIndex).getText();
				String tdTopicCnt = selectedScript.get(stTottopicCntIndex).getText();

//				if (tdskillGroup.equals(skillGroup)) {
//					System.out.println("Skill group matched assertion");
//				}
				System.out.println("section count:");
				System.out.println(tdSectionCnt);
				System.out.println("topic count:");
				System.out.println(tdTopicCnt);
				if (tdSectionCnt.equals(String.valueOf(ScriptArray.size()))) {
					System.out.println("section count matched assertion");

				}
				if (tdTopicCnt.equals(String.valueOf(topicCount))) {
					System.out.println("topic count matched assertion");
				}
				Thread.sleep(3000);
				selectedScript.get(stActionIndex).findElement(By.cssSelector("[title=View]")).click();

				Thread.sleep(3000);
				

				JsonObject scriptJobj ;
				//String section = scriptJobj.get("section").getAsString();

				JsonArray tokenArray = null;
				JsonArray DescriptionArray =null;

				List<WebElement> scriptsDivs = divComposePanel;
				int topicIndex = 0;
				int sectionIndex = 0;
				for (int j = 0; j < scriptsDivs.size(); j++) {

					WebElement scriptDiv = scriptsDivs.get(j);
					if (j == 0) {
						if (scriptDiv.findElement(By.id("scriptName_")).getText().equals(title)) {
							System.out.println("compose title matched assertion");
						}
//						if (divComposePanel.findElement(By.className("label_round_sm")).getText().equals(skillGroup)) {
//							System.out.println("compose skillGroup matched assertion");
//						}
					} else {
						String ClassName = scriptDiv.getAttribute("class");
						System.out.println(ClassName);
						if (ClassName.equals("form-group")) {
							System.out.println("verify new section");
							String sectionElement = scriptDiv.findElements(By.tagName("label")).get(1).getText();
							topicIndex = 0;
							scriptJobj = ScriptArray.get(sectionIndex).getAsJsonObject();
							sectionIndex++;

							tokenArray = scriptJobj.get("topic").getAsJsonArray();
							DescriptionArray = scriptJobj.get("Description").getAsJsonArray();
							String sectionJobj = scriptJobj.get("section").getAsString();
							
							if(sectionElement.equals(sectionJobj))
							{
								System.out.println(sectionElement + " equals");
							}
							
						} else if (ClassName.equals("lite")) {
							System.out.println("verify new topic");
							String Topic = scriptDiv.findElements(By.tagName("label")).get(1).getText();
							String Desc = scriptDiv.findElement(By.tagName("p")).getText();

							String TopicJobjString = tokenArray.get(topicIndex).toString();

							TopicJobjString = TopicJobjString.substring(1, TopicJobjString.length());
							TopicJobjString = TopicJobjString.substring(0, TopicJobjString.length() - 1);

							String DescJobjString = DescriptionArray.get(topicIndex).toString();

							DescJobjString = DescJobjString.substring(1, DescJobjString.length());
							DescJobjString = DescJobjString.substring(0, DescJobjString.length() - 1);
							
							topicIndex++;

							if (Topic.equals(TopicJobjString)) {
                                System.out.println(Topic + " equals");
							}
							if (Desc.equals(DescJobjString)) {
                                System.out.println(Desc + " equals");
							}
						}

					}

				}
				break;
			}
			else
			{
				continue;
			}
		}

	}
}
