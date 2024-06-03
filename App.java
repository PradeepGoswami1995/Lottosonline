import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import javax.swing.*;
import java.util.concurrent.TimeUnit;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class App {
    private WebDriver driver;

    @Parameters({"browser"})
    @BeforeClass
    public void setup(@Optional("chrome") String browser) {
        if (browser.equalsIgnoreCase("chrome")) {
            System.setProperty("webdriver.chrome.driver", "E:\\Lottos Java\\lib\\driver\\chromedriver-win64\\chromedriver.exe");
            driver = new ChromeDriver();
        } else if (browser.equalsIgnoreCase("firefox")) {
            System.setProperty("webdriver.gecko.driver", "E:\\Lottos Java\\lib\\driver\\geckodriver.exe");
            driver = new FirefoxDriver();
        }
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
            driver.findElement(By.id("email")).sendKeys("gopher@famcominc.com");
            driver.findElement(By.id("password")).sendKeys("FamComInc");
            clickAndWait(By.id("submit_button"), 2);
            sleep(3);
            // Assert.assertTrue(driver.findElement(By.id("submit_button")).isDisplayed(), "Login failed!");
        } catch (Exception e) {
            handleError(e);
            // Assert.fail("Exception during admin login: " + e.getMessage());
        }
    }

    @Test(dependsOnMethods = "testAdminLogin")
    public void testNavigatingPackage() {
        try {
            clickAndWait(By.xpath("//*[@id=\"menu\"]/li[3]/a"), 2);
            sleep(5);
        } catch (Exception e) {
            handleError(e);
            Assert.fail("Exception during navigating package: " + e.getMessage());
        }
    }

    @Test(dependsOnMethods = "testNavigatingPackage")
    public void testCreatePackage() {
        try {
            clickAndWait(By.partialLinkText("Create Package"), 2);
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
            String uniquePackageName = "Package manager V" + dateFormat.format(new Date());
            driver.findElement(By.id("package_name")).sendKeys(uniquePackageName);
            driver.findElement(By.id("package_description")).sendKeys("Testing by Automation, Game + Jackpot pool");
            clickAndWait(By.id("package_type"), 1);
            clickAndWait(By.xpath("//*[@id=\"package_type\"]/option[3]"), 1);
            driver.findElement(By.id("package_price")).sendKeys("30");
            clickAndWait(By.cssSelector("#gametbl > tbody > tr:nth-child(11) > td:nth-child(1) > input[type=checkbox]"), 1);
            clickAndWait(By.cssSelector("#gametbl > tbody > tr.lucky_diprow > td:nth-child(1) > input[type=checkbox]"), 1);
            clickAndWait(By.id("savepkg"), 3);
            takeScreenshot();
            showSuccessPopup("Package created successfully!");
        } catch (Exception e) {
            handleError(e);
            Assert.fail("Exception during create package: " + e.getMessage());
        }
    }

    @Test(dependsOnMethods = "testCreatePackage")
    public void testEditPackage() {
        try {
            clickAndWait(By.cssSelector("a[href^='/admin/create_admin_packages.php?uid=']:first-of-type"), 5);
            clickAndWait(By.id("savepkg"), 2);
            System.out.println("Edit successfully");
            showSuccessPopup("Record Edited successfully!");
            takeScreenshot();
        } catch (Exception e) {
            handleError(e);
            Assert.fail("Exception during edit package: " + e.getMessage());
        }
    }

    @Test(dependsOnMethods = "testEditPackage")
    public void testSorting() {
        try {
            clickAndWait(By.xpath("//*[@id=\"dyntable\"]/thead/tr/th[2]/span"), 3);
            takeScreenshot();
            clickAndWait(By.xpath("//*[@id=\"dyntable\"]/thead/tr/th[4]/span"), 3);
            takeScreenshot();
            clickAndWait(By.xpath("//*[@id=\"dyntable\"]/thead/tr/th[7]/span"), 3);
            takeScreenshot();
            clickAndWait(By.xpath("//*[@id=\"dyntable\"]/thead/tr/th[8]/span"), 3);
            takeScreenshot();
            clickAndWait(By.xpath("//*[@id=\"dyntable\"]/thead/tr/th[9]/span"), 3);
            takeScreenshot();
            clickAndWait(By.xpath("//*[@id=\"dyntable\"]/thead/tr/th[10]/span"), 3);
            takeScreenshot();
            showSuccessPopup("All columns have been sorted successfully!");
        } catch (Exception e) {
            handleError(e);
            Assert.fail("Exception during sorting: " + e.getMessage());
        }
    }

    @Test(dependsOnMethods = "testSorting")
    public void testCreatePromoPackage() {
        try {
            clickAndWait(By.partialLinkText("Create Package"), 2);
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
            String uniquePackageName = "Package manager V" + dateFormat.format(new Date());
            driver.findElement(By.id("package_name")).sendKeys(uniquePackageName);
            driver.findElement(By.id("package_description")).sendKeys("Testing by Automation, Game + Jackpot pool");
            clickAndWait(By.id("promo_package"), 1);
            clickAndWait(By.xpath("//*[@id=\"promo_package\"]/option[2]"), 1);
            sleep(1);
            driver.findElement(By.id("promo_package_price")).sendKeys("50");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String currentDate = sdf.format(new Date());
            System.out.println("Current Date: " + currentDate);
            clickAndWait(By.id("fromdate"), 1);
            sleep(5);
            driver.findElement(By.id("fromdate")).sendKeys(currentDate);
            clickAndWait(By.cssSelector("#gametbl > tbody > tr:nth-child(11) > td:nth-child(1) > input[type=checkbox]"), 1);
            clickAndWait(By.cssSelector("#gametbl > tbody > tr.lucky_diprow > td:nth-child(1) > input[type=checkbox]"), 1);
            clickAndWait(By.id("savepkg"), 3);
            showSuccessPopup("Promo package created successfully!");
            takeScreenshot();
        } catch (Exception e) {
            handleError(e);
            Assert.fail("Exception during create promo package: " + e.getMessage());
        }
    }

    public void handleError(Exception e) {
        System.err.println("An error occurred: " + e.getMessage());
        takeScreenshot();
        JOptionPane.showMessageDialog(null, "An error occurred: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }

    public void showSuccessPopup(String message) {
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

    public void takeScreenshot() {
        try {
            TakesScreenshot ts = (TakesScreenshot) driver;
            File screenshot = ts.getScreenshotAs(OutputType.FILE);
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
            String timestamp = dateFormat.format(new Date());
            String fileName = "Package manager" + timestamp + ".png";
            File destination = new File("D:\\Images\\" + fileName);
            if (!destination.getParentFile().exists()) {
                destination.getParentFile().mkdirs(); // Ensure the directory exists
            }
            FileUtils.copyFile(screenshot, destination);
            System.out.println("Screenshot saved: " + destination.getAbsolutePath());
        } catch (IOException e) {
            System.err.println("Error saving the screenshot: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("Error taking the screenshot: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void clickAndWait(By locator, int seconds) {
        driver.findElement(locator).click();
        sleep(seconds);
    }

    public void sleep(int seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
