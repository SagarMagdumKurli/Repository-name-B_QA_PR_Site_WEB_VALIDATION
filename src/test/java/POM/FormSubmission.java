package POM;

import static org.testng.Assert.assertEquals;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.WheelInput;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentTest;

import Generic.BaseLogics;
import Generic.LogUtil;

public class FormSubmission extends BaseLogics {

	@FindBy(xpath = "//span[text()='Form Submission']")
	private WebElement formSubmission;

	@FindBy(xpath = "//input[@id='name']")
	private WebElement nameField;

	@FindBy(xpath = "//input[@id='email']")
	private WebElement emailField;

	@FindBy(xpath = "//input[@id='contact']")
	private WebElement contactField;

	@FindBy(xpath = "//input[@id='date']")
	private WebElement dateField;

	@FindBy(xpath = "//input[@id='Red']")
	private WebElement selectRedRadio;

	@FindBy(xpath = "//input[@id='Pasta']")
	private WebElement selectFoodPasta;

	@FindBy(xpath = "//select[@id='country']")
	private WebElement SelectCountryCTA;

	@FindBy(xpath = "//input[@id='Sandwich']")
	private WebElement sandwich;

	@FindBy(xpath = "//option[@value='Albania']")
	private WebElement Albania;

	@FindBy(xpath = "//option[@value='Aland']")
	private WebElement Aland;

	@FindBy(xpath = "//select[@id='country']/option")
	private List<WebElement> SelectCountryList;
	
	@FindBy(xpath="//div[@id='scrollableDiv']")
	private WebElement FeedbackSection;
	
	@FindBy(xpath="//textarea[@placeholder='Write Comment...']")
	private WebElement CommentBox;

	// input[@id='Sandwich']

	// private final By SignInbutton=By.xpath("//a[.//span[normalize-space()='Sign
	// In']]");

	// ----------------

	Actions act;

	public FormSubmission(WebDriver driver) {
		this.driver = driver;

		act = new Actions(driver);

		PageFactory.initElements(driver, this);

	}

	private void WAIT(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		wait.until(ExpectedConditions.visibilityOf(element));
	}

	private void clickFormSubmission() {
		WAIT(formSubmission);
		formSubmission.click();
		chiledTest = parentTest.createNode("Click on form submission CTA");
	}

	private void EnterName(String name) {
		WAIT(nameField);
		nameField.sendKeys(name);
		chiledTest = parentTest.createNode("Enter Name");

	}

	private void enterEmail(String email) {
		emailField.sendKeys(email);
		chiledTest = parentTest.createNode("Enter Email");

	}

	private void Entercontact(String contact) {
		contactField.sendKeys(contact);
		chiledTest = parentTest.createNode("Enter Contact");

	}

	private void enterDOB(String DOB) {
		dateField.sendKeys(DOB);
		chiledTest = parentTest.createNode("Enter DOB");

	}

	private void UploadFile() {
		String path = System.getProperty("user.dir") + "\\Upload\\SamplePDF4.5MB.pdf";

		driver.findElement(By.xpath("//input[@id='file']")).sendKeys(path);

		chiledTest = parentTest.createNode("PDF file is Uploaded");

	}

	private void SelectColorAndFood() {

		ScrollToElement(sandwich);
		WAIT(selectRedRadio);
		selectRedRadio.click();
		LogUtil.info("Red color selected");
		chiledTest = parentTest.createNode("selected RedRadio ");

		// ScrollToElement(sandwich); // Using Actions Class

		WAIT(selectFoodPasta);
		selectFoodPasta.click();
		LogUtil.info("Food is selected Pasta");
		chiledTest = parentTest.createNode("selected Pasta");

	}

	private void selectCountry() {
		WAIT(SelectCountryCTA);

		Select SC = new Select(SelectCountryCTA);

		SC.selectByValue("Albania");
		LogUtil.info("Albania is selected as Country");

		ScrollToElement(SelectCountryCTA);
		LogUtil.info("Scroll till element is worked");

		SelectCountryCTA.click();

		WAIT(Albania);
		// SC.selectByValue("Andorra");

		ScrollIntoSelectCountry();

	}

	// ------------------

	private void ScrollToElement(WebElement element) {

		// Actions act = new Actions(driver);
		act.scrollToElement(element).perform();

	}

