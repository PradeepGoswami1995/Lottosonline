import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Login {
    private static WebDriver chromeDriver;
    private static WebDriver firefoxDriver;
    private static WebDriverWait chromeWait;
    private static WebDriverWait firefoxWait;

    @BeforeClass
    public void setup() {
        // Initialize Chrome driver
        System.setProperty("webdriver.chrome.driver", "E:\\Lottos Java\\lib\\driver\\chromedriver-win64\\chromedriver.exe");
        chromeDriver = new ChromeDriver();
        chromeWait = new WebDriverWait(chromeDriver, Duration.ofSeconds(5));

        // Initialize Firefox driver
        System.setProperty("webdriver.gecko.driver", "E:\\Lottos Java\\lib\\driver\\chromedriver-win64\\geckodriver.exe");
        firefoxDriver = new FirefoxDriver();
        firefoxWait = new WebDriverWait(firefoxDriver, Duration.ofSeconds(5));

        // Navigate to the application URL
        chromeDriver.get("https://lottosonline.thefamcomlab.com");
        firefoxDriver.get("https://lottosonline.thefamcomlab.com");
    }

    @AfterClass
    public void tearDown() {
        if (chromeDriver != null) {
            chromeDriver.quit();
        }
        if (firefoxDriver != null) {
            firefoxDriver.quit();
        }
    }

    @Test
    public void testAdminLoginOnChrome() {
        testAdminLogin(chromeDriver, chromeWait);
    }

    @Test
    public void testAdminLoginOnFirefox() {
        testAdminLogin(firefoxDriver, firefoxWait);
    }

    public void testAdminLogin(WebDriver driver, WebDriverWait wait) {
        clickAndWait(driver, wait, By.linkText("Login"));
        driver.findElement(By.id("email")).sendKeys("pradeepadmin@yopmail.com");
        driver.findElement(By.id("password")).sendKeys("famcom");
        driver.findElement(By.id("submit_button")).click();
        Reference.sleep(5);
    }

    public void clickAndWait(WebDriver driver, WebDriverWait wait, By locator) {
        driver.findElement(locator).click();
        wait.until(ExpectedConditions.elementToBeClickable(locator));
    }
}
