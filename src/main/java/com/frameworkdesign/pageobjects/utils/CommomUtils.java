package com.frameworkdesign.pageobjects.utils;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class CommomUtils {
	public AppiumDriverLocalService service;


	public double formattedAmount(String amount) {
		double price = Double.parseDouble((amount).substring(1)); // removing the $ from $123.45 and converting string
																	// // into integer to add
		return price;
	}

	public List<HashMap<String, String>> getJsonData(String jsonFilePath) throws IOException {
		// Convert json file content to json string
		@SuppressWarnings("deprecation")
		String jsonContent = FileUtils.readFileToString(
				new File(jsonFilePath));

		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsonContent,
				new TypeReference<List<HashMap<String, String>>>() {
				});
		return data;

	}
	
	public AppiumDriverLocalService startAppiumServer(String ipAddress, int port) {
		service = new AppiumServiceBuilder()
				.withAppiumJS(new File("C://Users//Khadhar//AppData//Roaming//npm//node_modules//appium//lib//main.js"))
				.withIPAddress(ipAddress).usingPort(port).build();
		service.start(); //If the server is not getting started automatically then we
		return service;
		// need to manually start the server
	}

	public void waitForElementsToAppear(WebElement ele, AppiumDriver driver) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.attributeContains((ele), "text", "Cart"));

	}
	
	public String getScreenshotPath(String testCaseName, AppiumDriver driver) throws IOException {
		File source = driver.getScreenshotAs(OutputType.FILE);
		String destinationFile = System.getProperty("user=dir")+"//reports"+testCaseName+".png";
		FileUtils.copyFile(source, new File(destinationFile));
		return destinationFile;
		
	}

}
