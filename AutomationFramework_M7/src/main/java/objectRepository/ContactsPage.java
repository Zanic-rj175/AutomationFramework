package objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactsPage {
	
	public ContactsPage(WebDriver driver) {
		PageFactory.initElements(driver , this);
	}
	@FindBy(linkText = "Contacts")
	private WebElement contactsLink;
	
	public WebElement getContactsLink() {
		return contactsLink;
	}
	@FindBy(xpath = "//img[@src='themes/softed/images/btnL3Add.gif']")
	private WebElement contactsLookUpImg;

	public WebElement getContactsLookUpImg() {
		return contactsLookUpImg;
	}

}
