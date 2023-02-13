package androidFrameworkDesign.TestUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import com.frameworkdesign.pageobjects.android.FormPage;
import com.frameworkdesign.pageobjects.utils.CommomUtils;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;

public class AndroidBaseTest extends CommomUtils {

	public AndroidDriver driver;
	public AppiumDriverLocalService service;
	public FormPage formPage;

	@BeforeClass(alwaysRun=true)//irrespective of groups @beforeClass will run because of alwaysRun=true
	public void ConfigureAppium() throws IOException {
		
		Properties props = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"//src//main//java//resources//data.properties");
		String ipAddress = System.getProperty("ipAddress")!=null ? System.getProperty("ipAddress"): props.getProperty("ipAddress");;
		props.load(fis);
		//String ipAddress= props.getProperty("ipAddress");
		String port = props.getProperty("port");

		service = startAppiumServer(ipAddress, Integer.parseInt(port));

		// Set the desired capabilities here
		UiAutomator2Options options = new UiAutomator2Options();
		options.setDeviceName(props.getProperty("AndroidDeviceName"));
		options.setChromedriverExecutable("C://Users//Khadhar//Downloads//chromedriver_win32 95V"); //In order to handle chrome browser
		//options.setApp("C://Users//Khadhar//eclipse-workspace//Appium//src//test//java//resources//ApiDemos-debug.apk");
		options.setApp(System.getProperty("user.dir")+"//src//main//java//resources//General-Store.apk");

		// Appium Architecture - Appium code -> Appium Server -->Mobile
		// Need to create an object for Android driver as we are going to test Android device
		//AndroidDriver driver = new AndroidDriver();
		driver = new AndroidDriver(service.getUrl(), options);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		formPage = new FormPage(driver);
	}
	
	@AfterClass
	public void tearDown() {
		driver.quit();
		service.stop();
	}
}
