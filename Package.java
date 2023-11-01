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
import java.util.Date;                            // Class representing a specific instant in time


public class Package {
    private static WebDriver driver;

    public static void main(String[] args) {
        try {
            setupDriver();
            adminlogin();
            navigatingpackage();
            createPackage();
            createpromopacage();
            editPackage();
            sorting();
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
        takeScreenshot();
        JOptionPane.showMessageDialog(null, "An error occurred: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
    public static void showSuccessPopup(String message) {
        JOptionPane.showMessageDialog(null, message, "Success", JOptionPane.INFORMATION_MESSAGE);
    }
    

    //... [Rest of your code remains unchanged]
       

    public static void setupDriver() {
        System.setProperty("webdriver.chrome.driver", "E:\\JavaP3\\lib\\driver\\chromedriver-win64\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://lottosonline.thefamcomlab.com");
        driver.manage().window().maximize();
    }

    public static WebDriver getDriver() {
        return driver;
    }

    public static void adminlogin() {
        clickAndWait(By.linkText("Login"), 3);
        
        driver.findElement(By.id("email")).sendKeys("gopher@famcominc.com");
        driver.findElement(By.id("password")).sendKeys("FamComInc");
        driver.findElement(By.id("submit_button")).click();

        sleep(5);
    }
    public static void navigatingpackage()
    {
        driver.findElement(By.xpath("//*[@id=\"menu\"]/li[3]/a")).click();

        sleep(5);

    }

    public static void createPackage() {
       // driver.findElement(By.xpath("/html/body/div[2]/div[2]/div[1]/div/a")).click();
        driver.findElement(By.partialLinkText("Create Package")).click();
       // driver.findElement(By.cssSelector("a.btn.btn-info[href='/admin/create_admin_packages.php']")).click();



        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        String uniquePackageName = "Package manager V" + dateFormat.format(new Date());

        driver.findElement(By.id("package_name")).sendKeys(uniquePackageName);
        driver.findElement(By.id("package_description")).sendKeys("Testing by Automation, Game + Jackpot pool");

        driver.findElement(By.id("package_type")).click();
        driver.findElement(By.xpath("//*[@id=\"package_type\"]/option[3]")).click();
        driver.findElement(By.id("package_price")).sendKeys("30");
        

        driver.findElement(By.cssSelector("#gametbl > tbody > tr:nth-child(11) > td:nth-child(1) > input[type=checkbox]")).click();
    driver.findElement(By.cssSelector("#gametbl > tbody > tr.lucky_diprow > td:nth-child(1) > input[type=checkbox]")).click();

        // driver.findElement(By.xpath("/html/body/div[2]/div[4]/div[2]/div/div/div/div/div/div/form/div[12]/div/div/table/tbody/tr[2]/td[1]/div/span/input")).click();
        // driver.findElement(By.xpath("/html/body/div[2]/div[4]/div[2]/div/div/div/div/div/div/form/div[12]/div/div/table/tbody/tr[20]/td[1]/div")).click();
        // driver.findElement(By.xpath("/html/body/div[2]/div[4]/div[2]/div/div/div/div/div/div/form/div[12]/div/div/table/tbody/tr[34]/td[1]/div/span/input")).click();
        driver.findElement(By.id("savepkg")).click();


        sleep(3);

        takeScreenshot();
        showSuccessPopup("Package created successfully!");
    }

    public static void editPackage() {
        //driver.findElement(By.xpath("//*[@id=\"dyntable\"]/tbody/tr[1]/td[11]/a/i")).click();
        //driver.findElement(By.cssSelector("a[href='/admin/create_admin_packages.php?uid=130']")).click();
        driver.findElement(By.cssSelector("a[href^='/admin/create_admin_packages.php?uid=']:first-of-type")).click();
        sleep(5);

        driver.findElement(By.id("savepkg")).click();
        sleep(2);
        System.out.println("Edit successfully");
        showSuccessPopup("Record Edited successfully!");
        takeScreenshot();
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

    public static void sorting()
    {
       //Package Name 
     driver.findElement(By.xpath("//*[@id=\"dyntable\"]/thead/tr/th[2]/span")).click();
     sleep(5);

     takeScreenshot();
     // Price 
     driver.findElement(By.xpath("//*[@id=\"dyntable\"]/thead/tr/th[4]/span")).click();
     sleep(5);
     takeScreenshot();

     //Agent Name
     driver.findElement(By.xpath("//*[@id=\"dyntable\"]/thead/tr/th[7]/span")).click();
     sleep(5);
     takeScreenshot();

     // AGent Location 
     driver.findElement(By.xpath("//*[@id=\"dyntable\"]/thead/tr/th[8]/span")).click();
     sleep(5);

     takeScreenshot();
     // Status
     driver.findElement(By.xpath("//*[@id=\"dyntable\"]/thead/tr/th[9]/span")).click();
     sleep(5);
      takeScreenshot();

// Create Date
     driver.findElement(By.xpath("//*[@id=\"dyntable\"]/thead/tr/th[10]/span")).click();

     sleep(5);
      takeScreenshot();
      showSuccessPopup("All columns have been sorted successfully!");


    }

    public static void createpromopacage()
    {
         // driver.findElement(By.xpath("/html/body/div[2]/div[2]/div[1]/div/a")).click();
        driver.findElement(By.partialLinkText("Create Package")).click();
       // driver.findElement(By.cssSelector("a.btn.btn-info[href='/admin/create_admin_packages.php']")).click();

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        String uniquePackageName = "Package manager V" + dateFormat.format(new Date());

        driver.findElement(By.id("package_name")).sendKeys(uniquePackageName);
        driver.findElement(By.id("package_description")).sendKeys("Testing by Automation, Game + Jackpot pool");

        //Selecting the DDL 
        driver.findElement(By.id("promo_package")).click(); // This opens the dropdown
        driver.findElement(By.xpath("//*[@id=\"promo_package\"]/option[2]")).click(); // This selects the second option
        sleep(1);

        driver.findElement(By.id("promo_package_price")).sendKeys("50");

         // Date setup
         SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
         String currentDate = sdf.format(new Date());
         System.out.println("Current Date: " + currentDate);  // Print the date for verification
 
         // Click the dropdown
         driver.findElement(By.id("fromdate")).click();
         sleep(5);
         
         //driver.findElement(By.xpath("//*[@id=\"fromdate\"]/option[@value='" + currentDate + "']")).click();
         driver.findElement(By.id("fromdate")).sendKeys("currentDate");
         //driver.findElement(By.linkText("26")).click();


         driver.findElement(By.cssSelector("tr:nth-child(11) #uniform-undefined input")).click();
    driver.findElement(By.cssSelector(".lucky_diprow #uniform-undefined input")).click();

         
         // Continue with the rest of your code
         driver.findElement(By.id("savepkg")).click();
         showSuccessPopup("Promo package created successfully!");
         takeScreenshot();
    }

        

    }


