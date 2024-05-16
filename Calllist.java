
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.openqa.selenium.By;

public class Calllist 
{
    private static WebDriver driver;

    public static void main(String[] args)
     {
        Setdriver();
        adminlogin();
        Navigatecalllist();
       // creatcalllist();
        //sorting();
        //delete();
        //creatcalllist();
        //assigncalllist(); 
        archivecallist();
        //searchpanel();    
    }
    
    public static void Setdriver()
    {
         System.setProperty("webdriver.chrome.driver", "E:\\Lottos Java\\lib\\driver\\chromedriver-win64\\chromedriver.exe");
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

    public static void clickAndWait(By locator, int seconds) 
    {
        driver.findElement(locator).click();
        Reference.sleep(seconds);
    }

    public static void Navigatecalllist()
    {
        //driver.findElement(By.tagName(" Create Call  Lists ")).click();
        clickAndWait(By.xpath("//*[@id=\"menu\"]/li[5]/a"), 3);     

    }

    public static void creatcalllist() 
    {
        driver.findElement(By.id("addRecord")).click();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        String uniquePackageName = "Call List V" + dateFormat.format(new Date());
        driver.findElement(By.id("call_list_name")).sendKeys(uniquePackageName);
        //clickAndWait(By.xpath("//*[@id=\\\"cid\\\"]/option[31]"), 3);
       driver.findElement(By.xpath("//*[@id=\"cid\"]/option[31]")).click();
        driver.findElement(By.id("max_records")).sendKeys("2");
        // Click and wait for the save button
        clickAndWait(By.id("save"), 3);
    }   

    public static void assigncalllist()
    {       
        driver.findElement(By.xpath("//button[@name='assign_user']")).click();
        WebElement dropdown = driver.findElement(By.id("sales_user"));
        // Create a Select object from the dropdown WebElement
        Select select = new Select(dropdown);
        // Select the option with the value "134948"
           select.selectByValue("134948");
           clickAndWait(By.id("saveAssign"), 3);
          // driver.findElement(By.id("saveAssign")).click();
          
    }

    public static void archivecallist()
    {
        clickAndWait(By.xpath("//button[contains(text(), 'Archive')]"), 2);
    }

    public static void delete()
    {
        driver.findElement(By.name("delete")).click();
        Reference.sleep(10);
        //here is pending to click on the Ok button for confirmation to delete
    }

    public static void sorting()
    {

        // driver.findElement(By.xpath("//*[@id='dyntable']/thead/tr/th[2]/span[1]")).click();
        // driver.findElement(By.xpath("//*[@id=\"dyntable\"]/thead/tr/th[3]/span[1]")).click();
        // driver.findElement(By.xpath("//*[@id=\"dyntable\"]/thead/tr/th[4]/span[1]")).click();
        // driver.findElement(By.xpath("//*[@id=\"dyntable\"]/thead/tr/th[5]/span[1]")).click();
        // driver.findElement(By.xpath("//*[@id=\"dyntable\"]/thead/tr/th[6]/span[1]")).click();
        // driver.findElement(By.xpath("//*[@id=\"dyntable\"]/thead/tr/th[7]/span[1]")).click();
        // driver.findElement(By.xpath("//*[@id=\"dyntable\"]/thead/tr/th[8]/span[1]")).click();
        clickAndWait(By.xpath("//*[@id='dyntable']/thead/tr/th[2]/span[1]"), 3);
        clickAndWait(By.xpath("//*[@id='dyntable']/thead/tr/th[3]/span[1]"), 3);
        clickAndWait(By.xpath("//*[@id='dyntable']/thead/tr/th[4]/span[1]"), 3);
        clickAndWait(By.xpath("//*[@id='dyntable']/thead/tr/th[5]/span[1]"), 3);
        clickAndWait(By.xpath("//*[@id='dyntable']/thead/tr/th[6]/span[1]"), 3);
        clickAndWait(By.xpath("//*[@id='dyntable']/thead/tr/th[7]/span[1]"), 3);
        clickAndWait(By.xpath("//*[@id='dyntable']/thead/tr/th[8]/span[1]"), 3);
    }

    public static void searchpanel()
    {   
        driver.findElement(By.xpath("//*[@id=\"dyntable\"]/thead/tr/th[2]/span[2]/i")).click();
        driver.findElement(By.id("tagsinputcol0_tag_input")).sendKeys("85877");

        //driver.findElement(By.)

        driver.findElement(By.xpath("//*[@id='dyntable']/thead/tr/th[3]/span[2]/i")).click();
        driver.findElement(By.id("tagsinputcol1_tag_input")).sendKeys("A");

        driver.findElement(By.xpath("//*[@id='dyntable']/thead/tr/th[5]/span[2]/i")).click();
        driver.findElement(By.id("tagsinputcol56_tag_input")).sendKeys("05/10/2024 - 05/10/2024");        
        driver.findElement(By.xpath("null")).click();       

    }
}
