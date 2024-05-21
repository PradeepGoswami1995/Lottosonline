
//This Script using to test the Ticket selection and purchase by the customer

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.devtools.Message;
import org.openqa.selenium.support.ui.Select;
import javax.swing.JOptionPane;

public class Customerpurchase {

    private static WebDriver driver;

    public static void main(String[] args) {

        try {
        setDriver();
        openLottosSite();
        Customerlogin();
        Select();
    } catch (Exception e) {
        Package.handleError(e);
    } finally {
        if (driver != null) 
        {
            driver.quit();
        
        }
    }
    }

    public static void setDriver() 
    {
        System.setProperty("webdriver.chrome.driver", "E:\\Lottos Java\\lib\\driver\\chromedriver-win64\\chromedriver.exe");
        driver = new ChromeDriver();
    }

    public static void openLottosSite() {
        driver.get("https://lottosonline.thefamcomlab.com");
        driver.manage().window().maximize();
    }

    public static void Customerlogin() {
        driver.findElement(By.linkText("Login")).click();
        // Assuming you are on the login page again after opening the site
        driver.findElement(By.id("email")).sendKeys("hunny@yopmail.com");
        driver.findElement(By.id("password")).sendKeys("famcom");
        driver.findElement(By.id("submit_button")).click();
    }

    public static void Select() 
    {
        // 
        driver.findElement(By.cssSelector("#home > div.maincontent.relative.homepage > div > div.leftcontent > div.lotteries > table > tbody > tr:nth-child(1) > td:nth-child(4) > form > button")).click();
        
        // Corrected the selector to use CSS instead of ID
        new Select(driver.findElement(By.cssSelector("#choose-quickpick"))).selectByVisibleText("Quick Pick 5 tickets");
        
        driver.findElement(By.cssSelector("#play_form > div.full-order > div.order-summary > div.order-summary-right > button")).click();
        
        // Corrected the selector to use the button's text
        driver.findElement(By.xpath("//button[contains(text(), 'Place my order')]")).click();
    
        Massage("Customer Purchase successful");
        // Logout
        driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/ul[1]/li/a")).click();
    
    }
    

    public static void Massage(String Massage){
        JOptionPane.showMessageDialog(null, Massage, "Customer Purchase sucessfull", 0);
    }

    

}
