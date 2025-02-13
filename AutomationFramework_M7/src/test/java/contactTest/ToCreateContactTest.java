package contactTest;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import genericUtility.BaseClass;
import genericUtility.ExcelFileUtility;
import objectRepository.ContactInfoPage;
import objectRepository.ContactsPage;
import objectRepository.CreateContactPage;
import objectRepository.HomePage;
@Listeners(genericUtility.ListenersImplementation.class)
public class ToCreateContactTest extends BaseClass {
	@Test(groups = "smoke")
	public void toCreateContact_001() throws EncryptedDocumentException, IOException {
		HomePage hp = new HomePage(driver);
		hp.getContactLink().click();
		ContactsPage cp = new ContactsPage(driver);
		cp.getContactsLookUpImg().click();
		CreateContactPage ccp = new CreateContactPage(driver);
		ExcelFileUtility eutil = new ExcelFileUtility();
		String LASTNAME = eutil.toReadDataFromExcel("Contacts", 1, 2);
		ccp.getLastnameTextfielld().sendKeys(LASTNAME);
		ccp.getSaveButton().click();
		Assert.fail();
		ContactInfoPage cip = new ContactInfoPage(driver);
		String lastname = cip.getHeaderTitle().getText();
//		Assert.assertTrue(lastname.contains(LASTNAME));
//		Reporter.log("Contact create successfully",true);
		
		if (lastname.contains(LASTNAME)) {
			System.out.println(lastname + "---Passed");
		} else {
			System.out.println(lastname + "---Failed");
		}
	}

}
