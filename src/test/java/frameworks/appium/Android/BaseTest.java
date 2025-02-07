package frameworks.appium.Android;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.commons.io.FileUtils;
import org.appium.pageobjects.Android.FormPage;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class BaseTest {
	public AndroidDriver driver;
	public AppiumDriverLocalService service;
	public FormPage formpage;
	@BeforeClass
	public void ServerConnection() throws MalformedURLException, URISyntaxException {
		 service = new AppiumServiceBuilder().withAppiumJS(new File ("C://Users//enque//AppData//Roaming//npm//node_modules//appium//build//lib//main.js"))
				.withIPAddress("127.0.0.1").usingPort(4723).build();
				service.start();
		UiAutomator2Options options = new UiAutomator2Options();
		//options.setDeviceName("Medium Phone API 35"); for virtual device
		options.setDeviceName("Android Device"); //Real Device
		options.setApp("C://Users//enque//eclipse-workspace//myappium//src//test//java//Resources//General-Store.apk");
		options.setChromedriverExecutable("E://chromedriver-win64//chromedriver.exe");
		driver = new AndroidDriver(new URI("http://127.0.0.1:4723").toURL(),options);
		
		System.out.println("Connection established with server");
		formpage = new FormPage(driver);
		
	}
	
	
	@AfterClass
	public void TearDown() {
		driver.quit();
		service.stop();
		
	}
}
