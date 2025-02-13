package practice;

import java.io.IOException;
import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import genericUtility.ExcelFileUtility;
import genericUtility.PropertyFileUtility;
import genericUtility.WebDriverUtility;
import objectRepository.CreateOrganizationPage;
import objectRepository.HomePage;
import objectRepository.LoginPage;
import objectRepository.OrganizationInfoPage;
import objectRepository.OrganizationPage;

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

public class DemoScriptWithGUandPOM1 {

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
		String ORGANIZATIONTEXTFIELD = eutil.toReadDataFromExcel("Organization", 1, 2);

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

		// step 3 :-navigate to organization link
		HomePage hp = new HomePage(driver);
		hp.getOrganizationsLink().click();

		// step 4 :-click on create organization look up image
		OrganizationPage op = new OrganizationPage(driver);
		op.getOrgLookupImg().click();

		// step 5 :-create a organization with mandatory fields
		Random r = new Random();
		int random = r.nextInt(1000);
		CreateOrganizationPage cop = new CreateOrganizationPage(driver);
		cop.getOrganizationTextfield().sendKeys(ORGANIZATIONTEXTFIELD + random);
		wutil.toHandleDropDown("Energy", cop.getIndustryDropDown());
		wutil.toHandleDropDown("Customer", cop.getTypeDropDown());
		cop.getSaveButton().click();

		// step 6 :-save and verify
		OrganizationInfoPage oip = new OrganizationInfoPage(driver);
		String organizationtextfield = oip.getHeaderTitle().getText();
		if (organizationtextfield.contains(ORGANIZATIONTEXTFIELD)) {
			System.out.println(organizationtextfield + "---Passed");
		} else {
			System.out.println(organizationtextfield + "---Failed");

		}

		// step 7 :-logout of application
		wutil.toMouseOver(driver, hp.getLogoutLink());
		hp.getSignoutLink().click();

		// step 8 :-close browser
		driver.quit();
	}

}
