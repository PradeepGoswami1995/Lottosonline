
import org.openqa.selenium.chrome.ChromeDriver;
import javax.swing.JOptionPane;
//import org.openqa.selenium.takeScreenshot;
import org.openqa.selenium.By;
//import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver; 

public class Practice {
   private static WebDriver driver;

  public static void main(String[] args) 
  {
   SetDriver();
   Login();
  }

  
  public static void SetDriver()
  {
   System.setProperty("webdriver.chrome.driver","E:\\Lottos Java\\lib\\driver\\chromedriver-win64\\chromedriver.exe");

   }

   public static void Login(){
      ChromeDriver driver = new  ChromeDriver();
      driver.get("https://lottosonline.thefamcomlab.com");
      driver.manage().window().maximize();
      Massage("Login sucessfull");
      
   }

   public static void Massage(String  message)
   {
      JOptionPane.showMessageDialog(null, message, "LOTTOS", 0);
   }

   public static void Screenshot()
   {
     // TakesScreenshot ts = (TakesScreenshot) driver;
      


   }

   public static void clickAndWait(By locator, int seconds) {
      driver.findElement(locator).click();
      driver.findElement(locator).click();
     // sleep(seconds);
  }


}