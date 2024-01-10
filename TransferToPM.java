
//import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.chrome.ChromeDriver;
//import java.util.concurrent.TimeUnit;

public class TransferToPM {

    private static WebDriver driver;

    public static void main(String[] args) {

        TransferToCustomer.Setdriver();
        TransferToCustomer.openLottosSite();
        TransferToCustomer.Adminlogin();
        TransferPM();
        
    }

    public static void openLottosSite() {
        driver.get("https://lottosonline.thefamcomlab.com");
        driver.manage().window().maximize();
    }

    public static void TransferPM(){

        // Click on the "Customer List" link
        driver.findElement(By.xpath("//a[contains(@href,'customer_search')]")).click();
    Reference.sleep(2);
        // Click on the "Search Panel" button
        driver.findElement(By.xpath("//*[@id=\"dyntable\"]/thead/tr/th[3]/span[2]/i")).click();
        Reference.sleep(2);
    
        // Searching by first name
        driver.findElement(By.id("tagsinputcol1_tag_input")).sendKeys("Hunny");
        driver.findElement(By.id("tagsinputcol1_tag_input")).sendKeys(Keys.RETURN);
    Reference.sleep(2);
        // CLICK ON THE USER PROFILE 
        driver.findElement(By.cssSelector("#dyntable > tbody > tr > td:nth-child(3) > a")).click();
        Reference.sleep(5);

        // Click on "New Order"
        driver.findElement(By.cssSelector("body > div.mainwrapper > div.rightpanel > div.user-left > ul > li:nth-child(1) > a")).click();
        Reference.sleep(2);

        WebElement iframe = driver.findElement(By.id("iframe"));
        driver.switchTo().frame(iframe);
    
        // select a ticket
       driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/table/tbody/tr[1]/td[4]/form/button")).click();
        
        Reference.sleep(2);

        //select ticket numbers
       //driver.findElement(By.id("1")).click();
       driver.findElement(By.xpath("//*[@id='play_form']/div/div[2]/div[1]/div[1]/a")).click();
       driver.findElement(By.xpath("//*[@id='play_form']/div/div[2]/div[1]/div[3]/a")).click();
       driver.findElement(By.xpath("//*[@id=\"play_form\"]/div/div[2]/div[1]/div[4]/a")).click();
       driver.findElement(By.xpath("//*[@id=\"play_form\"]/div/div[2]/div[1]/div[5]/a")).click();
       driver.findElement(By.xpath("//*[@id=\"play_form\"]/div/div[2]/div[1]/div[6]/a")).click();
         Reference.sleep(5);
    
        // Click on "Continue to Checkout"
        driver.findElement(By.xpath("//button[contains(text(), 'Continue to Checkout')]")).click();
         Reference.sleep(2);

         driver.findElement(By.id("submit_button")).click();

         Reference.Massage("Transfered to PM successfully");

         Reference.sleep(5);
         Reference.PMlogin();


    }

}
    
