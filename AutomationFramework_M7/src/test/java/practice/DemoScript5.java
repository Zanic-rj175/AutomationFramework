package practice;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class DemoScript5 {

	public static void main(String[] args) {
		// step 1 :- Launch Browser
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

		// step 2 :-Login to application with valid credentials
		driver.get("http://localhost:8888/index.php?action=Login&module=Users");
		driver.findElement(By.name("user_name")).sendKeys("admin");
		driver.findElement(By.name("user_password")).sendKeys("password ");
		driver.findElement(By.id("submitButton")).click();

		// step 3 :-navigate to contact link
		driver.findElement(By.linkText("Contacts")).click();

		// step 4 :-click on create contact look up image
		driver.findElement(By.xpath("//img[@src='themes/softed/images/btnL3Add.gif']")).click();

		// step 5 :-create a contact with mandatory fields
		driver.findElement(By.name("lastname")).sendKeys("Saumya Rj");
		driver.findElement(By.xpath("(//img[@src='themes/softed/images/select.gif'])[1]")).click();

		// To switch window
		for (String handle : driver.getWindowHandles()) {
			driver.switchTo().window(handle);
		}
		// Search for an Organization and Select it
		driver.findElement(By.id("search_txt")).sendKeys("Wipro");
		WebElement searchDropdown = driver.findElement(By.name("search_field"));
		Select select = new Select(searchDropdown);
		select.selectByVisibleText("Organization Name");
		driver.findElement(By.name("search")).click();
		driver.findElement(By.linkText("Wipro")).click();

		// Switch back to the parent window
		driver.switchTo().window(driver.getWindowHandles().iterator().next());

		// step 6 :-save and verify
		driver.findElement(By.xpath("(//input[@title='Save [Alt+S]'])[1]")).click();
		String lastname = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if (lastname.contains("Saumya Rj")) {
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
		//driver.quit();

	}

}
