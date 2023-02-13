package androidFrameworkDesign.TestUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import com.frameworkdesign.pageobjects.ios.IOSHomePage;
import com.frameworkdesign.pageobjects.utils.CommomUtils;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import io.appium.java_client.service.local.AppiumDriverLocalService;

public class IOSBaseTest extends CommomUtils{

	public IOSDriver driver;
	public AppiumDriverLocalService service;
	public IOSHomePage iosHomePage;

	@BeforeClass
	public void ConfigureAppium() throws IOException {
		
		Properties props = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"//src//main//java//resources//data.properties");
		props.load(fis);
		String ipAddress = props.getProperty("ipAddress");
		String portNumber = props.getProperty("port");

		// Code to start the server automatically?
		service = startAppiumServer(ipAddress, Integer.parseInt(portNumber));
		
		// Set the desired capabilities here
		XCUITestOptions options = new XCUITestOptions();
		options.setDeviceName(props.getProperty("IOSDeviceName"));
		options.setApp("Path of the uiCatalogApp/or any other ios app");
		options.setPlatformVersion("15.5");
		// Appium - Webdriver Agent -> IOS Apps.
		options.setWdaLaunchTimeout(Duration.ofSeconds(20));

		driver = new IOSDriver(service.getUrl(), options);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		iosHomePage = new IOSHomePage(driver);
	}

	@AfterClass
	public void tearDown() {
		driver.quit();
		service.stop();
	}
}
