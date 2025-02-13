package contactTest;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.testng.annotations.Test;

import genericUtility.BaseClass;
import genericUtility.ExcelFileUtility;
import genericUtility.WebDriverUtility;
import objectRepository.ContactInfoPage;
import objectRepository.ContactsPage;
import objectRepository.CreateContactPage;
import objectRepository.HomePage;

public class ToCreateContactwithOrgTest extends BaseClass {
	@Test(groups = "smoke")
	public void toCreateContactWithOrgTest_002() throws EncryptedDocumentException, IOException {
		HomePage hp = new HomePage(driver);
		hp.getContactLink().click();
		ContactsPage cp = new ContactsPage(driver);
		cp.getContactsLookUpImg().click();
		CreateContactPage ccp = new CreateContactPage(driver);
		ExcelFileUtility eutil = new ExcelFileUtility();
		String LASTNAME = eutil.toReadDataFromExcel("Contacts", 1, 2);
		ccp.getLastnameTextfielld().sendKeys(LASTNAME);
		ccp.getOrgLookUpImag().click();
		WebDriverUtility wutil = new WebDriverUtility();
		wutil.toSwitchWindow(driver, LASTNAME);
		ccp.getSearchtextfield().sendKeys("Wipro969");
		ccp.getSearchbutton().click();
		ccp.getSearchresult().click();
		wutil.toSwitchBackWindow(driver, LASTNAME);
		ccp.getSaveButton().click();
		ContactInfoPage cip = new ContactInfoPage(driver);
		String lastname = cip.getHeaderTitle().getText();
		if (lastname.contains(LASTNAME)) {
			System.out.println(lastname + "---Passed");
		} else {
			System.out.println(lastname + "---Failed");
		}

	}

}
