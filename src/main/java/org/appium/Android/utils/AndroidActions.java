package org.appium.Android.utils;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;

public class AndroidActions {
	
	AndroidDriver driver;
	public AndroidActions(AndroidDriver driver) {
		this.driver = driver;
	}
	

	public void LongPressAction(WebElement ele) {
		((JavascriptExecutor) driver).executeScript("mobile: longClickGesture", 
        		ImmutableMap.of("elementId", ((RemoteWebElement) ele).getId(), "duration", 2000));
        
		
	}
	
	public void scrollToEndAction() {
		boolean canScrollMore;
		do {
		canScrollMore = (Boolean) ((JavascriptExecutor) driver).executeScript("mobile: scrollGesture", ImmutableMap.of(
		    "left", 100, "top", 100, "width", 200, "height", 200,
		    "direction", "down",
		    "percent", 3.0
		));
		
		} while(canScrollMore);
	}
	
	public void swipeAction(WebElement ele, String direction) {
		((JavascriptExecutor) driver).executeScript("mobile: swipeGesture", ImmutableMap.of(
				   "elementId",((RemoteWebElement)ele).getId(),
				    "direction", "left",
				    "percent", 0.30 //up to which % you want to swipe form (0.01 to 1 i.e.100%)
				));
	}
	
	public void DragDropAction(WebElement source, int endX,int endY) {
		((JavascriptExecutor) driver).executeScript("mobile: dragGesture", ImmutableMap.of(
			    "elementId", ((RemoteWebElement) source).getId(),
			    "endX", endX,
			    "endY", endY
			));
	}
	
	public void scrollToText(String text) {
	    driver.findElement(AppiumBy.androidUIAutomator(
	        "new UiScrollable(new UiSelector()).scrollIntoView(text(\"" + text + "\"))"));
	}

	
	public void TakeScreenshot (String location) throws IOException {
		TakesScreenshot screenshot = (TakesScreenshot) driver;
		File source= screenshot.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(source, new File(location));
	}
}
