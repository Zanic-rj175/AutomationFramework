package objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateOrganizationPage {
	
	public CreateOrganizationPage(WebDriver driver) {
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(name="accountname")
	private WebElement organizationTextfield;
	
	@FindBy(xpath = "//input[@title='Save [Alt+S]']")
	private WebElement saveButton;
	
	@FindBy(name="industry")
	private WebElement industryDropDown;
	
	@FindBy(partialLinkText = "Chemicals")
	private WebElement chemicalDropDown;
	
	@FindBy(name="accounttype")
	private WebElement typeDropDown;
	
	@FindBy(partialLinkText = "Energy")
	private WebElement customerDropDown;

	public WebElement getOrganizationTextfield() {
		return organizationTextfield;
	}

	public WebElement getSaveButton() {
		return saveButton;
	}

	public WebElement getIndustryDropDown() {
		return industryDropDown;
	}

	public WebElement getChemicalDropDown() {
		return chemicalDropDown;
	}

	public WebElement getTypeDropDown() {
		return typeDropDown;
	}

	public WebElement getCustomerDropDown() {
		return customerDropDown;
	}

}
