package practice;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;


/**
 * Scenario 4 :
 * Launch Browser,Open Application vitgerCRM,
 * Login to application with valid credentials,
 * Navigate to Organization link,
 * Click on create Organization look up image,
 * Create Organization with mandatory field,
 * Select "Energy" in the industry dropDown,
 * Select "Customer" in the Type DropDown,
 * Save and verify,
 * Logout of application. 
 */

public class DemoScript4 {

	public static void main(String[] args) {
		// Step 1:-Launch Browser
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

		// Step 2:- login to application with valid credentials
		driver.get("http://localhost:8888/");
		driver.findElement(By.name("user_name")).sendKeys("admin");
		driver.findElement(By.name("user_password")).sendKeys("password ");
		driver.findElement(By.id("submitButton")).click();

		// Step 3:-Navigate to Organization link
		driver.findElement(By.linkText("Organizations")).click();

		// Step 4:-Click on Create Organization look up image
		driver.findElement(By.xpath("//img[@src='themes/softed/images/btnL3Add.gif']")).click();

		// Step 5:-Create organization with mandatory fields
		driver.findElement(By.name("accountname")).sendKeys("Wipro");

		// Step 6:-Select "Energy" in the industry dropDown

		// identify dropDown
		WebElement industryDropDown = driver.findElement(By.name("industry"));

		// To handle dropDown
		Select industry = new Select(industryDropDown);

		// call Method
		industry.selectByVisibleText("Energy");

		// Step 7:-Select "Customer" in the Type DropDown
		// identify dropDown
		WebElement typeDropDown = driver.findElement(By.name("accounttype"));

		// To handle dropDown
		Select type = new Select(typeDropDown);

		// To call Method
		type.selectByVisibleText("Customer");

		// Step 7:-Save and Verify
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		String OrganizationName = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if (OrganizationName.contains("Wipro")) {
			System.out.println(OrganizationName + "---Passed");
		} else {
			System.out.println(OrganizationName + "---Failed");
		}
		// Step 7:-Logout of Application
		WebElement logoutEle = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		Actions action = new Actions(driver);
		action.moveToElement(logoutEle).perform();
		driver.findElement(By.linkText("Sign Out")).click();

		// Step 8:-Close Browser
		driver.quit();

	}

}
