package org.appium.pageobjects.Android;

import java.util.List;

import org.appium.Android.utils.AndroidActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class productCataloguePage extends AndroidActions{
	
	AndroidDriver driver;
	public productCataloguePage(AndroidDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	@AndroidFindBy(xpath="(//android.widget.TextView[@resource-id=\"com.androidsample.generalstore:id/productAddCart\"])")
	public List<WebElement> addToCart;
	
	@AndroidFindBy(xpath = "//android.widget.ImageButton[@resource-id=\"com.androidsample.generalstore:id/appbar_btn_cart\"]")
	public WebElement cartIcon;
	
	public void addItemToCartByIndex(int index) {
		addToCart.get(index).click();
	
	}

	public /*CartPage*/ void checkoutpage() throws InterruptedException {
		cartIcon.click();
		Thread.sleep(2000);
		//return new CartPage(driver);
	}
	

	
}
