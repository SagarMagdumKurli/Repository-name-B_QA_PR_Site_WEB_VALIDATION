package TestScripts;

import org.testng.annotations.Test;

import Generic.BaseLogics;
import POM.FormSubmission;

public class VerifyformSubmission extends BaseLogics {

	@Test
	public void formsubmission() throws Exception {

		FormSubmission FM = new FormSubmission(driver);

		parentTest = extent.createTest("Verify form submission");

		FM.FillSubmissionForm("Sagar", "Test@yopmail.com", "123456", "10071993");
		
		FM.Check_AllCountries_InSequence_AlongWith_Duolicate_CountryPresence();

		FM.CheckCountrySequencePresenceInList();
		
		FM.Scroll_In_To_FeedBackSection();
		
		FM.EnterFeedBack();
		
	}
	
	
	
}
