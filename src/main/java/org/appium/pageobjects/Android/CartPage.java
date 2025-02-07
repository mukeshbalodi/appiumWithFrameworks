package org.appium.pageobjects.Android;

import org.appium.Android.utils.AndroidActions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class CartPage extends AndroidActions{
	AndroidDriver driver;
	public CartPage(AndroidDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	@AndroidFindBy(xpath = "//android.widget.CheckBox[@text=\"Send me e-mails on discounts related to selected products in future\"]")
	private WebElement checkbox;
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/btnProceed")
	private WebElement gotowebsite;
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/termsButton")
	public WebElement termsAndConditionscheck;
	
	public  void checkboxclick() {
		checkbox.click();
	}
	
	public  void gotoWebsiteForPurchase() {
		gotowebsite.click();
	}
	
	public void termsAndConditions(WebElement termsAndConditionscheck) {
		
	}

}
