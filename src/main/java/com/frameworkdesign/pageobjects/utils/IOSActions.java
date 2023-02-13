package com.frameworkdesign.pageobjects.utils;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.PageFactory;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class IOSActions extends CommomUtils {

	IOSDriver driver;

	public IOSActions(IOSDriver driver) {
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);

	}

	public void longPressAction(WebElement ele) {

		Map<String, Object> params = new HashMap<>();
		params.put("element", ((RemoteWebElement) ele).getId());
		params.put("duration", 5);
		driver.executeScript("mobile:touchAndHold", params);
	}

	public void swipeAction(WebElement ele, String direction) {
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("direction", "left");
		driver.executeScript("mobile:swipe", parameters);

	}

	public void scrollAction() {
		boolean canScrollMore;
		do {

			canScrollMore = (Boolean) ((JavascriptExecutor) driver).executeScript("mobile: scrollGesture", ImmutableMap
					.of("left", 100, "top", 100, "width", 200, "height", 200, "direction", "down", "percent", 3.0

					));
		} while (canScrollMore);
	}

	public void srollToText(WebElement ele) {
		Map<String, Object> params = new HashMap<>();
		params.put("direction", "down");
		params.put("element", ((RemoteWebElement) ele).getId());
		driver.executeScript("mobile:scroll", params);

	}

}
