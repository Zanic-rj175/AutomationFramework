package practice;

import java.time.Duration;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class DemoScript2 {
	public static void main(String[] args) {

		// Step 1:- Launch Browser
		WebDriver driver = new ChromeDriver();
		// driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

		// Step 2:- login to application with valid credentials
		driver.get("http://localhost:8888/");
		driver.findElement(By.name("user_name")).sendKeys("admin");
		driver.findElement(By.name("user_password")).sendKeys("password ");
		driver.findElement(By.id("submitButton")).click();

		// Step 3:-Navigate to Organization link
		driver.findElement(By.linkText("Organizations")).click();

		// Step 4:-Click on Create Organization look up image
		driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();

		// Step 5:-Create organization with mandatory fields
		Random r = new Random();
		int random = r.nextInt(1000);
		driver.findElement(By.name("accountname")).sendKeys("Insprion Lab"+random);

		// step 6 :-save and verify
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		String orgname = driver.findElement(By.className("dvHeaderText")).getText();
		if (orgname.contains("Insprion Lab"+random)) {
			System.out.println(orgname + "---Passed");
		} else {
			System.out.println(orgname + "---failed");

		}
        // Step 7 :-logout of application
		WebElement logoutEle = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		Actions action = new Actions(driver);
		action.moveToElement(logoutEle).perform();
		driver.findElement(By.linkText("Sign Out")).click();

		// step 8 :-close browser
		driver.quit();

	}

}
