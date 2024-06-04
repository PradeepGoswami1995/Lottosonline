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
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class Draft1 {
    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeClass
    @Parameters("browser")
    public void setDriver(String browser) {
        if (browser.equalsIgnoreCase("chrome")) {
            System.setProperty("webdriver.chrome.driver", "E:\\Lottos Java\\lib\\driver\\chromedriver-win64\\chromedriver.exe");
            driver = new ChromeDriver();
            wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        } else if (browser.equalsIgnoreCase("firefox")) {
            System.setProperty("webdriver.gecko.driver", "E:\\Lottos Java\\lib\\driver\\geckodriver.exe");
            driver = new FirefoxDriver();
            wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        }
        driver.get("https://lottosonline.thefamcomlab.com");
        driver.manage().window().maximize();
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void openAccount() {
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
        waitForClickable(driver, wait, By.id("submit_button"), 0).click();
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

    public String generateUniqueName(String baseName) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        return baseName + dateFormat.format(new Date());
    }

    public String generateUniqueEmail() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        return "test" + dateFormat.format(new Date()) + "@yopmail.com";
    }

    public void sleep(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
