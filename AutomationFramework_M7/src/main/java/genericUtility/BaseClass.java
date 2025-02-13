package genericUtility;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import objectRepository.HomePage;
import objectRepository.LoginPage;

public class BaseClass {

	PropertyFileUtility putil = new PropertyFileUtility();
	ExcelFileUtility eutil = new ExcelFileUtility();
	WebDriverUtility wutil = new WebDriverUtility();
	public WebDriver driver = null;
    public static WebDriver sDriver;//Listeners
	@BeforeSuite
	public void bsConfig() {
		Reporter.log("---DataBase connection Established---");
	}

//	@Parameters("browser")
//	@BeforeTest
	@BeforeClass
	public void bcConfig(/*String BROWSER*/) throws IOException {
		String BROWSER = putil.toReadDataFromPropertyFile("browser");
		String URL = putil.toReadDataFromPropertyFile("url");

		if (BROWSER.contains("chrome")) {
			driver = new ChromeDriver();
		} else if (BROWSER.contains("edge")) {
			driver = new EdgeDriver();

		} else if (BROWSER.contains("firefox")) {
			driver = new FirefoxDriver();
		}
		sDriver = driver;//Listeners
		wutil.toMaximize(driver);
		wutil.toimplicitlyWait(driver);
		driver.get(URL);
		Reporter.log("Browser got Launched Successfully", true);
	}

	@BeforeMethod
	public void bmConfig() throws IOException {
		String USERNAME = putil.toReadDataFromPropertyFile("username");
		String PASSWORD = putil.toReadDataFromPropertyFile("password");
		LoginPage lp = new LoginPage(driver);
		lp.getUsernameTextfield().sendKeys(USERNAME);
		lp.getPasswordTextfield().sendKeys(PASSWORD);
		lp.getLoginButton().click();
		Reporter.log("Navigate to Vtiger HomePage Successfully", true);
	}

	@AfterMethod
	public void amConfig() {
		HomePage hp = new HomePage(driver);
		wutil.toMouseOver(driver, hp.getLogoutLink());
		hp.getSignoutLink();
	}

	@AfterClass
	public void acConfig() {
		driver.quit();
	}

	@AfterSuite
	public void asConfig() {
		Reporter.log("---DataBase connection Disconnection---");
	}

}
