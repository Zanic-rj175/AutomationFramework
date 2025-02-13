package practice;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class ToLearnAssertion {
	@Test
	public void sample() {
		System.out.println("Step 1");
		System.out.println("Step 2");
		//Hard Assert
		Assert.assertEquals(false, false);
		System.out.println("Step 3");
		System.out.println("Step 4");
		
		//Soft Assert
		SoftAssert sa = new SoftAssert();
		sa.assertEquals(false, true);
		System.out.println("Step 5");
		System.out.println("Step 6");
		sa.assertAll();
	}
}
