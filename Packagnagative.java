
import org.openqa.selenium.WebDriver;            
//import org.openqa.selenium.chrome.ChromeDriver;   
import org.openqa.selenium.By;                    
                          

public class Packagnagative {
    private static WebDriver driver;

    public static void main(String[] args) {
        try {
            Package.setupDriver();  // This initializes the driver.
            driver = Package.getDriver(); // Now you can get the driver.
            
            Package.adminlogin();
            Package.navigatingpackage();
            blanksave();
            blankupdate();
        } catch (Exception e) {
            Package.handleError(e);
        } finally {
            if (driver != null) {
                driver.quit();
            }
        }
    }

    // public static void setupDriver() {
    //    System.setProperty("webdriver.chrome.driver", "E:\\JavaP3\\lib\\driver\\chromedriver-win64\\chromedriver.exe");
    //     driver = new ChromeDriver();
    //     driver.get("https://lottosonline.thefamcomlab.com");
    //     driver.manage().window().maximize();
    //  }
    public static void blanksave()
    {
        driver.findElement(By.partialLinkText("Create Package")).click();
        driver.findElement(By.id("savepkg")).click();
        Package.takeScreenshot();

    }
    public static void blankupdate()
    {
        driver.findElement(By.cssSelector("a[href^='/admin/create_admin_packages.php?uid=']:first-of-type")).click();
        Package.sleep(5);
        driver.findElement(By.id("package_name")).sendKeys("");
        driver.findElement(By.id("package_description")).sendKeys("");
        driver.findElement(By.id("savepkg")).click();
        Package.sleep(2);
        Package.showSuccessPopup("Record Edited successfully!");
        Package.takeScreenshot();
    }


}