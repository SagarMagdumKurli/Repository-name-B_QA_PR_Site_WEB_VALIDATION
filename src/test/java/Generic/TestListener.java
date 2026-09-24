package Generic;

import org.openqa.selenium.WebDriver;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {

	@Override
	public void onStart(ITestContext context) {
		LogUtil.info("===== Test Suite Started: " + context.getName() + " =====");
	}

	@Override
	public void onFinish(ITestContext context) {
		LogUtil.info("===== Test Suite Finished: " + context.getName() + " =====");
	}

	@Override
	public void onTestStart(ITestResult result) {
		LogUtil.info("Test Started: " + result.getMethod().getMethodName());
		
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		LogUtil.info("Test Passed: " + result.getMethod().getMethodName());		
	}

	
	@Override
	public void onTestFailure(ITestResult result) {
		String testName = result.getMethod().getMethodName();
		LogUtil.error("Test Failed: " + testName, result.getThrowable());

		WebDriver driver = getDriver(result);
		if (driver != null) {
			ScreenshotUtil.captureScreenshot(driver, testName);
		} else {
			LogUtil.warn("No WebDriver found on test instance - skipping screenshot for: " + testName);
		}
	}

	
	@Override
	public void onTestSkipped(ITestResult result) {
		LogUtil.warn("Test Skipped: " + result.getMethod().getMethodName());
	}

	/**
	 * Pulls the active WebDriver off the running test instance. Every test in
	 * this framework extends Generic.BaseLogics, which exposes a public
	 * "driver" field - this reuses it instead of creating a separate driver
	 * registry.
	 */
	private WebDriver getDriver(ITestResult result) {
		Object currentInstance = result.getInstance();

		if (currentInstance instanceof BaseLogics) {
			return ((BaseLogics) currentInstance).driver;
		}
		return null;
	}

}