	private void ScrollIntoSelectCountry() {
//		WheelInput.ScrollOrigin origin = WheelInput.ScrollOrigin.fromElement(Albania);
//
//		act.scrollFromOrigin(origin, 0, 200);   it wont work because used Select class meaning Native web

		act.sendKeys(Keys.ARROW_DOWN).perform(); // move down with single step.
		LogUtil.info(
				"Keys.ARROW_DOWN: Scroll Single step is done || Only keys actions works because Note: Scroll wont work because used Select class meaning Native web");

		act.sendKeys(Keys.PAGE_DOWN).perform(); // it will go down to view screen list
		LogUtil.info("Keys.PAGE_DOWN: Scroll till last low of list");

	}

	public void FillSubmissionForm(String name, String email, String contact, String DOB) throws Exception {

		clickFormSubmission();

		EnterName(name);

		enterEmail(email);

		Entercontact(contact);

		enterDOB(DOB);

		UploadFile();

		SelectColorAndFood();

		selectCountry();
		chiledTest = extent.createTest("Form is Filled with All Mandatory Data");

	}

	public void Check_AllCountries_InSequence_AlongWith_Duolicate_CountryPresence() {
		SelectCountryCTA.click();

		System.out.println("Cout Of Countries : " + SelectCountryList.size());

		Set<String> Unique = new TreeSet<>(String.CASE_INSENSITIVE_ORDER);
		Set<String> Duplicate = new TreeSet<>(String.CASE_INSENSITIVE_ORDER);

		boolean DuplicateFound = false;
		// for(WebElement element:SelectCountryList)

		for (int i = 0; i < SelectCountryList.size() - 1; i++) {
			String getCountryName = SelectCountryList.get(i).getText();
			System.out.println("Country Position" + i + "Counyty Name: " + getCountryName);

			if (!Unique.add(getCountryName)) {
				System.out.println("Unique will be add In to Unique and Duplicate will Add into Duplicate");
				Duplicate.add(getCountryName);
				DuplicateFound = true;
				Assert.assertEquals(DuplicateFound, false, "Duplicate is found :" + getCountryName);
			}

		}

//		for(int i=0;i<SelectCountryList.size()-1;i++)
//		{
//			String STDContryName=SelectCountryList.get(i).getText();
//			System.out.println("Position:"+i+"Country Name: "+SelectCountryList.get(i).getText());
//			
//			for(int j=0;j<SelectCountryList.size()-1;j++)
//			{
//				String CHK_duplicate=SelectCountryList.get(j).getText();
//				
//				
//				if(STDContryName.equalsIgnoreCase(CHK_duplicate))
//				{
//					count++;
//					if(count>1)
//					{
//					System.out.println("Country Matched: "+STDContryName);
//					duplicate=true;
//					Assert.assertEquals(duplicate, false,"The Dulicate Country Found: ");
//					}
//				}
//				
//			}
//			
//			System.out.println("Creating Count 0 again");
//			count=0;
//		}

		System.out.println("No Duplicate Counyty Name Found");

		chiledTest = extent.createTest("Verified No Duplicate Counyty Name Found");

	}

	public void CheckCountrySequencePresenceInList() {
		List<String> CountryList = new ArrayList<>();

		for (WebElement element : SelectCountryList) {
			
			  
		
			CountryList.add(element.getText().trim());

		}

		List<String> OrderedList = new ArrayList<>(CountryList);
		Collections.sort(OrderedList, String.CASE_INSENSITIVE_ORDER);

		for (int i = 0; i < CountryList.size() - 1; i++) {
			if (!(OrderedList.get(i)).equals(CountryList.get(i))) {
				System.out.println("This is MisPlaced in Order :" + CountryList.get(i) + "Index Is:" + i);
			}

		}

	}
	
	
	public void Scroll_In_To_FeedBackSection()
	{
		act.moveToElement(FeedbackSection).build().perform();
//		act=new Actions(driver);
//		
//		act.moveToElement(FeedbackSection)
//		   .scrollByAmount(0, 100).build().perform();
		
		JavascriptExecutor js= (JavascriptExecutor)driver;
		
		js.executeScript("arguments[0].scrollTop = arguments[0].scrollTop + 100;", FeedbackSection);
		
		System.out.println("Scrolled In to Feedback Section.");
	}
	
	public void EnterFeedBack()
	{
		Actions act= new Actions(driver);
		act.moveToElement(CommentBox).build().perform();
		WAIT(CommentBox);
		
		act.click(CommentBox).keyDown(Keys.SHIFT).sendKeys("sagar").keyUp(Keys.SHIFT).build().perform();
        act.sendKeys(Keys.HOME).build().perform();	
	}
	
	
	

}
