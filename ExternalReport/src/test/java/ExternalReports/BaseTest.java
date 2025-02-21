package ExternalReports;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Optional;

import com.aventstack.extentreports.ExtentReports;

import com.aventstack.extentreports.ExtentTest;

public class BaseTest {
    public static WebDriver driver = null;
    public static ExtentReports extent;
    public static ExtentTest test;

    // Set up the Extent Reports (BeforeTest)
    @BeforeTest
    public void config() {
        String path = System.getProperty("user.dir") + "\\reports\\index.html";
        com.aventstack.extentreports.reporter.ExtentSparkReporter reporter = new com.aventstack.extentreports.reporter.ExtentSparkReporter(path);
        reporter.config().setReportName("Web Automation Results");
        reporter.config().setDocumentTitle("Test Results");

        extent = new ExtentReports();
        extent.attachReporter(reporter);
        extent.setSystemInfo("Tester", "Mukesh");
    }

    // Initialize WebDriver based on browser selection (@BeforeClass)
    @BeforeClass
    @Parameters("browser")
    public void setUp(@Optional("edge") String browser) {
        if (browser.equalsIgnoreCase("chrome")) {
            ChromeOptions options = new ChromeOptions();
            driver = new ChromeDriver(options);
        } else if (browser.equalsIgnoreCase("firefox")) {
            FirefoxOptions options = new FirefoxOptions();
            driver = new FirefoxDriver(options);
        } else if (browser.equalsIgnoreCase("edge")) {
            EdgeOptions options = new EdgeOptions();
            driver = new EdgeDriver(options);
        }

        driver.manage().window().maximize();
        driver.get("https://rahulshettyacademy.com/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    // Tear down the WebDriver after each test (@AfterClass)
    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
        extent.flush();
    }

  
}
