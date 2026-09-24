package TestScripts;

import org.testng.annotations.Test;

import Generic.BaseLogics;
import POM.HeaderLinks;

public class Verify_OpeningNewWebsitesandSwitch extends BaseLogics {
	@Test
	public void CheckWindowHandlesWorkingExpected() throws Exception {
		
		HeaderLinks HL = new HeaderLinks(driver);
		HL.OpenQATopics_DEscussio_TagsInNewTab();
		
		Thread.sleep(3000);
		
		HL.WindowHandles();

	}
}
