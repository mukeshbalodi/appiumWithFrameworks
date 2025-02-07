package org.appium.ios.utils;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;

public class iosActions {
	
	IOSDriver driver;
	public iosActions(IOSDriver driver) {
		this.driver = driver;
	}
	

	public void iosLongPressTest(WebElement ele) throws MalformedURLException, URISyntaxException {
	    // Make sure that the element is visible and clickable before performing the action.
	    Map<String, Object> params = new HashMap<>();
	    params.put("element", ((RemoteWebElement) ele).getId());
	    params.put("duration", 5);  // Specify the duration for the long press in seconds.

	    // Use mobile:touchAndHold for iOS long press gesture.
	    driver.executeScript("mobile:touchAndHold", params);
	}

		
	
	

	
	public void iosScrollTest(WebElement ele) {
    Map<String, Object> params = new HashMap<>();
    params.put("direction", "down");  // Scroll direction ("up", "down", "left", "right")
    params.put("element", ((RemoteWebElement) ele).getId());  // Target element

    // For iOS, use 'mobile:scroll' for scrolling actions.
    driver.executeScript("mobile:scroll", params);
}


	
	public void dragDropAction(WebElement source, int endX, int endY) {
	    Map<String, Object> params = new HashMap<>();
	    params.put("elementId", ((RemoteWebElement) source).getId());  // The source element's ID
	    params.put("endX", endX);  // X-coordinate of the destination
	    params.put("endY", endY);  // Y-coordinate of the destination
	    
	    // Use "mobile:dragAndDrop" for iOS to perform the drag-drop gesture.
	    driver.executeScript("mobile:dragAndDrop", params);
	}

	
	public void scrollToText(String text) {
	    Map<String, Object> params = new HashMap<>();
	    params.put("direction", "down");  // Scroll direction, "down" for scrolling down
	    params.put("predicateString", "label == '" + text + "'");  // Searching for the text in the accessibility label
	    
	    driver.executeScript("mobile:scroll", params);  // Use mobile:scroll for iOS
	}

	
	public void TakeScreenshot(String location) throws IOException {
	    // Assuming 'driver' is an instance of IOSDriver (not parameterized)
	    IOSDriver driver = (IOSDriver) this.driver;

	    // Take screenshot and get it as a base64 encoded string
	    String base64Screenshot = driver.getScreenshotAs(OutputType.BASE64);

	    // Decode the base64 string to get the raw bytes
	    byte[] decodedBytes = java.util.Base64.getDecoder().decode(base64Screenshot);

	    // Write the bytes to a file
	    File screenshotFile = new File(location);
	    FileUtils.writeByteArrayToFile(screenshotFile, decodedBytes);
	}
	}











