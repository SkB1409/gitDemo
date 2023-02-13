package com.frameworkdesign.pageobjects.ios;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.frameworkdesign.pageobjects.utils.IOSActions;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class AlertViews extends IOSActions {

	IOSDriver driver;

	public AlertViews(IOSDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);

	}

	//driver.findElement(AppiumBy.accessibilityId("Alert Views")).click();
	@iOSXCUITFindBy(accessibility = "Alert Views")
	private WebElement alertViews;

	public void selectAlertViews() {
		alertViews.click();
	}
	
	//driver.findElement(AppiumBy.iOSClassChain("**/XCUIElementTypeStaticText[`label=='Text Entry'`]")).click(); // slightly
	@iOSXCUITFindBy(iOSClassChain="**/XCUIElementTypeStaticText[`label=='Text Entry'`]")
	private WebElement textEntryMenu;
	
	@iOSXCUITFindBy(iOSClassChain="**/XCUIElementTypeCell")
	private WebElement textBox;
	
	@iOSXCUITFindBy(accessibility = "OK")
	private WebElement acceptPopup;

	@iOSXCUITFindBy(iOSNsPredicate="type=='XCUIElementTypeStaticText' AND value BEGINSWITH[c] 'Confirm'")
	private WebElement confirmMenuItem;
	
	@iOSXCUITFindBy(iOSNsPredicate="name BEGINS WITH[c] 'A message'")
	private WebElement confirmationMessage;
	
	//label == 'Confirm'
	@iOSXCUITFindBy(iOSNsPredicate="label == 'Confirm'")
	private WebElement submit;
	
	public void fillTextLabel(String name) {
		textEntryMenu.click();
		textBox.sendKeys(name);
		acceptPopup.click();
	}
	
	public String getConfirmMessage() {
		confirmMenuItem.click();
		return confirmationMessage.getText();
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
