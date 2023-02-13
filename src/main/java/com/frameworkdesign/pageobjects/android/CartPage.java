package com.frameworkdesign.pageobjects.android;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.frameworkdesign.pageobjects.utils.AndroidActions;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class CartPage extends AndroidActions {

	AndroidDriver driver;

	public CartPage(AndroidDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);

	}

	// List<WebElement> productPrices =
	// driver.findElements(By.id("com.androidsample.generalstore:id/productPrice"));
	@AndroidFindBy(id = "com.androidsample.generalstore:id/productPrice")
	private List<WebElement> productList;

	// String displaySum =
	// driver.findElement(By.id("com.androidsample.generalstore:id/totalAmountLbl")).getText();
	@AndroidFindBy(id = "com.androidsample.generalstore:id/totalAmountLbl")
	private WebElement totalAmount;

	@AndroidFindBy(id = "com.androidsample.generalstore:id/termsButton")
	private WebElement terms;

	@AndroidFindBy(id = "android:id/button1")
	private WebElement acceptButton;

	// com.androidsample.generalstore:id/btnProceed
	@AndroidFindBy(id = "com.androidsample.generalstore:id/btnProceed")
	private WebElement proceedButton;

	@AndroidFindBy(className = "android.widget.CheckBox")
	private WebElement checkBox;

	public List<WebElement> getProductList() {
		return productList;
	}

	public double getProductSum() {
		int productCount = productList.size();
		double totalSum = 0;
		for (int i = 0; i < productCount; i++) {
			String amountString = productList.get(i).getText();
			double price = formattedAmount(amountString);
			totalSum = totalSum + price;
		}
		return totalSum;
	}

	public double getTotalAmountDisplayed() {
		return formattedAmount(totalAmount.getText());

	}

	public void acceptTermsNConditions() {
		longPressAction(terms);
		acceptButton.click();
	}

	public double formattedAmount(String amount) {
		Double price = Double.parseDouble((amount).substring(1)); // removing the $ from $123.45 and converting string
																	// // into integer to add
		return price;
	}

	public void submitOrder() {
		checkBox.click();
		proceedButton.click();

	}

}
