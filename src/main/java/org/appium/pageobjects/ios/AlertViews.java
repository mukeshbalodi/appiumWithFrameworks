package org.appium.pageobjects.ios;

import java.time.Duration;

import org.appium.ios.utils.iosActions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class AlertViews extends iosActions{

IOSDriver driver;
	
	public AlertViews(IOSDriver driver) {
		super (driver);       // just remove this line and see the error "Implicit super constructor AndroidActions() is undefined. Must explicitly invoke another constructor"
		this.driver = driver;
		 driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	@iOSXCUITFindBy(xpath = "**/XCUIElementTypeStaticText[`label=='Text Entry'`]")
	private WebElement textEntryMenu;
	
	@iOSXCUITFindBy(iOSNsPredicate = "type == 'XCUIElementTyepStaticText' AND value BEGINSWITH[c] 'Confirm'")
	private WebElement confirmMenuItem;
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeCell")
	private WebElement textBox;
	
	@iOSXCUITFindBy(accessibility = "OK")
	private WebElement acceptPopUp;
	
	@iOSXCUITFindBy(iOSNsPredicate = "name BEGINSWITH[c] 'A message'")
	private WebElement confirmMessage;
	
	@iOSXCUITFindBy(iOSNsPredicate = "label == 'Confirm'")
	private WebElement submit;
	
	public void fillTextLabel(String name) {
		textEntryMenu.click();
		textBox.sendKeys(name);
		acceptPopUp.click();
	}
	
	public String getConfirmMessage() {
		confirmMenuItem.click();
	return 	confirmMessage.getText();
		
	}
	
	
	
	
	
	
}
