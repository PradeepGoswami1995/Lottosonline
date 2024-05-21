import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.Select;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

public class Opennewaccount {
    private static WebDriver driver;

    public static void main(String[] args) {
        setDriver();
        openAccount();
        Package.showSuccessPopup("New Acccount created succesfully");
    }

    public static void setDriver() 
    {
        System.setProperty("webdriver.chrome.driver", "E:\\Lottos Java\\lib\\driver\\chromedriver-win64\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://lottosonline.thefamcomlab.com");
        driver.manage().window().maximize();
        Reference.sleep(5); 
    }

    public static void openAccount() {
        waitForClickable(By.xpath("//a[text()='Open Account']"), 10).click();
    
        // Generate unique values
        String uniqueFirstName = generateUniqueName("Pradeep");
        String uniqueLastName = generateUniqueName("TestUser");
        String uniqueEmail = generateUniqueEmail();
    
        // Use the unique values to fill in the form
        waitForClickable(By.id("firstname"), 2).sendKeys(uniqueFirstName);
        waitForClickable(By.id("lastname"), 2).sendKeys(uniqueLastName);
        waitForClickable(By.id("email"), 2).sendKeys(uniqueEmail);
        waitForClickable(By.id("email_confirm"), 2).sendKeys(uniqueEmail);
    
        waitForClickable(By.id("password"), 2).sendKeys("famcom");
        waitForClickable(By.id("contact_phone"), 2).sendKeys("9599899386");
        
        // Select country
        selectCountry("India");
    
        waitForClickable(By.id("notifications_notification_email"), 1).click();
        waitForClickable(By.id("submit_button"), 0).click();
        Reference.sleep(5);
    }
    
    public static void selectCountry(String countryName) {
        WebElement countryDropdown = waitForClickable(By.id("country"), 10);
        Select select = new Select(countryDropdown);
        select.selectByVisibleText(countryName);
    }
    
    public static WebElement waitForClickable(By locator, int timeoutSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    // Method to generate a unique name
    public static String generateUniqueName(String baseName) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        return baseName + dateFormat.format(new Date());
    }

    // Method to generate a unique email
    public static String generateUniqueEmail() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        return "test" + dateFormat.format(new Date()) + "@yopmail.com";
    }

    // Simple sleep method (you can replace this with any utility method you have)
    public static void sleep(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e)

         {
            e.printStackTrace();
        }
    }
}
