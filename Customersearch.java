import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import java.util.concurrent.TimeUnit;

class SeleniumExample {
    public static void main(String[] args) {
        // Set the path to the chromedriver executable
        System.setProperty("webdriver.chrome.driver", "E:\\JavaP3\\lib\\driver\\chromedriver-win64\\chromedriver.exe");

        // Create a new instance of the ChromeDriver
        WebDriver driver = new ChromeDriver();

        // Open the Chrome browser
        driver.get("https://lottosonline.thefamcomlab.com");
        driver.manage().window().maximize();

        // Delay the program execution for 5 seconds (5000 milliseconds)
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // Click on the Login button
        driver.findElement(By.linkText("Login")).click();

        // Fill in the login form
        driver.findElement(By.id("email")).sendKeys("pradeepadmin@yopmail.com");
        driver.findElement(By.id("password")).sendKeys("famcom");
        driver.findElement(By.id("submit_button")).click();

        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Click on the "Customer List" link
        driver.findElement(By.xpath("//a[contains(@href,'customer_search')]")).click();

        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Click on the "Search Panel" button
        driver.findElement(By.xpath("//*[@id='dyntable']/thead/tr/th[3]/span[2]/i")).click();

        // Searching by first name
        driver.findElement(By.id("tagsinputcol1_tag_input")).sendKeys("Hunny");
        driver.findElement(By.id("tagsinputcol1_tag_input")).sendKeys(Keys.RETURN);

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Removing the search data
        driver.findElement(By.xpath("//*[@id='tagsinputcol1_tag_list']/li/a")).click();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Searching by user id
        driver.findElement(By.xpath("//*[@id='dyntable']/thead/tr/th[2]/span[2]/i")).click();

        driver.findElement(By.id("tagsinputcol0_tag_input")).sendKeys("134970");
        driver.findElement(By.id("tagsinputcol0_tag_input")).sendKeys(Keys.RETURN);

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Removing the search data from user id
        driver.findElement(By.xpath("//*[@id='tagsinputcol0_tag_list']/li/a")).click();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Searching by last name
        driver.findElement(By.xpath("//*[@id='dyntable']/thead/tr/th[4]/span[2]/i")).click();

        driver.findElement(By.id("tagsinputcol10_tag_input")).sendKeys("goswami");
        driver.findElement(By.id("tagsinputcol10_tag_input")).sendKeys(Keys.RETURN);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Removing the search data from last name
        driver.findElement(By.xpath("//*[@id='tagsinputcol10_tag_list']/li/a")).click();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Close the browser
        // driver.quit();

    }
}