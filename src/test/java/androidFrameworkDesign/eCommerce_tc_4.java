package androidFrameworkDesign;

import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.frameworkdesign.pageobjects.android.CartPage;
import com.frameworkdesign.pageobjects.android.FormPage;
import com.frameworkdesign.pageobjects.android.ProductCatalog;

import androidFrameworkDesign.TestUtils.AndroidBaseTest;

public class eCommerce_tc_4 extends AndroidBaseTest {

	
	@Test(dataProvider = "getData", groups= {"Smoke"})
	public void AddMultipleProductsToCartAndVerifyBrowserNavigation(HashMap<String, String> input)
			throws InterruptedException {

		formPage = new FormPage(driver);
		formPage.setNameField(input.get("name"));
		formPage.setGender(input.get("gender"));
		formPage.SetCountrySeletion(input.get("country"));
		ProductCatalog productCatalogue = formPage.submitForm();
		productCatalogue.addItemToCartByIndex(0);
		productCatalogue.addItemToCartByIndex(0);

		CartPage cartPage = productCatalogue.goToCartPage();

		double totalSum = cartPage.getProductSum();
		double displayFormattedAmout = cartPage.getTotalAmountDisplayed();

		assertEquals(totalSum, displayFormattedAmout);

		// Longpress on terms button
		cartPage.acceptTermsNConditions();
		cartPage.submitOrder();
	}

	/*
	 * @BeforeMethod(alwaysRun=true) public void preSetup() {
	 * formPage.setActivity(); }
	 */

	@DataProvider
	public Object[][] getData() throws IOException {

		List<HashMap<String, String>> data = getJsonData(
				System.getProperty("user.dir") + "//src//test//java//testData//eCommerceData.json");

		// return new Object[][] { {"Khadar", "female", "Congo"}, {"Nasir", "male","India"} };

		return new Object[][] { { data.get(0) }, { data.get(1) } };
	}

}
