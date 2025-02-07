package org.appium.pageobjects.Android;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.appium.Android.utils.AndroidActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidBy;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class FormPage extends AndroidActions {
	
	AndroidDriver driver;
	
	public FormPage(AndroidDriver driver) {
		super (driver);       // just remove this line and see the error "Implicit super constructor AndroidActions() is undefined. Must explicitly invoke another constructor"
		this.driver = driver;
		 driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/nameField")
	private WebElement nameField;
	
	
	@AndroidFindBy(xpath="//android.widget.RadioButton[@resource-id=\"com.androidsample.generalstore:id/radioFemale\"]")
	private WebElement femaleOption;
	
	@AndroidFindBy(xpath="//android.widget.RadioButton[@resource-id=\\\"com.androidsample.generalstore:id/radioMale\\\"]")
	private WebElement maleOption;
	
	@AndroidFindBy(id = "android:id/text1")
	private WebElement countrySelection;
	
	
	
	@AndroidFindBy(id = "com.androidsample.generalstore:id/btnLetsShop")
	public WebElement shopButton;
	
	

	public void setNameField(String name) {
		nameField.sendKeys(name);
		driver.hideKeyboard();
		
	}
	
	public void setGender(String gender) {
		if (gender.contains("female"))
			femaleOption.click();
		else 
			maleOption.click();
	}
	
	public void setCountrySelection(String countryName) {
		countrySelection.click();
		scrollToText(countryName);
		driver.findElement(By.xpath("//android.widget.TextView[@text='"+countryName+"']")).click();
		
	}
	
	public productCataloguePage submitForm() {
		shopButton.click();
		return new productCataloguePage(driver);
	}
	
	
	
}
