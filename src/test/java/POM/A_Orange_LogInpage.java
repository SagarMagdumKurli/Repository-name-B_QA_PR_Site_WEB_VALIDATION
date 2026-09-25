package POM;

import static org.testng.Assert.assertTrue;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class A_Orange_LogInpage {

	WebDriver driver;

	@FindBy(xpath = "//input[@name='username']")
	private WebElement username;

	@FindBy(xpath = "//input[@name='password']")
	private WebElement password;

	@FindBy(xpath = "//button[@type='submit']")
	private WebElement submit;

	@FindBy(xpath="//span/h6[text()='Dashboard']")
	private WebElement DashBoard;
	
	public A_Orange_LogInpage(WebDriver driver) {
		this.driver = driver;

		PageFactory.initElements(driver, this);
	}

	private void Wait(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.elementToBeClickable(element));
        System.out.println("Element is Visible: "+element);
	}
	

	public void CheckLogInFunctionOrange()
	{		
		Wait(username);
		username.sendKeys("Admin");
		password.sendKeys("admin123");
		submit.click();
        System.out.println("Click Submit button");        
	}
	
	public void CheckHomePageDisplsyed()
	{
		Wait(DashBoard);
		boolean DashBoardIsVisible=DashBoard.isDisplayed();
		Assert.assertTrue(DashBoardIsVisible);
		System.out.println("The DashBoard is visible");
	}
	
	
	

}
