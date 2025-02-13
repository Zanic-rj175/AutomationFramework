package objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateContactPage {
	
	public CreateContactPage(WebDriver driver) {
		PageFactory.initElements(driver ,this);
	}
	
	@FindBy(name="lastname")
	private WebElement lastnameTextfielld;
	
	@FindBy(xpath = "//input[@title='Save [Alt+S]']")
	private WebElement saveButton;
	
	@FindBy(xpath = "(//img[@src='themes/softed/images/select.gif'])[1]")
	private WebElement orglookupImg;
	
	@FindBy(id="search_txt")
	private WebElement searchtextfield;
	
	@FindBy(name="search")
	private WebElement searchbutton;
	
	@FindBy(linkText = "Wipro969")
	private WebElement searchresult;

	public WebElement getSearchtextfield() {
		return searchtextfield;
	}

	public WebElement getSearchbutton() {
		return searchbutton;
	}

	public WebElement getSearchresult() {
		return searchresult;
	}

	public WebElement getLastnameTextfielld() {
		return lastnameTextfielld;
	}

	public WebElement getSaveButton() {
		return saveButton;
	}

	public WebElement getOrgLookUpImag() {
		return orglookupImg;
	}

}
