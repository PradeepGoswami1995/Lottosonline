import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Archivecalllist 
{
    private static WebDriver driver;
   
    public static void main(String[] args) 
    {
        driversetup();  
        adminlogin();
        sorting(); 
        
    }
    public static void driversetup(){
        System.setProperty("webdriver.chrome.driver",  "E:\\Lottos Java\\lib\\driver\\chromedriver-win64\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://lottosonline.thefamcomlab.com");
        driver.manage().window().maximize();
    }
    public static void adminlogin() 

    {
        clickAndWait(By.linkText("Login"), 3);
        driver.findElement(By.id("email")).sendKeys("pradeepadmin@yopmail.com");
        driver.findElement(By.id("password")).sendKeys("famcom");
        driver.findElement(By.id("submit_button")).click();
        Reference.sleep(5);
    } 
    
    public static void navigation()
    {
       clickAndWait(By.xpath("//*[@id=\"menu\"]/li[5]/a"), 3); 
       clickAndWait(By.xpath("//a[text()='Archived Call Lists']"),2);

    }

    public static void sorting()
    {
        clickAndWait(By.xpath("//*[@id=\"dyntable\"]/thead/tr/th[2]/span[1]"), 2);
        clickAndWait(By.xpath("//*[@id=\"dyntable\"]/thead/tr/th[3]/span[1]"), 2);
        clickAndWait(By.xpath("//*[@id=\"dyntable\"]/thead/tr/th[4]/span[1]"), 2);
        clickAndWait(By.xpath("//*[@id=\"dyntable\"]/thead/tr/th[5]/span[1]"), 2);
        clickAndWait(By.xpath("//*[@id=\"dyntable\"]/thead/tr/th[6]/span[1]"), 2);
        clickAndWait(By.xpath("//*[@id=\"dyntable\"]/thead/tr/th[7]/span[1]"), 2);
        clickAndWait(By.xpath("//*[@id=\"dyntable\"]/thead/tr/th[8]/span[1]"), 2);
        Package.showSuccessPopup("Test ase 'List sorting'completed successfully ");
    }
    public static void clickAndWait(By locator, int seconds) 
    {
        driver.findElement(locator).click();
        Reference.sleep(seconds);
    }
    
}
