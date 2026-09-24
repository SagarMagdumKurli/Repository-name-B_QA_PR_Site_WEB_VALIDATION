package POM;

import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Generic.BaseLogics;

public class HeaderLinks extends BaseLogics {

	@FindBy(xpath = "//a[contains(text(),'QA Topics')]")
	private WebElement qaTopic;

	@FindBy(xpath = "//a[contains(text(),'Discussion')]")
	private WebElement discussion;

	@FindBy(xpath = "//a[contains(text(),'Tags')]")
	private WebElement tags;

	public HeaderLinks(WebDriver driver) {

		this.driver = driver;

		PageFactory.initElements(driver, this);
	}

	public void OpenQATopics_DEscussio_TagsInNewTab() {
		Actions act = new Actions(driver);
		act.keyDown(Keys.CONTROL).click(qaTopic).click(discussion).click(tags).keyUp(Keys.CONTROL).build().perform();
        
	}

	public void WindowHandles() throws Exception {
		
		String ParentWindow = driver.getWindowHandle();

		Set<String> ListOfChildwindow = driver.getWindowHandles();

		int default_window = 1;

		Iterator<String> it = ListOfChildwindow.iterator();
		System.out.println(ListOfChildwindow.size());

		while (it.hasNext())  // It will switch to every window may be it will start with First one
		{
			String NextWindow = it.next(); // Gives Current Window

			if (!ParentWindow.equals(NextWindow)) {
				driver.switchTo().window(NextWindow);
				Thread.sleep(2000);
				default_window++;
				System.out.println(default_window);

			}

//			else {
//				System.out.println("This is end of the windows");
//				System.out.println("Total window count:" + default_window);
//				break;
//			}

		}

	}

}
