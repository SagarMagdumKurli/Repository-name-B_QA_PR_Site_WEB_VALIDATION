package POM;

import static org.testng.Assert.assertTrue;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import Generic.BaseLogics;
import Generic.Lib;

public class E_cart_ProductListPage extends BaseLogics {
	
	WebDriver driver;
	
	@FindBy(xpath="//button[contains(@type,'button') and contains (text(),'Add to cart')]")
	private List<WebElement> AddToCartbuttons;
	
	@FindBy(xpath="//button[contains(@type,'button') and contains (text(),'Add to cart')]")
	private WebElement AddToCartbutton;
	
	@FindBy(xpath="//button[@data-slot='popover-trigger']")
	private WebElement Click_OrderByCTA;
	
	@FindBy(xpath="")
	private WebElement element;
	
	public E_cart_ProductListPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);		
	}
	
	public void CheckProductListAvailable() throws Exception
	{
		Lib.WAIT(driver, AddToCartbutton);
		boolean addTocartbuttonPresent=AddToCartbutton.isDisplayed();
		Assert.assertTrue(addTocartbuttonPresent);
		System.out.println("Add to Cart button is present ");
		chiledTest=extent.createTest("Add to Cart button is present");
	}
	
	public void VerifyPriceLowToHighFunction() throws Exception
	{
		Lib.WAIT(driver, Click_OrderByCTA);
		Click_OrderByCTA.click();
		
		
	}

}
