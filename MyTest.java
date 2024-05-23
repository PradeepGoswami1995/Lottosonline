import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;

public class MyTest {
    private static WebDriver driver;

    @BeforeClass
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "E:\\Lottos Java\\lib\\driver\\chromedriver-win64\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://lottosonline.thefamcomlab.com");
        driver.manage().window().maximize();
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void testAdminLogin() {
        try {
            clickAndWait(By.linkText("Login"), 3);
            driver.findElement(By.id("email")).sendKeys("pradeepadmin@yopmail.com");
            driver.findElement(By.id("password")).sendKeys("famcom");
            driver.findElement(By.id("submit_button")).click();
            Reference.sleep(5);
        //     Assert.assertTrue(driver.findElement(By.id("submit_button")).isDisplayed(), "Login failed!");
        } catch (Exception e) 
        {
            handleError(e);
         Assert.fail("Exception during admin login: " + e.getMessage());
        }
    
    }

    @Test(dependsOnMethods = "testAdminLogin")
    public void testNavigateCallList() {
        try {
            clickAndWait(By.xpath("//*[@id=\"menu\"]/li[5]/a"), 3);  
        } catch (Exception e) {
            handleError(e);
            Assert.fail("Exception during navigate call list: " + e.getMessage());
        }
    }

    @Test(dependsOnMethods = "testNavigateCallList")
    public void testCreateCallList() {
        try {
            clickAndWait(By.id("addRecord"), 3);
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
            String uniqueCalllistName = "Call List V" + dateFormat.format(new Date());
            driver.findElement(By.id("call_list_name")).sendKeys(uniqueCalllistName);
            driver.findElement(By.xpath("//*[@id=\"cid\"]/option[31]")).click();
            driver.findElement(By.id("max_records")).sendKeys("2");
            clickAndWait(By.id("save"), 2);
            showSuccessPopup("Test Case 'Create Call List' completed successfully.");
        } catch (Exception e) {
            handleError(e);
            Assert.fail("Exception during create call list: " + e.getMessage());
        }
    }

    @Test(dependsOnMethods = "testCreateCallList")
    public void testDeleteCallList() {
        try {
            driver.findElement(By.name("delete")).click();
            Reference.sleep(2);
            try {
                Alert alert = driver.switchTo().alert();
                alert.accept();
            } catch (NoAlertPresentException e) {
                System.out.println("No alert present.");
            }
            showSuccessPopup("Test Case 'Delete call list' completed successfully.");
        } catch (Exception e) {
            handleError(e);
            Assert.fail("Exception during delete call list: " + e.getMessage());
        }
    }

    @Test(dependsOnMethods = "testDeleteCallList")
    public void testAssignCallList() {
        try {
            creatcalllist(); // Recreate call list to assign it
            driver.findElement(By.xpath("//button[@name='assign_user']")).click();
            WebElement dropdown = waitForVisibility(By.id("sales_user"), 10);
            Select select = new Select(dropdown);
            select.selectByValue("134948");
            clickAndWait(By.id("saveAssign"), 3);
            showSuccessPopup("Test Case 'Assign call list ' completed successfully.");
        } catch (Exception e) {
            handleError(e);
            Assert.fail("Exception during assign call list: " + e.getMessage());
        }
    }

    @Test(dependsOnMethods = "testAssignCallList")
    public void testArchiveCallList() {
        try {
            clickAndWait(By.xpath("//button[contains(text(), 'Archive')]"), 2);
            Reference.sleep(2);
            try {
                Alert alert = driver.switchTo().alert();
                alert.accept();
            } catch (NoAlertPresentException e) {
                System.out.println("No alert present.");
            }
            showSuccessPopup("Test Case 'Archive call list ' completed successfully.");
        } catch (Exception e) {
            handleError(e);
            Assert.fail("Exception during archive call list: " + e.getMessage());
        }
    }

    @Test(dependsOnMethods = "testArchiveCallList")
    public void testImportCallList() {
        try {
            clickAndWait(By.id("addRecord"), 3);
            clickAndWait(By.id("uploadcsv"), 3);
            String uniqueCalllistName = generateUniqueCalllistName();
            driver.findElement(By.id("uploadcsv_list_name")).sendKeys(uniqueCalllistName);
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement uploadElement = wait.until(ExpectedConditions.elementToBeClickable(By.id("csv_file")));
            uploadElement.sendKeys("C:\\Users\\Famcom\\Desktop\\blank.csv");
            clickAndWait(By.id("saveUploadcsv"), 3);
            showSuccessPopup("Test Case 'upload CSV file to create call list ' completed successfully.");
        } catch (Exception e) {
            handleError(e);
            Assert.fail("Exception during import call list: " + e.getMessage());
        }
    }

    @Test(dependsOnMethods = "testImportCallList")
    public void testSorting() {
        try {
            creatcalllist(); // Recreate call list for sorting
            clickAndWait(By.xpath("//*[@id='dyntable']/thead/tr/th[2]/span[1]"), 3);
            clickAndWait(By.xpath("//*[@id='dyntable']/thead/tr/th[3]/span[1]"), 3);
            clickAndWait(By.xpath("//*[@id='dyntable']/thead/tr/th[4]/span[1]"), 3);
            clickAndWait(By.xpath("//*[@id='dyntable']/thead/tr/th[5]/span[1]"), 3);
            clickAndWait(By.xpath("//*[@id='dyntable']/thead/tr/th[6]/span[1]"), 3);
            clickAndWait(By.xpath("//*[@id='dyntable']/thead/tr/th[7]/span[1]"), 3);
            clickAndWait(By.xpath("//*[@id='dyntable']/thead/tr/th[8]/span[1]"), 3);
            showSuccessPopup("Test Case 'sorting list' completed successfully.");
        } catch (Exception e) {
            handleError(e);
            Assert.fail("Exception during sorting: " + e.getMessage());
        }
    }

    public static void clickAndWait(By locator, int seconds) {
        driver.findElement(locator).click();
        Reference.sleep(seconds);
    }

    public static WebElement waitForVisibility(By locator, int timeout) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public static String generateUniqueCalllistName() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        return "Call List V" + dateFormat.format(new Date());
    }

    public static void showSuccessPopup(String message) {
        final JOptionPane optionPane = new JOptionPane(message, JOptionPane.INFORMATION_MESSAGE, JOptionPane.DEFAULT_OPTION, null, new Object[]{}, null);
        final JDialog dialog = optionPane.createDialog("Success");

        Timer timer = new Timer(3000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dialog.dispose();
            }
        });
        timer.setRepeats(false); // Ensure the timer only runs once
        timer.start();

        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.setVisible(true);
    }

    public static void handleError(Exception e) {
        System.err.println("An error occurred: " + e.getMessage());
        //Package.takeScreenshot(driver); // Use the takeScreenshot method from Package class
        JOptionPane.showMessageDialog(null, "An error occurred: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }

    public static void creatcalllist() {
        clickAndWait(By.id("addRecord"), 3);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        String uniqueCalllistName = "Call List V" + dateFormat.format(new Date());
        driver.findElement(By.id("call_list_name")).sendKeys(uniqueCalllistName);
        driver.findElement(By.xpath("//*[@id=\"cid\"]/option[31]")).click();
        driver.findElement(By.id("max_records")).sendKeys("2");
        clickAndWait(By.id("save"), 2);
    }
}
