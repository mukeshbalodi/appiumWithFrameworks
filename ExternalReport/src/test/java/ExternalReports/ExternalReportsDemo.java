package ExternalReports;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class ExternalReportsDemo extends BaseTest {

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
        driver.navigate().back();
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
}
