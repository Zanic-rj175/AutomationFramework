package objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
	// constructor
	public HomePage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	@FindBy(linkText = "Contacts")
	private WebElement contactLink;

	@FindBy(linkText = "Organizations")
	private WebElement organizationsLink;

	@FindBy(xpath = "//img[@src='themes/softed/images/user.PNG']")
	private WebElement logoutLink;

	@FindBy(linkText = "Sign Out")
	private WebElement signoutLink;

	public WebElement getLogoutLink() {
		return logoutLink;
	}

	public WebElement getContactLink() {
		return contactLink;
	}

	public WebElement getOrganizationsLink() {
		return organizationsLink;
	}

	public WebElement getSignoutLink() {
		return signoutLink;
	}

}
