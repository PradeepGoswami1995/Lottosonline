import org.openqa.selenium.WebDriver;             // Main interface to instantiate and manage browser instances
import org.openqa.selenium.chrome.ChromeDriver;   // Specific WebDriver implementation for Google Chrome
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.Select;
//import org.openqa.selenium.support.ui.WebDriverWait;
//import org.openqa.selenium.support.ui.Select;
import org.apache.commons.io.FileUtils;           // Utility class for IO operations (e.g., file copying)
import org.openqa.selenium.By;                    // Class used to locate elements on the web page
import org.openqa.selenium.OutputType;            // Enum defining the type of output for certain operations (like screenshot)
import org.openqa.selenium.TakesScreenshot;       // Interface allowing WebDriver to capture screenshots
import javax.swing.JOptionPane;                  // Class used for showing standard dialogs (like pop-up messages)
import java.util.concurrent.TimeUnit;             // Enum defining time units (e.g., seconds, minutes)
import java.io.File;                              // Class representing file and directory path names
import java.io.IOException;                       // Exception thrown when IO operations fail or are interrupted
import java.text.SimpleDateFormat;                 // Class used to format dates into text and parse text into dates
import java.util.Date;                           // Class representing a specific instant in time                      


public class Reference {
    private static WebDriver driver;

    public static void main(String[] args) {
        setupDriver();
        Adminlogin();
        //setupDriver();
        Syndicatelogin();

    }

    public static void handleError(Exception e) {
        System.err.println("An error occurred: " + e.getMessage());
        takeScreenshot();
        JOptionPane.showMessageDialog(null, "An error occurred: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
    public static void showSuccessPopup(String message) {
        JOptionPane.showMessageDialog(null, message, "Success", JOptionPane.INFORMATION_MESSAGE);
    }
       
    public static void setupDriver() {
        System.setProperty("webdriver.chrome.driver", "E:\\JavaP3\\lib\\driver\\chromedriver-win64\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://lottosonline.thefamcomlab.com");
        driver.manage().window().maximize();
    }

    public static WebDriver getDriver() {
        return driver;
    }

    public static void Syndicatelogin() {
        clickAndWait(By.linkText("Login"), 3);
        
        driver.findElement(By.id("email")).sendKeys("gopher@famcominc.com");
        driver.findElement(By.id("password")).sendKeys("FamComInc");
        driver.findElement(By.id("submit_button")).click();

        sleep(5);
    }

     public static void Adminlogin() {
        clickAndWait(By.linkText("Login"), 1);
        
        driver.findElement(By.id("email")).sendKeys("pradeepadmin@yopmail.com");
        driver.findElement(By.id("password")).sendKeys("famcom");
        driver.findElement(By.id("submit_button")).click();

        sleep(5);
    }

     public static void Saleslogin() {
        clickAndWait(By.linkText("Login"), 3);
        
        driver.findElement(By.id("email")).sendKeys("pradeepadmin@yopmail.com");
        driver.findElement(By.id("password")).sendKeys("famcom");
        driver.findElement(By.id("submit_button")).click();

        sleep(2);     
    }



    public static void takeScreenshot() {
        try {
            TakesScreenshot ts = (TakesScreenshot) driver;
            File screenshot = ts.getScreenshotAs(OutputType.FILE);
    
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
            String timestamp = dateFormat.format(new Date());
            String fileName = "Package manager" + timestamp + ".png";
    
            File destination = new File("D:\\Images\\" + fileName);
            if(!destination.getParentFile().exists()){
                destination.getParentFile().mkdirs();  // Ensure the directory exists
            }
    
            FileUtils.copyFile(screenshot, destination);
            System.out.println("Screenshot saved: " + destination.getAbsolutePath());
        } catch (IOException e) {
            System.err.println("Error saving the screenshot: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("Error taking the screenshot: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void clickAndWait(By locator, int seconds) {
        driver.findElement(locator).click();
        sleep(seconds);
    }

    public static void sleep(int seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

     public static void PMlogin() {
        driver.findElement(By.linkText("Login")).click();
        //clickAndWait(By.linkText("Login"), 3);
        
        driver.findElement(By.id("email")).sendKeys("iampradeepgoswami@gmail.com");
        driver.findElement(By.id("password")).sendKeys("famcom");
        driver.findElement(By.id("submit_button")).click();
        sleep(2);     
    }

    public static void Massage(String Massage ){ 
        JOptionPane.showMessageDialog(null, Massage, "Success", JOptionPane.INFORMATION_MESSAGE);
    }
}

    

        

    


