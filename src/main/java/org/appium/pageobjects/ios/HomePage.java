package org.appium.pageobjects.ios;

import java.time.Duration;

import org.appium.ios.utils.iosActions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class HomePage extends iosActions {

IOSDriver driver;
	
	public HomePage(IOSDriver driver) {
		super (driver);       // just remove this line and see the error "Implicit super constructor AndroidActions() is undefined. Must explicitly invoke another constructor"
		this.driver = driver;
		 driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	@iOSXCUITFindBy(accessibility = "Alert Views")
	private WebElement alertViews;
	
	public AlertViews selectAlertViews() {
		alertViews.click();
		return new AlertViews(driver);
	}
	
	
	
}
