import org.openqa.selenium.WebDriver;             // Main interface to instantiate and manage browser instances
import org.openqa.selenium.chrome.ChromeDriver;   // Specific WebDriver implementation for Google Chrome
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.Select;
//import org.openqa.selenium.support.ui.WebDriverWait;
//import org.openqa.selenium.support.ui.Select;
//import org.apache.commons.io.FileUtils;           // Utility class for IO operations (e.g., file copying)
import org.openqa.selenium.By;                    // Class used to locate elements on the web page
//import org.openqa.selenium.OutputType;            // Enum defining the type of output for certain operations (like screenshot)
//import org.openqa.selenium.TakesScreenshot;       // Interface allowing WebDriver to capture screenshots
import javax.swing.JOptionPane;                  // Class used for showing standard dialogs (like pop-up messages)
//import java.util.concurrent.TimeUnit;             // Enum defining time units (e.g., seconds, minutes)
//import java.io.File;                                  // Class representing file and directory path names
//import java.io.IOException;                       // Exception thrown when IO operations fail or are interrupted
//import java.text.SimpleDateFormat;                 // Class used to format dates into text and parse text into dates
//import java.util.Date;    
//import org.openqa.selenium.sendKeys;

class SeleniumExample {
    private static WebDriver driver;
    public static void main(String[] args) 
    {
        try {
            setupDriver();
            navigatingcustomerlist();
            searching();
        } catch (Exception e) {
            handleError(e);
        } finally {
            if (driver != null) {
                driver.quit();
            }
        }
    }

    public static void handleError(Exception e) {
        System.err.println("An error occurred: " + e.getMessage());
        Reference.takeScreenshot();
        JOptionPane.showMessageDialog(null, "An error occurred: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
    public static void showSuccessPopup(String message) {
        JOptionPane.showMessageDialog(null, message, "Success", JOptionPane.INFORMATION_MESSAGE);
    }
    

    //... [Rest of your code remains unchanged]
       

    public static void setupDriver() {
        System.setProperty("webdriver.chrome.driver", "E:\\Lottos Java\\lib\\driver\\chromedriver-win64\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://lottosonline.thefamcomlab.com");
        driver.manage().window().maximize();
    }

    public static void navigatingcustomerlist()
    {
        // Click on the Login button
        driver.findElement(By.linkText("Login")).click();

        // Fill in the login form
        driver.findElement(By.id("email")).sendKeys("pradeepadmin@yopmail.com");
        driver.findElement(By.id("password")).sendKeys("famcom");
        driver.findElement(By.id("submit_button")).click();
          // Click on the "Customer List" link
        driver.findElement(By.xpath("//a[contains(@href,'customer_search')]")).click();

       
    }
    public static void searching()
    {
        // Click on the "Search Panel" button
        driver.findElement(By.xpath("//*[@id='dyntable']/thead/tr/th[3]/span[2]/i")).click();

        // Searching by first name
        driver.findElement(By.id("tagsinputcol1_tag_input")).sendKeys("Hunny");
       // driver.findElement(By.id("tagsinputcol1_tag_input")).sendKeys(Keys.RETURN);

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Removing the search data
        driver.findElement(By.xpath("//*[@id='tagsinputcol1_tag_list']/li/a")).click();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Searching by user id
        driver.findElement(By.xpath("//*[@id='dyntable']/thead/tr/th[2]/span[2]/i")).click();

        driver.findElement(By.id("tagsinputcol0_tag_input")).sendKeys("134970");
        //driver.findElement(By.id("tagsinputcol0_tag_input")).sendKeys(Keys.RETURN);

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Removing the search data from user id
        driver.findElement(By.xpath("//*[@id='tagsinputcol0_tag_list']/li/a")).click();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Searching by last name
        driver.findElement(By.xpath("//*[@id='dyntable']/thead/tr/th[4]/span[2]/i")).click();

        driver.findElement(By.id("tagsinputcol10_tag_input")).sendKeys("goswami");
        //driver.findElement(By.id("tagsinputcol10_tag_input")).sendKeys(Keys.RETURN);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Removing the search data from last name
        driver.findElement(By.xpath("//*[@id='tagsinputcol10_tag_list']/li/a")).click();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Close the browser
        // driver.quit();



    }      
    
}