// import org.openqa.selenium.WebDriver;             // Main interface to instantiate and manage browser instances
// import org.openqa.selenium.chrome.ChromeDriver;   // Specific WebDriver implementation for Google Chrome
// //import org.openqa.selenium.support.ui.ExpectedConditions;
// //import org.openqa.selenium.support.ui.Select;
// //import org.openqa.selenium.support.ui.WebDriverWait;
// //import org.openqa.selenium.support.ui.Select;
// import org.apache.commons.io.FileUtils;           // Utility class for IO operations (e.g., file copying)
// import org.openqa.selenium.By;                    // Class used to locate elements on the web page
// import org.openqa.selenium.OutputType;            // Enum defining the type of output for certain operations (like screenshot)
// import org.openqa.selenium.TakesScreenshot;       // Interface allowing WebDriver to capture screenshots
// import javax.swing.JOptionPane;                  // Class used for showing standard dialogs (like pop-up messages)
// import java.util.concurrent.TimeUnit;             // Enum defining time units (e.g., seconds, minutes)
// import java.io.File;                              // Class representing file and directory path names
// import java.io.IOException;                       // Exception thrown when IO operations fail or are interrupted
// import java.text.SimpleDateFormat;                 // Class used to format dates into text and parse text into dates
// import java.util.Date;                            // Class representing a specific instant in time


// public class Customerlist {
//     private static WebDriver driver;

//     public static void main(String[] args) {
//         try {
//             setupDriver();
//             Reference.Adminlogin();

//         } catch (Exception e) {
//             Reference.handleError(e);
//         } finally {
//             if (driver != null) {
//                 driver.quit();
//             }
//         }
//     }

//     // public static void handleError(Exception e) {
//     //     System.err.println("An error occurred: " + e.getMessage());
//     //     takeScreenshot();
//     //     JOptionPane.showMessageDialog(null, "An error occurred: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
//     // }
//     public static void showSuccessPopup(String message) {
//         JOptionPane.showMessageDialog(null, message, "Success", JOptionPane.INFORMATION_MESSAGE);
//     }
    

//     //... [Rest of your code remains unchanged]
       

//     public static void setupDriver() {
//         System.setProperty("webdriver.chrome.driver", "E:\\JavaP3\\lib\\driver\\chromedriver-win64\\chromedriver.exe");
//         driver = new ChromeDriver();
//         driver.get("https://lottosonline.thefamcomlab.com");
//         driver.manage().window().maximize();
//     }

//     public static void Navi_Customerlist()
//     {
//         driver.findElement(By.cssSelector("")).click();
//     }

//     public static void Createcustomerlist()
//     {
//         driver.findElement(By.cssSelector("")).click();
//        // driver.findElement(By.id("dsfdfds")).sendKeys(null"fddfd");
//         driver.findElement(By.cssSelector("")).click();
//     }


// }

