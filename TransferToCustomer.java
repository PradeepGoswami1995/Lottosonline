
// This Script using to test the transfer to customer and purchase ticket by customer

import java.util.concurrent.TimeUnit;

import javax.swing.JOptionPane;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
//import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class TransferToCustomer{
    private static WebDriver driver;

    public static void main(String[] args) {

        try {
        
        Setdriver();
        openLottosSite();
        Adminlogin();
        AdminTransferCustomer();

    } catch (Exception e) {
        Package.handleError(e);
    } finally {
        if (driver != null) 
        {
            driver.quit();
        
        }
    }
    
    }
    public static void Setdriver()
    {
        System.setProperty("webdriver.chrome.driver", "E:\\Lottos Java\\lib\\driver\\chromedriver-win64\\chromedriver.exe");
        driver = new ChromeDriver();

    }
    public static void openLottosSite() {
        driver.get("https://lottosonline.thefamcomlab.com");
        driver.manage().window().maximize();
    }

     public static void Adminlogin() {
        driver.findElement(By.linkText("Login")).click();
        // Assuming you are on the login page again after opening the site
        driver.findElement(By.id("email")).sendKeys("pradeepadmin@yopmail.com");
        driver.findElement(By.id("password")).sendKeys("famcom");
        driver.findElement(By.id("submit_button")).click();
       sleep(2);
    }


    public static void AdminTransferCustomer()
     {
        // Click on the "Customer List" link
        driver.findElement(By.xpath("//a[contains(@href,'customer_search')]")).click();
    sleep(2);
        // Click on the "Search Panel" button
        driver.findElement(By.xpath("//*[@id=\"dyntable\"]/thead/tr/th[3]/span[2]/i")).click();
        sleep(2);
    
        // Searching by first name
        driver.findElement(By.id("tagsinputcol1_tag_input")).sendKeys("Hunny");
        driver.findElement(By.id("tagsinputcol1_tag_input")).sendKeys(Keys.RETURN);
    sleep(2);
        // CLICK ON THE USER PROFILE 
        driver.findElement(By.cssSelector("#dyntable > tbody > tr > td:nth-child(3) > a")).click();
        sleep(5);

        // Click on "New Order"
        driver.findElement(By.cssSelector("body > div.mainwrapper > div.rightpanel > div.user-left > ul > li:nth-child(1) > a")).click();
        sleep(2);

        WebElement iframe = driver.findElement(By.id("iframe"));
        driver.switchTo().frame(iframe);
    
        // select a ticket
       driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/table/tbody/tr[1]/td[4]/form/button")).click();
        
        sleep(2);

        //select ticket numbers
       //driver.findElement(By.id("1")).click();
       driver.findElement(By.xpath("//*[@id='play_form']/div/div[2]/div[1]/div[1]/a")).click();
       driver.findElement(By.xpath("//*[@id='play_form']/div/div[2]/div[1]/div[3]/a")).click();
       driver.findElement(By.xpath("//*[@id=\"play_form\"]/div/div[2]/div[1]/div[4]/a")).click();
       driver.findElement(By.xpath("//*[@id=\"play_form\"]/div/div[2]/div[1]/div[5]/a")).click();
       driver.findElement(By.xpath("//*[@id=\"play_form\"]/div/div[2]/div[1]/div[6]/a")).click();
         sleep(5);
    
        // Click on "Continue to Checkout"
        driver.findElement(By.xpath("//button[contains(text(), 'Continue to Checkout')]")).click();
         sleep(2);
    
        // Continue with the remaining steps...
        // Transfer to customer
        //driver.findElement(By.tagName("Transfer to Customer")).click();
        driver.findElement(By.xpath("//*[@id='card_details_container']/div[2]/a[2]")).click();
        sleep(2);

        // logout
        driver.get("https://lottosonline.thefamcomlab.com/logout");
        //driver.findElement(By.xpath("//a[contains(text(),'Logout')]")).click();
               
        sleep(2);

        Message("Item transftered to customer");

        //purchase by customer
        
            driver.findElement(By.linkText("Login")).click();
            // Assuming you are on the login page again after opening the site
            driver.findElement(By.id("email")).sendKeys("hunny@yopmail.com");
            driver.findElement(By.id("password")).sendKeys("famcom");
            driver.findElement(By.id("submit_button")).click();
            sleep(2);

            // Navigate to the cart page
           // driver.findElement(By.tagName("My Cart (1)")).click();
           driver.findElement(By.xpath("//*[@id='logo-header-block']/div[2]/div/p")).click();

            sleep(2);   

            // place order
            //driver.findElement(By.tagName("Place my order")).click();
            driver.findElement(By.xpath("//*[@id='simple_account_payment_form']/div/button")).click();
            sleep(5);   
            
            // Logout
        driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/ul[1]/li/a")).click();
       // driver.findElement(By.tagName("Logout")).click();
        sleep(5);

        Message("Order Placed successfully by customer");

    }

    public static void sleep(int seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    public static void Message(String Message){
        JOptionPane.showMessageDialog(null, driver, Message , 0);
    }

    
}