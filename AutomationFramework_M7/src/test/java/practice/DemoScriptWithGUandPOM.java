package practice;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import genericUtility.ExcelFileUtility;
import genericUtility.PropertyFileUtility;
import genericUtility.WebDriverUtility;
import objectRepository.ContactInfoPage;
import objectRepository.ContactsPage;
import objectRepository.CreateContactPage;
import objectRepository.HomePage;
import objectRepository.LoginPage;

public class DemoScriptWithGUandPOM {

	public static void main(String[] args) throws IOException {
		PropertyFileUtility putil = new PropertyFileUtility();
		ExcelFileUtility eutil = new ExcelFileUtility();
		WebDriverUtility wutil = new WebDriverUtility();

		// To Read data from PropertyFile
		String URL = putil.toReadDataFromPropertyFile("url");
		String USERNAME = putil.toReadDataFromPropertyFile("username");
		String PASSWORD = putil.toReadDataFromPropertyFile("password");
		String BROWSER = putil.toReadDataFromPropertyFile("browser");
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
		LoginPage lp = new LoginPage(driver);
		lp.getUsernameTextfield().sendKeys(USERNAME);
		lp.getPasswordTextfield().sendKeys(PASSWORD);
		lp.getLoginButton().click();

		// step 3 :-navigate to contact link
		HomePage hp = new HomePage(driver);
		hp.getContactLink().click();

		// step 4 :-click on create contact look up image
		ContactsPage cp = new ContactsPage(driver);
		cp.getContactsLookUpImg().click();

		// step 5 :-create a contact with mandatory fields
		CreateContactPage ccp = new CreateContactPage(driver);
		ccp.getLastnameTextfielld().sendKeys(LASTNAME);
		ccp.getOrgLookUpImag().click();
		// To switch window
		wutil.toSwitchWindow(driver, LASTNAME);	
		ccp.getSearchtextfield().sendKeys("wipro");
		ccp.getSearchbutton().click();
		ccp.getSearchresult().click();
		wutil.toSwitchBackWindow(driver, LASTNAME);
		ccp.getSaveButton().click();

		// step 6 :-save and verify
		ContactInfoPage cip = new ContactInfoPage(driver);
		String lastname = cip.getHeaderTitle().getText();
		if (lastname.contains(LASTNAME)) {
			System.out.println(lastname + "---Passed");
		} else {
			System.out.println(lastname + "---Failed");
		}

		// step 7 :-logout of application
		wutil.toMouseOver(driver, hp.getLogoutLink());
		hp.getSignoutLink().click();

		// step 8 :-close browser
		driver.quit();

	}

}
