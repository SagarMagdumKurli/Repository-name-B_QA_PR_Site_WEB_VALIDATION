package Generic;

import org.testng.annotations.Test;




import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;

//@Listeners(Generic.TestListener.class)
public class BaseLogics implements Autoconstant {

	public WebDriver driver;
	public static String result = "fail";

	public static ExtentReports extent;
	public static ExtentTest parentTest;
	public static ExtentTest chiledTest;

	@BeforeSuite(groups = { "Regression", "Smoke", "Sanity" })
	public void setupReport() {
		ExtentSparkReporter sparkReporter = new ExtentSparkReporter("./Reports/extent-parent-child.html");
		extent = new ExtentReports();
		extent.attachReporter(sparkReporter);
	}

	@Parameters({ "browser" })
	@BeforeClass(groups = { "Regression", "Smoke", "Sanity" })
	public void LaunchURL(String Name) throws Exception {

		if (Name.equalsIgnoreCase("Chrome")) {
			driver = new ChromeDriver();

		} 
		
		else if (Name.equalsIgnoreCase("Firefox")) {

			driver = new FirefoxDriver();
		}
		
		driver.get(URL);
		driver.manage().window().maximize();
		Thread.sleep(1000);
		LogUtil.info("Browser launched and navigated to: " + URL);
		System.out.println("Browser launched and navigated to URL");
        //Test
	}

//	@AfterClass(groups= {"Regression","Smoke","Sanity"})
	public void CloseTab() {
		driver.close();
		LogUtil.info("Browser tab closed.");
	}
	
	@AfterMethod()
	public void afterMethod(ITestResult result)
	{
		if(result.getStatus()==ITestResult.FAILURE)
		{
			int printStatus = result.getStatus();
			System.out.println("This is status"+printStatus);
		}
	}

//	@AfterSuite(groups= {"Regression","Smoke","Sanity"})
	public void flushReport() {
		extent.flush();
	}

//	@AfterSuite
//	public void Logout() 
//	{
//		driver.quit();
//		System.out.println("Browser is Closed.");
//	}

}
