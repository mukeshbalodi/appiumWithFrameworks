package frameworks.appium.ios;

import static org.testng.Assert.assertEquals;

import org.appium.pageobjects.ios.AlertViews;

public class iosBasics extends BaseTest {

	public void IOSBasicsTest() {
	AlertViews alertViews = homePage.selectAlertViews();
	alertViews.fillTextLabel("Hello");
	String actualMessage = alertViews.getConfirmMessage();
	assertEquals(actualMessage, "A message should be short, complete sentence.");
		
	}
}
