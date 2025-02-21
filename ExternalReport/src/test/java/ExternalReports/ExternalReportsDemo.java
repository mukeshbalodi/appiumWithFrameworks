package ExternalReports;

import static org.testng.Assert.assertEquals;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExternalReportsDemo {
    ExtentReports extent;
    WebDriver driver = null;
    ExtentTest test;

    // Set up the Extent Reports
    @BeforeTest
    public void config() {
        String path = System.getProperty("user.dir") + "\\reports\\index.html";
        ExtentSparkReporter reporter = new ExtentSparkReporter(path);
        reporter.config().setReportName("Web Automation Results");
        reporter.config().setDocumentTitle("Test Results");

        extent = new ExtentReports();
        extent.attachReporter(reporter);
        extent.setSystemInfo("Tester", "Mukesh");
    }

    // This setup method runs before each test and initializes the WebDriver for the browser specified
    @BeforeClass
    @org.testng.annotations.Parameters("browser")
    public void setUp(String browser) {
        if (browser.equalsIgnoreCase("chrome")) {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--headless");
            driver = new ChromeDriver(options);
        } else if (browser.equalsIgnoreCase("firefox")) {
            FirefoxOptions options = new FirefoxOptions();
            options.setHeadless(true);
            driver = new FirefoxDriver(options);
        } else if (browser.equalsIgnoreCase("edge")) {
            EdgeOptions options = new EdgeOptions();
            options.setHeadless(true);
            driver = new EdgeDriver(options);
        }

        driver.manage().window().maximize();
        driver.get("https://rahulshettyacademy.com/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test(priority = 1)
    public void initialDemo() {
        test = extent.createTest("Initial Demo", "Testing driver setup");
        System.out.println(driver.getTitle());
    }

    @Test(priority = 2)
    public void secondTest() throws InterruptedException {
        test = extent.createTest("Second Test");
        driver.findElement(By.xpath("//a[@class='btn btn-theme btn-sm btn-min-block']")).click();
        Thread.sleep(2000);
        driver.navigate().back();
    }

    @Test(priority = 3)
    public void thirdTest() throws InterruptedException {
        test = extent.createTest("Third Test");
        driver.findElement(By.xpath("//span[@class='fa fa-linkedin']")).click();
        Thread.sleep(2000);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        
        driver.navigate().back();
        Thread.sleep(2000);
    }

    @Test(priority = 4)
    public void fourthTest() throws InterruptedException {
        test = extent.createTest("Fourth Test");
        driver.findElement(By.xpath("//a[normalize-space()='Courses']")).click();
        Thread.sleep(2000);
    }

    @Test(priority = 5)
    public void fifthTest() {
        test = extent.createTest("Fifth Test", "This test is expected to fail");
        try {
            driver.findElement(By.id("1234")).click(); // This will fail
            String actualTitle = driver.getTitle();
            assertEquals(actualTitle, "test"); // This will also fail
        } catch (Exception e) {
            test.fail("Test Failed due to Exception: " + e.getMessage());
        }
    }

    // Tear down the WebDriver after each test
    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
        extent.flush();
    }
}
