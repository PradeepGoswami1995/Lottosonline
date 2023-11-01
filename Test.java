import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.support.ui.WebDriverWait;

class Dmunty1 {
    public static void main(String[] args) throws InterruptedException, IOException {
        // Set the path to the chromedriver executable
        System.setProperty("webdriver.chrome.driver", "E:\\JavaP3\\lib\\driver\\chromedriver-win64\\chromedriver.exe");

        // Create a new instance of the ChromeDriver
        WebDriver driver = new ChromeDriver();

        // Open the Chrome browser
        driver.get("https://stage-dmunity.thefamcomlab.com/");
        driver.manage().window().maximize();

        // WebDriverWait wait = new WebDriverWait(driver, 10);

        // Click on the login button
        driver.findElement(By.xpath("//*[@id=\"offcanvasNavbar-expand-lg\"]/div[2]/div/button[1]")).click();
        Thread.sleep(5000);

        // Enter email and password
        driver.findElement(By.id(":r0:")).sendKeys("linon@yopmail.com");
        // Thread.sleep(2000);
        driver.findElement(By.id(":r1:")).sendKeys("12345678");

        // Wait for 2 seconds
        Thread.sleep(2000);

        // Click on the login button
        driver.findElement(By.xpath("//*[@id=\"__next\"]/div[1]/div/div/div/div/div[1]/div/div/div/form/button"))
                .click();
        // Thread.sleep(5000);
        driver.findElement(By.xpath("//*[@id=\"__next\"]/div[1]/section/div/div/div[1]/button")).click();
        driver.findElement(By.id("outlined-required")).sendKeys("Linon");

        // Take a screenshot
        TakesScreenshot ts = (TakesScreenshot) driver;
        File screenshot = ts.getScreenshotAs(OutputType.FILE);

        // Generate a unique file name using a timestamp
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
        String timestamp = dateFormat.format(new Date());
        String fileName = "Dmunity_" + timestamp + ".png";

        // Specify the destination file
        File destination = new File("D:\\Images\\" + fileName);

        // Copy the screenshot file to the destination
        try {
            FileUtils.copyFile(screenshot, destination);
            System.out.println("Screenshot saved: " + destination.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Close the browser
        driver.quit();

    }
}