package TestScripts;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;

import Generic.BaseLogics;
import POM.FormSubmission;
import POM.UserAuthentication;

public class VerifyRegistrationFlow extends BaseLogics {

	@Test
	public void EnterRegistartionForm() throws Exception 
	{
		parentTest = extent.createTest("VerifyRegistrationFlow");

		UserAuthentication UA = new UserAuthentication(driver);

		UA.OpenRegistartionForm();
		chiledTest = parentTest.createNode("OpenRegistartionForm");

		UA.FillRegistrationform();
		chiledTest = parentTest.createNode("FillRegistrationform");
		
		UA.CheckSelectCountryDropDown();
		chiledTest = parentTest.createNode("CheckSelectCountryDropDown");
		
		UA.VerifyListAccountTypes();
		chiledTest = parentTest.createNode("Verified_ListAccountTypes");	
		
	}
	
	@Test(dependsOnMethods="EnterRegistartionForm")
	public void VerifyFunction()
	{
		
	}
	
	
	
	

	
}
