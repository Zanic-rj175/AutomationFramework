package organizationTest;

import java.io.IOException;
import java.util.Random;

import org.apache.poi.EncryptedDocumentException;
import org.testng.annotations.Test;

import genericUtility.BaseClass;
import genericUtility.ExcelFileUtility;
import objectRepository.CreateOrganizationPage;
import objectRepository.HomePage;
import objectRepository.OrganizationInfoPage;
import objectRepository.OrganizationPage;

public class ToCreateOrgTest extends BaseClass {

	@Test(groups = "regration")
	public void toCreateOrgTest_001() throws EncryptedDocumentException, IOException {
		HomePage hp = new HomePage(driver);
		hp.getOrganizationsLink().click();
		OrganizationPage op = new OrganizationPage(driver);
		op.getOrgLookupImg().click();
		CreateOrganizationPage cop = new CreateOrganizationPage(driver);
		ExcelFileUtility eutil = new ExcelFileUtility();
		Random r = new Random();
		int random = r.nextInt(1000);
		String ORGTEXTFIELD = eutil.toReadDataFromExcel("Organization", 1, 2);
		cop.getOrganizationTextfield().sendKeys("Wipro"+random);
		cop.getSaveButton().click();
		OrganizationInfoPage oip = new OrganizationInfoPage(driver);
		String orgTextField = oip.getHeaderTitle().getText();
		if (orgTextField.contains(ORGTEXTFIELD)) {
			System.out.println(orgTextField + "---Passed");
		} else {
			System.out.println(orgTextField + "---Failed");
		}

	}

}
