package androidFrameworkDesign;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.frameworkdesign.pageobjects.android.FormPage;

import androidFrameworkDesign.TestUtils.AndroidBaseTest;

public class eCommerce_tc_01 extends AndroidBaseTest {
	
	/*
	 * @BeforeMethod public void preSetup() {
	 * 
	 * formPage.setActivity(); }
	 */

	@Test
	public void FillForm_ErrorValidationTest() {

		//FormPage formPage = new FormPage(driver);
		formPage.setNameField(""); //Leave it empty to validate
		formPage.SetCountrySeletion("Argentina");
		formPage.submitForm();
		
		String toastMessage = formPage.getToastMessage();
		System.out.println("Error message displayed is: " + toastMessage);
		Assert.assertEquals(toastMessage, "Please enter your name");
	
	}

	
	@Test
	public void FillForm_PositiveFlowTest() {

		formPage = new FormPage(driver);
		formPage.setNameField("Nasir"); //Leave it empty to validate
		formPage.SetCountrySeletion("Australia");
		formPage.submitForm();
		System.out.println("Error message is not dispalyed, Form is filled successfully and submitted");

		
		//Assert.assertTrue(formPage.getToastMessages().size()<1);
	}
}
