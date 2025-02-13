package practice;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

import genericUtility.ExcelFileUtility;
import genericUtility.PropertyFileUtility;
import genericUtility.WebDriverUtility;

public class DemoScriptWithDDTandGU {

	public static void main(String[] args) throws IOException {

		PropertyFileUtility putil = new PropertyFileUtility();
		ExcelFileUtility eutil = new ExcelFileUtility();
		WebDriverUtility wutil = new WebDriverUtility();

		// To Read data from PropertyFile
		String BROWSER = putil.toReadDataFromPropertyFile("browser");
		String URL = putil.toReadDataFromPropertyFile("url");
		String USERNAME = putil.toReadDataFromPropertyFile("username");
		String PASSWORD = putil.toReadDataFromPropertyFile("password");

		// To Read Data from ExcelFile
		String LASTNAME = eutil.toReadDataFromExcel("Contacts", 1, 2);

		// Step 1:-Launch Browser
		WebDriver driver = null;
		if (BROWSER.contains("chrome")) {
			driver = new ChromeDriver();
		} else if (BROWSER.contains("edge")) {
			driver = new EdgeDriver();

		} else if (BROWSER.contains("firefox")) {
			driver = new FirefoxDriver();
		}
		// to maximize the browser
		wutil.toMaximize(driver);
		// to used implicitlyWait
		wutil.toimplicitlyWait(driver);

		// step 2 :-Login to application with valid credentials
		driver.get(URL);
		driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		driver.findElement(By.id("submitButton")).click();
		// step 3 :-navigate to contact link
		driver.findElement(By.linkText("Contacts")).click();

		// step 4 :-click on create contact look up image
		driver.findElement(By.xpath("//img[@src='themes/softed/images/btnL3Add.gif']")).click();

		// step 5 :-create a contact with mandatory fields
		driver.findElement(By.name("lastname")).sendKeys(LASTNAME);

		// step 6 :-save and verify
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		String lastname = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if (lastname.contains(LASTNAME)) {
			System.out.println(lastname + "---Passed");
		} else {
			System.out.println(lastname + "---failed");

		}

		// step 7 :-logout of application

		WebElement logoutEle = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		Actions action = new Actions(driver);
		action.moveToElement(logoutEle).perform();
		driver.findElement(By.linkText("Sign Out")).click();

		// step 8 :-close browser
		driver.quit();

	}

}
