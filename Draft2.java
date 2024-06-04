import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.Select;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Draft2 
{
    private static WebDriver chromeDriver;
    private static WebDriver firefoxDriver;
    private static WebDriverWait chromeWait;
    private static WebDriverWait firefoxWait;

    @BeforeClass
    public void setDriver() {
        // Initialize Chrome driver
        System.setProperty("webdriver.chrome.driver", "E:\\Lottos Java\\lib\\driver\\chromedriver-win64\\chromedriver.exe");
        chromeDriver = new ChromeDriver();
        chromeWait = new WebDriverWait(chromeDriver, Duration.ofSeconds(10));

        // Initialize Firefox driver
        System.setProperty("webdriver.gecko.driver", "E:\\Lottos Java\\lib\\driver\\geckodriver.exe");
        firefoxDriver = new FirefoxDriver();
        firefoxWait = new WebDriverWait(firefoxDriver, Duration.ofSeconds(10));

        // Navigate to the application URL for both browsers
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
    public void openAccountOnBothBrowsers() {
        openAccount(chromeDriver, chromeWait);
        openAccount(firefoxDriver, firefoxWait);
    }

    public void openAccount(WebDriver driver, WebDriverWait wait) {
        waitForClickable(driver, wait, By.xpath("//a[text()='Open Account']"), 10).click();

        // Generate unique values
        String uniqueFirstName = generateUniqueName("Pradeep");
        String uniqueLastName = generateUniqueName("TestUser");
        String uniqueEmail = generateUniqueEmail();

        // Use the unique values to fill in the form
        waitForClickable(driver, wait, By.id("firstname"), 2).sendKeys(uniqueFirstName);
        waitForClickable(driver, wait, By.id("lastname"), 2).sendKeys(uniqueLastName);
        waitForClickable(driver, wait, By.id("email"), 2).sendKeys(uniqueEmail);
        waitForClickable(driver, wait, By.id("email_confirm"), 2).sendKeys(uniqueEmail);

        waitForClickable(driver, wait, By.id("password"), 2).sendKeys("famcom");
        waitForClickable(driver, wait, By.id("contact_phone"), 2).sendKeys("9599899386");

        // Select country
        selectCountry(driver, wait, "India");

        waitForClickable(driver, wait, By.id("notifications_notification_email"), 1).click();
       //waitForClickable(driver, wait, By.id("submit_button"), 0).click();
        sleep(5);
    }

    public void selectCountry(WebDriver driver, WebDriverWait wait, String countryName) {
        WebElement countryDropdown = waitForClickable(driver, wait, By.id("country"), 10);
        Select select = new Select(countryDropdown);
        select.selectByVisibleText(countryName);
    }

    public WebElement waitForClickable(WebDriver driver, WebDriverWait wait, By locator, int timeoutSeconds) {
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    // Method to generate a unique name
    public String generateUniqueName(String baseName) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        return baseName + dateFormat.format(new Date());
    }

    // Method to generate a unique email
    public String generateUniqueEmail() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        return "test" + dateFormat.format(new Date()) + "@yopmail.com";
    }

    // Simple sleep method (you can replace this with any utility method you have)
    public void sleep(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
