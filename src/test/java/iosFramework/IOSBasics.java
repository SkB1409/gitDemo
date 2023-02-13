package iosFramework;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.frameworkdesign.pageobjects.ios.AlertViews;

import androidFrameworkDesign.TestUtils.IOSBaseTest;

public class IOSBasics extends IOSBaseTest {

	@Test
	public void IOSBasicsTest() {

		// xpath, classname, IOS, IOSClassChain, IOSPredicateString, accesibilityId, id

		AlertViews alertViews = iosHomePage.selectAlertViews();
		alertViews.fillTextLabel("Hello World");
		String actualMessage = alertViews.getConfirmMessage();

		Assert.assertEquals(actualMessage, "A message should be a short, complete sentence.");

	}

}
