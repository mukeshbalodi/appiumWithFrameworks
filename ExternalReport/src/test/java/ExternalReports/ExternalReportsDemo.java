package ExternalReports;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExternalReportsDemo {
    ExtentReports extent;
    WebDriver driver=null;

    @BeforeTest
    public void config() {
        // ExternalReports, ExtentSparkReporter
        String path = System.getProperty("user.dir") + "\\reports\\index.html";
        ExtentSparkReporter reporter = new ExtentSparkReporter(path);
        reporter.config().setReportName("Web Automation Results");
        reporter.config().setDocumentTitle("Test Results");

        extent = new ExtentReports();
        extent.attachReporter(reporter);
        extent.setSystemInfo("Tester", "Mukesh");
    }

    @Test
    public void initialDemo() {
        extent.createTest("Initial Demo");

        // Initialize the WebDriver (chrome driver)
        System.setProperty("webdriver.chrome.driver", "E://chromedriver-win64//chromedriver.exe"); 
        driver = new ChromeDriver(); 

        driver.manage().window().maximize();
        driver.get("https://www.rahulshettyacademy.com");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        System.out.println(driver.getTitle());
        extent.flush();
        // Flush the reports after the test
        
    }

    @Test
    public void secondTest() throws InterruptedException {
    	 extent.createTest("Second test");
    	 driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    	 driver.findElement(By.xpath("//a[@class='btn btn-theme btn-sm btn-min-block']")).click();
    	 Thread.sleep(2000);
    	 extent.flush();
    }
    @AfterTest
    public void TearDown() {
        if (driver != null) {
            driver.quit(); 
            
        }
    }
}
