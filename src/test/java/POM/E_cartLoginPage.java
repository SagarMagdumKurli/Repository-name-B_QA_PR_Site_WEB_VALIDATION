package POM;

import static org.testng.Assert.assertEquals;



import org.jspecify.annotations.Nullable;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.aventstack.extentreports.Status;

import Generic.BaseLogics;
import Generic.Lib;

public class E_cartLoginPage extends BaseLogics {

	@FindBy(xpath = "//li[@id='ecommerce-site']")
	private WebElement Ecommerce;

	@FindBy(xpath = "//a[text()='Visit Demo Site' or @class='text-blue-600']")
	private WebElement VisitEcommerceSite;

	@FindBy(xpath = "//input[@id='email']")
	private WebElement email;

	@FindBy(xpath = "//input[@id='password']")
	private WebElement password;

	@FindBy(xpath = "//button[@type='submit']")
	private WebElement LogInBtn;
	
	@FindBy(xpath="//div[contains(text(),'Password matched but email is incorrect.']")
	private WebElement ErrorUsername;
	
	@FindBy(xpath="//div[contains(text(),'Password matched but email is incorrect.')]")
	private WebElement InvalidEmail;
	
	public E_cartLoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	private String BrouserError() {
		String error = email.getAttribute("validationMessage");
		return error;
	}

	public void ClickE_CommerceCite() throws Exception {
		Lib.WAIT(driver, Ecommerce);

		Ecommerce.click();

		System.out.println("The Ecommerece-site is Clicked ");

		Lib.WAIT(driver, VisitEcommerceSite);

		VisitEcommerceSite.click();

		System.out.println("VisitEcommerceSite Clicked");
	}

	
	public void  Enter_InvaliEmail_WithoutAt_ValidPassword(String email_Id, String pass) throws Exception {
		Lib.WAIT(driver, email);
		email.sendKeys(email_Id);

		password.sendKeys(pass);

		System.out.println("Entered InvalidEmail without @ & Valid Password");
		LogInBtn.click();

		String Error = BrouserError();
		
		Assert.assertEquals(Error, "Please include an '@' in the email address. 'testqabrains.com' is missing an '@'.");
	    System.out.println("Page Error Message is Validated.");
	    result="Pass";
	    chiledTest.log(Status.INFO, "Page Error Message is Validated.&& msg= Password matched but email is incorrect.");
	    
	}
	
	public void EnterInvalidEmail_ValidPassword(String Email, String pass) throws Exception
	{
		Lib.WAIT(driver, email);
		email.clear();
		email.sendKeys(Email);
		password.clear();
		password.sendKeys(pass);
		System.out.println("Entered InvalidEmail with@ and Valid Password");
		LogInBtn.click();
		Lib.WAIT(driver, InvalidEmail);
		String InvaliUsernameError=InvalidEmail.getText();
		Assert.assertEquals(InvaliUsernameError, "Password matched but email is incorrect.");
		result="Pass";
	}
	
	public void Enter_validEmail_InValidPassword(String Email, String pass) throws Exception
	{
		Lib.WAIT(driver, email);
		email.clear();
		email.sendKeys(Email);
		password.clear();
		password.sendKeys(pass);
		System.out.println("validEmail and In_Valid Password");
	
		
		Actions act= new Actions(driver);
		act.moveToElement(LogInBtn).build().perform();
		Lib.WAIT(driver,LogInBtn);
		LogInBtn.click();
	//	Lib.WAIT(driver, InvalidEmail);
	//	String Invalid_PasswordError=InvalidEmail.getText();
	//	Assert.assertEquals(Invalid_PasswordError, "Password matched but email is incorrect.");
		result="Pass";
	}

	public void Enter_validEmail_validPassword_ANd_ClickEnter(String Email, String pass) throws Exception
	{
		Lib.WAIT(driver, email);
		email.clear();
		email.sendKeys(Email);
		password.clear();
		password.sendKeys(pass);
		System.out.println("validEmail and validPassword");
		
		Lib.WAIT(driver, LogInBtn);
		
//		JavascriptExecutor JS= (JavascriptExecutor)driver;
//		JS.executeScript("window.scrollBy(0,500)", LogInBtn);
		Actions act= new Actions(driver);
		act.moveToElement(LogInBtn).build().perform();
		
		Lib.WAIT(driver, LogInBtn);
		LogInBtn.click();
		
	//	Lib.WAIT(driver, InvalidEmail);
	//	String Invalid_PasswordError=InvalidEmail.getText();
	//	Assert.assertEquals(Invalid_PasswordError, "Password matched but email is incorrect.");
		result="Pass";
	}


}
