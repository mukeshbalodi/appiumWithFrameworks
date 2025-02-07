package frameworks.appium.ios;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.Duration;

import org.appium.pageobjects.ios.HomePage;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;


public class BaseTest {
	
	
	public IOSDriver driver;
	public AppiumDriverLocalService service;
	public HomePage homePage;
	@BeforeClass
	
	public void ServerConnection() throws MalformedURLException, URISyntaxException {
		 service = new AppiumServiceBuilder().withAppiumJS(new File ("//usr//local//lib//node_modules//appiumbuild//lib//main.js"))
				.withIPAddress("127.0.0.1").usingPort(4723).build();
				service.start();
		
				XCUITestOptions options = new XCUITestOptions();
				options.setDeviceName("iPhone 13 Pro");
				//options.setApp("//Users//ann//Desktop//UIKitCatalog.app");
				options.setApp("//Users//ann//workingcode//Appium//src//test//java//Resources//TestApp 3.app");
				options.setPlatformVersion("15.5");
				options.setWdaLaunchTimeout(Duration.ofSeconds(20));
		
		driver = new IOSDriver(new URI("http://127.0.0.1:4723").toURL(),options);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		System.out.println("Connection established with server");
		
		 homePage = new HomePage(driver);
		
	}
	
	@AfterClass
	public void TearDown() {
		driver.quit();
		service.stop();
		
	}
}
