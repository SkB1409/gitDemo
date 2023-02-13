package androidFrameworkDesign.TestUtils;

import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.frameworkdesign.pageobjects.utils.CommomUtils;

import io.appium.java_client.AppiumDriver;

public class Listeners extends CommomUtils implements ITestListener {
	ExtentReports extent = ExtentReporterNG.getReporterObject();
	ExtentTest test;
	AppiumDriver driver;

	@Override
	public void onTestStart(ITestResult result) {

		test = extent.createTest(result.getMethod().getMethodName());
	}

	public void onTestSuccess(ITestResult result) {

		test.log(Status.PASS, "Test Passed");
	}

	public void onTestFailure(ITestResult result) {

		test.fail(result.getThrowable());

		try {
			driver = (AppiumDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			test.addScreenCaptureFromPath(getScreenshotPath(result.getMethod().getMethodName(), driver),
					result.getMethod().getMethodName());
		} catch (IOException e1) {
			e1.printStackTrace();

		}
	}

	public void onFinish(ITestContext context) {
		extent.flush();
	}
}
