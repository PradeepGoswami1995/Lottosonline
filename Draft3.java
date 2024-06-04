import java.time.Duration;
//import java.util.concurrent.TimeUnit;

import javax.swing.JOptionPane;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Draft3 {
    private static WebDriver driver;
    private static WebDriverWait wait;

    @BeforeClass
    public void Setdriver() {
        System.setProperty("webdriver.chrome.driver", "E:\\Lottos Java\\lib\\driver\\chromedriver-win64\\chromedriver.exe");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test
    public void test() {
        try {
            openLottosSite();
            Adminlogin();
            AdminTransferCustomer();
            Customerlogin();
            purchasebycustomer();
        } catch (Exception e) {
            handleError(e);
        } finally {
            if (driver != null) {
                driver.quit();
            }
        }
    }

    public void openLottosSite() {
        driver.get("https://lottosonline.thefamcomlab.com");
        driver.manage().window().maximize();
    }

    public void Adminlogin() {
        driver.findElement(By.linkText("Login")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email"))).sendKeys("pradeepadmin@yopmail.com");
        driver.findElement(By.id("password")).sendKeys("famcom");
        driver.findElement(By.id("submit_button")).click();
    }

    public void AdminTransferCustomer() {
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(@href,'customer_search')]"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"dyntable\"]/thead/tr/th[3]/span[2]/i"))).click();
        WebElement searchInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("tagsinputcol1_tag_input")));
        searchInput.sendKeys("Hunny");
        Reference.sleep(1);
        searchInput.sendKeys(Keys.RETURN);
        Reference.sleep(1);
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#dyntable > tbody > tr > td:nth-child(3) > a"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("body > div.mainwrapper > div.rightpanel > div.user-left > ul > li:nth-child(1) > a"))).click();

        WebElement iframe = driver.findElement(By.id("iframe"));
        driver.switchTo().frame(iframe);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[2]/div/div/div[2]/table/tbody/tr[1]/td[4]/form/button"))).click();

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='play_form']/div/div[2]/div[1]/div[1]/a"))).click();
        driver.findElement(By.xpath("//*[@id='play_form']/div/div[2]/div[1]/div[3]/a")).click();
        driver.findElement(By.xpath("//*[@id=\"play_form\"]/div/div[2]/div[1]/div[4]/a")).click();
        driver.findElement(By.xpath("//*[@id=\"play_form\"]/div/div[2]/div[1]/div[5]/a")).click();
        driver.findElement(By.xpath("//*[@id=\"play_form\"]/div/div[2]/div[1]/div[6]/a")).click();

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(), 'Continue to Checkout')]"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='card_details_container']/div[2]/a[2]"))).click();

        driver.get("https://lottosonline.thefamcomlab.com/logout");

       // Message("Item transferred to customer");
    }

    public void Customerlogin() {
        driver.findElement(By.linkText("Login")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email"))).sendKeys("hunny@yopmail.com");
        driver.findElement(By.id("password")).sendKeys("famcom");
        driver.findElement(By.id("submit_button")).click();
    }

    public void purchasebycustomer() {
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='logo-header-block']/div[2]/div/p"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='simple_account_payment_form']/div/button"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[1]/div[2]/div/ul[1]/li/a"))).click();
    }

    public void handleError(Exception e) {
        e.printStackTrace();
    }

    public void Message(String message) {
        JOptionPane.showMessageDialog(null, driver, message, 0);
    }
}
