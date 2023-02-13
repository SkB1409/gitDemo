package androidFrameworkDesign;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.frameworkdesign.pageobjects.android.FormPage;

import androidFrameworkDesign.TestUtils.AndroidBaseTest;

public class eCommerce_tc_01 extends AndroidBaseTest {
	
	
	  @BeforeMethod public void preSetup() {
	  
	  formPage.setActivity(); }
	 

	@Test
	public void FillForm_ErrorValidationTest() {

		//FormPage formPage = new FormPage(driver);
		formPage.setNameField(""); //Leave it empty to validate
		formPage.SetCountrySeletion("Argentina");
		formPage.submitForm();
		
		String toastMessage = formPage.getToastMessage();
		System.out.println("Error message displayed is: " + toastMessage);
		Assert.assertEquals(toastMessage, "Please enter your name");
		System.out.println("Error message displayed is not dispalyed");

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
	
	@Test
	public void dummy01() {
		System.out.println("Hello World 001");
		
	}
	
	@Test
	public void dummy02() {
		
		System.out.println("Hello World 007");
		
	}
	@Test
	public void dummy03() {
		
		System.out.println("Hello World 007");
		
	}
}
