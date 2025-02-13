package objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrganizationPage {
	
	public OrganizationPage(WebDriver driver) {
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(linkText = "Organizations")
	private WebElement orgLink;
	
	@FindBy(xpath = "//img[@src='themes/softed/images/btnL3Add.gif']")
	private WebElement orgLookUpImg;

	public WebElement getOrganizationsLink() {
		return orgLink;
	}

	public WebElement getOrgLookupImg() {
		return orgLookUpImg;
	}

}
