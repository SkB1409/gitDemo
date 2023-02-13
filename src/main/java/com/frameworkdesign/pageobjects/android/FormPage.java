package com.frameworkdesign.pageobjects.android;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.frameworkdesign.pageobjects.utils.AndroidActions;

import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class FormPage extends AndroidActions {

	AndroidDriver driver;

	//create a constrctor which is nothing but the object of class FormPage
	public FormPage(AndroidDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	// driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("Khadar Basha");
	@AndroidFindBy(id = "com.androidsample.generalstore:id/nameField")
	private WebElement nameField;

	// driver.findElement(By.xpath("//android.widget.RadioButton[@text='Female']")).click();
	@AndroidFindBy(xpath = "//android.widget.RadioButton[@text='Female']")
	private WebElement femaleOptions;

	@AndroidFindBy(xpath = "//android.widget.RadioButton[@text='Male']")
	private WebElement maleOptions;

	@AndroidFindBy(id = "android:id/text1")
	private WebElement countrySelection;

	@AndroidFindBy(id = "com.androidsample.generalstore:id/btnLetsShop")
	private WebElement shopButton;

	//android.widget.Toast
	@AndroidFindBy(xpath = "//android.widget.Toast")
	private WebElement toastMessage;

	@AndroidFindBy(xpath = "//android.widget.Toast")
	private List<WebElement> toastMessages;
	
	public void setActivity() {
		
			Activity activity = new Activity("'com.androidsample.generalstore'","'com.androidsample.generalstore.MainActivity'");
			driver.startActivity(activity);
		}

	public void setNameField(String name) {
		nameField.sendKeys(name);
		driver.hideKeyboard();
	}

	public void setGender(String gender) {
		if (gender.contains("Female"))
			femaleOptions.click();
		else
			maleOptions.isSelected();
	}

	public void SetCountrySeletion(String countryName) {

		countrySelection.click();
		srollToText(countryName);
		driver.findElement(By.xpath("//android.widget.TextView[@text='" + countryName + "']")).click();

	}

	public ProductCatalog submitForm() {
		shopButton.click();
		return new ProductCatalog(driver);
	}

	public String getToastMessage() {
		return toastMessage.getAttribute("name");
	}

	public List<WebElement> getToastMessages() {
		return toastMessages;
	}

}
