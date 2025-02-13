package genericUtility;

import java.io.File;
import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebDriverUtility {
	/**
	 * This method is used to maximize
	 * 
	 * @param driver
	 */
	public void toMaximize(WebDriver driver) {
		driver.manage().window().maximize();
	}

	/**
	 * This method is used to minimize
	 * 
	 * @param driver
	 */
	public void toMinimize(WebDriver driver) {
		driver.manage().window().minimize();
	}

	/**
	 * T his method will wait until the webElement loaded in
	 * WebPage(implicitelyWait)
	 */
	public void toimplicitlyWait(WebDriver driver) {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
	}

	/**
	 * This method is used to wait until the element is ClickAble provided driver
	 * and element
	 * 
	 * @param driver
	 * @param element
	 */
	public void toWaitUntilElementIsClickable(WebDriver driver, WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	/**
	 * This method is used to wait until the element is visible provided driver and
	 * element
	 * 
	 * @param driver
	 * @param element
	 */
	public void toWaitForVisiblityOfElement(WebDriver driver, WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	/**
	 * This method is used to handle DropDown using index
	 * 
	 * @param element
	 * @param index
	 */
	public void toHandleDropDown(WebElement element, int index) {
		Select select = new Select(element);
		select.selectByIndex(index);
	}

	/**
	 * This method is used to handle DropDown using value
	 * 
	 * @param element
	 * @param value
	 */
	public void toHandleDropDown(WebElement element, String value) {
		Select select = new Select(element);
		select.selectByValue(value);
	}

	/**
	 * This method is used to handle DropDown using VisibleText
	 * 
	 * @param text
	 * @param element
	 */
	public void toHandleDropDown(String text, WebElement element) {
		Select select = new Select(element);
		select.selectByVisibleText(text);
	}

	/**
	 * This method is used to handle Frame using index
	 * 
	 * @param driver
	 * @param index
	 */
	public void toHandleFrame(WebDriver driver, int index) {
		driver.switchTo().frame(index);
	}

	/**
	 * This method is used to handle Frame using String name or id
	 * 
	 * @param driver
	 * @param name_id
	 */
	public void toHandleFrame(WebDriver driver, String name_id) {
		driver.switchTo().frame(name_id);
	}

	/**
	 * This method is used to handle Frame using WebElement
	 * 
	 * @param driver
	 * @param element
	 */
	public void toHandleFrame(WebDriver driver, WebElement element) {
		driver.switchTo().frame(element);
	}

	/**
	 * This method is used to comeBack to
	 * 
	 * @param driver
	 */
	public void toSwitchBackFromFrame(WebDriver driver) {
		driver.switchTo().defaultContent();
	}

	/**
	 * This method is used to mouseOver on the element(mouse actions)
	 * 
	 * @param driver
	 * @param element
	 */
	public void toMouseOver(WebDriver driver, WebElement element) {
		Actions action = new Actions(driver);
		action.moveToElement(element).perform();
	}

	/**
	 * This method is used to perform doubleClick on webElement
	 * 
	 * @param driver
	 * @param element
	 */
	public void toDoubleClick(WebDriver driver, WebElement element) {
		Actions action = new Actions(driver);
		action.doubleClick(element).perform();
	}

	/**
	 * This method is used to perform RightClick on webElement
	 * 
	 * @param driver
	 * @param element
	 */
	public void toRightClick(WebDriver driver, WebElement element) {
		Actions action = new Actions(driver);
		action.contextClick(element);
	}

	/**
	 * This method used to perform drag and drop action provided driver, source
	 * element and target element
	 * 
	 * @param driver
	 * @param src
	 * @param target
	 */
	public void toDargAndDrop(WebDriver driver, WebElement src, WebElement target) {
		Actions action = new Actions(driver);
		action.dragAndDrop(src, target).perform();
	}

	/**
	 * This method is used to perform click and hold
	 * 
	 * @param driver
	 * @param element
	 */
	public void toClickAndHold(WebDriver driver, WebElement element) {
		Actions action = new Actions(driver);
		action.clickAndHold(element);
	}

	/**
	 * This method is used to handle alert PopUp by accepting it(Ok button)
	 * 
	 * @param driver
	 */
	public void toHandleAlertPopupByAccept(WebDriver driver) {
		driver.switchTo().alert().accept();
	}

	/**
	 * This method is used to handle alert PopUp by dismissing it(Cancle button)
	 * 
	 * @param driver
	 */
	public void toHandleAlertPopupByDismiss(WebDriver driver) {
		driver.switchTo().alert().dismiss();
	}

	/**
	 * This method is used to capture message in alert popup and accept it
	 * 
	 * @param driver
	 * @return
	 */
	public String toHandleAlertPopupAndCaptureText(WebDriver driver) {
		Alert alertPopup = driver.switchTo().alert();
		String popupMsg = alertPopup.getText();
		return popupMsg;
	}
	/**
	 * 
	 * @param driver
	 * @param screenshotname
	 * @return 
	 * @throws Exception
	 */
	public String toTakeScreenShot(WebDriver driver, String screenshotname) throws Exception {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File temp = ts.getScreenshotAs(OutputType.FILE);
		File src = new File("./errorShots/" + screenshotname + ".jpeg");
		FileHandler.copy(temp, src);
	    return src.getAbsolutePath();
		
	}
	/**
	 * This method is used to transfer driver control to windows provided driver and
	 * partial title
	 * @param driver
	 * @param partialTitle
	 */
	public void toSwitchWindow(WebDriver driver, String partialTitle) {
		Set<String> allIds = driver.getWindowHandles();
		for (String id : allIds) {
			String windowTitle = driver.switchTo().window(id).getTitle();
			if (windowTitle.contains(partialTitle)) {
				break;
			}
		}
	}
	/**
	 *  Switch back to the parent window
	 * @param driver
	 * @param partialTitle
	 */
	public void toSwitchBackWindow(WebDriver driver,String partialTitle) {
		driver.switchTo().window(driver.getWindowHandles().iterator().next());
		
	}

}
