package POM;

import java.io.FileInputStream;
import java.util.Arrays;
import java.util.Collections;

import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

public class UserAuthentication {

	public WebDriver driver;

	@FindBy(xpath = "//li[@id='registration']")
	private WebElement Registration;

	@FindBy(xpath = "//input[@id='name']")
	private WebElement UserId;

	@FindBy(xpath = "//Select[@id='country']")
	private WebElement SelectCountry;

	@FindBy(xpath = "//Select[@id='account']")
	private WebElement SelectAccount;

	@FindBy(xpath = "//input[@id='email']")
	private WebElement EmailInput;

	@FindBy(xpath = "//input[@id='password']")
	private WebElement PasswordInput;

	@FindBy(xpath = "//input[@id='confirm_password']")
	private WebElement ConfirmPasswordInput;

	@FindBy(xpath = "//button[@type='submit']")
	private WebElement SubmitButton;

	@FindBy(xpath = "//textarea[@placeholder='Write Comment...']")
	private WebElement CommentTextArea;

	@FindBy(xpath = "//p[text()='Congratulations. You have successfully logged in. When you are done click logout below.']")
	private WebElement LoginSuccessMessage;

	public UserAuthentication(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this); // To initialize all @FindBy elements inside this class
	}

	public Properties Readconfig() throws Exception {

		FileInputStream FIS = new FileInputStream("./config.properties");

		Properties PR = new Properties();
		PR.load(FIS);
		return PR;

	}

	public void ScrollToElement_JS_Exicutor() {
		JavascriptExecutor JS = (JavascriptExecutor) driver;

		// JS.executeScript("arguments[0].scrollIntoView(true);", element);
		JS.executeScript("window.scrollTo(0, document.body.scrollHeight);");
	}

	public void ScrollToElement_Action(WebElement element) {
		Actions ACT = new Actions(driver);
		ACT.scrollToElement(element).build().perform();

	}

	public void WAIT(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(element));
		System.out.println("Explicitly wait worked");
		driver.manage().timeouts().implicitlyWait(java.time.Duration.ofSeconds(5));

	}

	public void selectcountrynamebyoption(WebElement element) {
		Select selectcountryname = new Select(SelectCountry);
		selectcountryname.selectByValue("Afghanistan");
		System.out.println("The Country is Afganistan");
	}

	public void selectaccountTypebyoption(WebElement element) {
		Select selectAccountName = new Select(SelectAccount);
		selectAccountName.selectByValue("Engineer");
		System.out.println("The Account is Engineer");
	}

	public int CheckIndiaIsPresent() {
		Select SC = new Select(SelectCountry);
		List<WebElement> list_Of_Countries = SC.getOptions();

		System.out.println("List of Countries:" + list_Of_Countries);

		System.out.println("Country List Count=" + list_Of_Countries.size());

		boolean tag = false;
		for (WebElement country : list_Of_Countries) {
			if (country.getText().equalsIgnoreCase("india")) {
				tag = true;

				break;
			}
		}

		if (tag == true) {
			System.out.println("India is Present in Dropdown");
		}

		return list_Of_Countries.size();
	}

	public void CheckIndiaPresentPosition() {

		Select SC = new Select(SelectCountry);
		List<WebElement> listofcountries = SC.getOptions();

		boolean tag = false;

		for (int i = 0; i <= listofcountries.size(); i++) {

			if (listofcountries.get(i).getText().equalsIgnoreCase("INDIA")) {
				System.out.println("Indias Position: " + (i + 1));
				System.out.println("Loop break at=" + i);
				tag = true;
				break;
			}
		}
	}

	public void VerifyListAccountTypes() throws Exception {
		WAIT(SelectAccount);
		SelectAccount.click();

		Select selectAccountName = new Select(SelectAccount);
		boolean engineerMatch = selectAccountName.getOptions().stream()
				.anyMatch(option -> option.getText().equals("Engineer"));

		if (engineerMatch == true) {
			WebDriverWait wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(2));
			wait.until(ExpectedConditions.visibilityOf(SelectAccount));
		}

		List<WebElement> listAccountType = selectAccountName.getOptions();

		List<String> accountList = listAccountType.stream().map(WebElement::getText).collect(Collectors.toList());

		Collections.sort(accountList);

		Assert.assertEquals(listAccountType.size(), 5, "The size of the Select Account Type");

		Set<String> uniqueAccountList = new HashSet<>(accountList);

		System.out.println(uniqueAccountList);

		Properties readProperties = Readconfig();
		String listOfAccountType = readProperties.getProperty("AccountType");

		Set<String> trnsalteList = new HashSet<>(Arrays.asList(listOfAccountType.split(",")));

		System.out.println(trnsalteList);

		Assert.assertEquals(uniqueAccountList, trnsalteList, "The Account List Does not Matched");

	}

	public void OpenRegistartionForm() throws Exception {
		WAIT(Registration);
		Registration.click();
		System.out.println("Clicked on Registration CTA");
	}

	public void FillRegistrationform() {
		WAIT(UserId);
		UserId.sendKeys("Sagar M");
		System.out.println("Entered Username");

		WAIT(SelectCountry);
		SelectCountry.click();
		System.out.println("SelectCountry Clicked");

		selectcountrynamebyoption(SelectCountry);

		selectaccountTypebyoption(SelectAccount);

		EmailInput.sendKeys("Sagarsample@yopmail.com");
		System.out.println("Entered Email");

		PasswordInput.sendKeys("Sagar@Password");
		System.out.println("Entered Password");

		ConfirmPasswordInput.sendKeys("Sagar@Password");
		System.out.println("Entered ConfirmedPassword");

		CommentTextArea.sendKeys("This is the Sample part of the material");
		System.out.println("Feedback is Entered");

		// ScrollToElement_JS_Exicutor();
		ScrollToElement_Action(SubmitButton);
		System.out.println("SubmitButton is visible By Java Scripts Exicutor");

		SubmitButton.click();
		System.out.println("Submit button Clicked");

		WAIT(LoginSuccessMessage);
		System.out.println("Log In is Successfully Done");
	}

	public void CheckSelectCountryDropDown() {
		WAIT(Registration);
		Registration.click();
		driver.findElement(By.xpath("//li[@id='registration']")).click();
		System.out.println("Driver is pass");
		WAIT(SelectCountry);

		Select SC = new Select(SelectCountry);
	
		List<WebElement> ListOfCountriesOptions = SC.getOptions();
		
		for(int i=0;i<ListOfCountriesOptions.size();i++)
		{
			
			String eachCountryname=ListOfCountriesOptions.get(i).getText();
			System.out.println("Country No"+i+" :"+eachCountryname);
			if(eachCountryname.contains("Angola"))
			{
				
			int IndexOfElement = ListOfCountriesOptions.indexOf(ListOfCountriesOptions.get(i));
			System.out.println("Index Of element: "+IndexOfElement);
			
			}
			
			if(i==2)
			{
				String SecondIndexValue=ListOfCountriesOptions.get(i).getText();
				System.out.println("Second Index Value:"+SecondIndexValue);
				
			}
			
		}		

		SC.selectByValue("Afghanistan");
		System.out.println("User Is able To select Country");

		CheckIndiaIsPresent();

		CheckIndiaPresentPosition();

	}

}
