package Generic;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ScreenshotUtil {

	private static final String SCREENSHOT_DIR = "Screenshots";

	private ScreenshotUtil() {
		// utility class, no instantiation
	}

	/**
	 * Captures a screenshot and saves it under /Screenshots as
	 * <testName>_<timestamp>.png
	 *
	 * @param driver   active WebDriver instance
	 * @param testName name to prefix the screenshot file with (usually the
	 *                 @Test method name)
	 * @return absolute path of the saved screenshot, or null if capture failed
	 */
	public static String captureScreenshot(WebDriver driver, String testName) {

		if (driver == null) {
			LogUtil.warn("Cannot capture screenshot - driver is null for test: " + testName);
			return null;
		}

		try {
			Path dirPath = Paths.get(SCREENSHOT_DIR);
			if (!Files.exists(dirPath)) {
				Files.createDirectories(dirPath);
			}

			String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
			String fileName = testName + "_" + timestamp + ".png";
			Path destination = dirPath.resolve(fileName);

			File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			Files.copy(srcFile.toPath(), destination);

			LogUtil.info("Screenshot captured: " + destination.toAbsolutePath());
			return destination.toAbsolutePath().toString();

		} catch (IOException | ClassCastException e) {
			LogUtil.error("Failed to capture screenshot for test: " + testName, e);
			return null;
		}
		
	}

}
