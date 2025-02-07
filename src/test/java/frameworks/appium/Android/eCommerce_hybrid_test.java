package frameworks.appium.Android;

import static org.testng.Assert.assertEquals;

import java.util.List;
import java.util.Set;

import org.appium.Android.utils.AndroidActions;
import org.appium.pageobjects.Android.CartPage;
import org.appium.pageobjects.Android.productCataloguePage;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;

public class eCommerce_hybrid_test extends BaseTest{
	@Test(dataProvider="getData")
	public void fillForm(String name, String gender, String country) throws  InterruptedException {
		
		
		formpage.setNameField(name);
		formpage.setGender(gender);
		formpage.setCountrySelection(country); // goto the method setcountry selection in form page then go to the method scroll to text in utils android actions.
		productCataloguePage productCataloguePage = formpage.submitForm();
		productCataloguePage.addItemToCartByIndex(0);
		productCataloguePage.addItemToCartByIndex(1);
		productCataloguePage.checkoutpage();
		//CartPage CartPage = productCataloguePage.checkoutpage();
		CartPage cartPage = new CartPage(driver);
		WebElement ele =	driver.findElement(By.id("com.androidsample.generalstore:id/termsButton"));
		cartPage.LongPressAction(ele);
		driver.findElement(By.id("android:id/button1")).click();
		List<WebElement> productPrices = driver.findElements(By.xpath("//android.widget.TextView[@resource-id=\"com.androidsample.generalstore:id/productPrice\"]"));
		int count = productPrices.size();
		double totalSum = 0;
		for(int i = 0; i < count; i++) {
		    String amountString = productPrices.get(i).getText(); // This gets the text like "$160.97"
		    // Remove the dollar sign and any commas (if present)
		    amountString = amountString.replaceAll("[^\\d.]", "");
		    // Now parse the cleaned string into a double
		    double price = Double.parseDouble(amountString);
		    totalSum += price;
		}

		String displaySum = driver.findElement(By.id("com.androidsample.generalstore:id/totalAmountLbl")).getText();
		// Remove dollar sign and any commas from the total amount displayed as well
		displaySum = displaySum.replaceAll("[^\\d.]", "");
		double displaySum1 = Double.parseDouble(displaySum);
		// Assert that the total sum calculated matches the displayed total sum
		assertEquals(totalSum, displaySum1);

		cartPage.checkboxclick();
		cartPage.gotoWebsiteForPurchase();
		Thread.sleep(5000);
		
		Set<String> contexts=driver.getContextHandles();
		for (String contextName:contexts) {
			System.out.println(contextName);
		}
		
		driver.context("WEBVIEW_com.androidsample.generalstore");
		driver.findElement(By.name("q")).sendKeys("mukesh");
		driver.findElement(By.name("q")).sendKeys(Keys.ENTER);
		Thread.sleep(2000);
		driver.pressKey(new KeyEvent(AndroidKey.BACK));
		driver.context("NATIVE_APP");
		Thread.sleep(2000);
		
		}

	@DataProvider
	public Object[] [] getData() {
		return new Object [][] {{"shanu ","female","Argentina"},{"shetty","female","Bangladesh"}};
	}
}
