package organizationTest;

import java.io.IOException;
import java.util.Random;

import org.apache.poi.EncryptedDocumentException;
import org.testng.annotations.Test;

import genericUtility.BaseClass;
import genericUtility.ExcelFileUtility;
import genericUtility.WebDriverUtility;
import objectRepository.CreateOrganizationPage;
import objectRepository.HomePage;
import objectRepository.OrganizationInfoPage;
import objectRepository.OrganizationPage;

public class ToCreateOrgWithTypeDropdownTest extends BaseClass {

	@Test(groups = "regration")
	public void toCreateOrgWithTypeDropdown_003() throws EncryptedDocumentException, IOException {
		HomePage hp = new HomePage(driver);
		hp.getOrganizationsLink().click();
		OrganizationPage op = new OrganizationPage(driver);
		op.getOrgLookupImg().click();
		CreateOrganizationPage cop = new CreateOrganizationPage(driver);
		ExcelFileUtility eutil = new ExcelFileUtility();
		Random r = new Random();
		int random = r.nextInt(1000);
		String ORGTEXTFIELD = eutil.toReadDataFromExcel("Organization", 1, 2);
		cop.getOrganizationTextfield().sendKeys(ORGTEXTFIELD + random);

		WebDriverUtility wutil = new WebDriverUtility();
		wutil.toHandleDropDown("Energy", cop.getIndustryDropDown());
		wutil.toHandleDropDown("Customer", cop.getTypeDropDown());

		cop.getSaveButton().click();
		OrganizationInfoPage oip = new OrganizationInfoPage(driver);
		String organizationtextfield = oip.getHeaderTitle().getText();
		if (organizationtextfield.contains(ORGTEXTFIELD)) {
			System.out.println(organizationtextfield + "---Passed");
		} else {
			System.out.println(organizationtextfield + "---Failed");

		}
	}

}
