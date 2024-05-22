import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;

public class Calllist 
{
    private static WebDriver driver;

    public static void main(String[] args)
     {
        try {
        Setdriver();
        adminlogin();
        Navigatecalllist();
        creatcalllist();
        showSuccessPopup("Test Case 'Create Call List' completed successfully.");
        delete();
        showSuccessPopup("Test Case 'Delete call list' completed successfully.");
        creatcalllist();
        assignCallList();
        showSuccessPopup("Test Case 'Assign call list ' completed successfully."); 
        archivecallist();
        showSuccessPopup("Test Case 'Archive call list ' completed successfully."); 
        impcalllist();
        showSuccessPopup("Test Case 'upload CSV file to create call list ' completed successfully."); 
        delete();
        showSuccessPopup("Test Case 'Delete call list which is created by upload CSV file to   ' completed successfully."); 
        creatcalllist();
        sorting();
        showSuccessPopup("Test Case 'sorting list' completed successfully."); 
        //searchpanel();  
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
        //driver.findElement(By.id("addRecord")).click();
        clickAndWait(By.id("addRecord"), 3);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        String uniqueCalllistName = "Call List V" + dateFormat.format(new Date());
        driver.findElement(By.id("call_list_name")).sendKeys(uniqueCalllistName);
       
       driver.findElement(By.xpath("//*[@id=\"cid\"]/option[31]")).click();
        driver.findElement(By.id("max_records")).sendKeys("2");
        // Click and wait for the save button
        clickAndWait(By.id("save"), 2);
       //showSuccessPopup("Call list Created Successfully");
    }   

    public static void assignCallList() 
    {
        // Click the button to open the dropdown
        driver.findElement(By.xpath("//button[@name='assign_user']")).click();
        
        // Wait for the dropdown to be visible using the helper method
        WebElement dropdown = waitForVisibility(By.id("sales_user"), 10);

        // Create a Select object from the dropdown WebElement
        Select select = new Select(dropdown);

        // Select the option with the value "134948"
        select.selectByValue("134948");

        // Click the save button and wait using the helper method
        clickAndWait(By.id("saveAssign"), 3);
        //showSuccessPopup("Call list Assign Successfully");
    }

    public static void archivecallist()
    {
        clickAndWait(By.xpath("//button[contains(text(), 'Archive')]"), 2);

        Reference.sleep(2);
        
        try {
            // Switch to the alert
            Alert alert = driver.switchTo().alert();
            // Accept the alert (click OK)
            alert.accept();
        } catch (NoAlertPresentException e) {
            // Handle the case where the alert is not present
            System.out.println("No alert present.");
        }
        //showSuccessPopup("Call list archive Successfully");
    }

    public static void delete() {
        driver.findElement(By.name("delete")).click();
        // Assuming a JavaScript alert is triggered
        // Wait for a short time to ensure the alert appears
        Reference.sleep(2);
        
        try {
            // Switch to the alert
            Alert alert = driver.switchTo().alert();
            // Accept the alert (click OK)
            alert.accept();
        } catch (NoAlertPresentException e) {
            // Handle the case where the alert is not present
            System.out.println("No alert present.");
        }
        //showSuccessPopup("Call list Deleted Successfully");
    }

    public static void sorting()
    {
        clickAndWait(By.xpath("//*[@id='dyntable']/thead/tr/th[2]/span[1]"), 3);
        clickAndWait(By.xpath("//*[@id='dyntable']/thead/tr/th[3]/span[1]"), 3);
        clickAndWait(By.xpath("//*[@id='dyntable']/thead/tr/th[4]/span[1]"), 3);
        clickAndWait(By.xpath("//*[@id='dyntable']/thead/tr/th[5]/span[1]"), 3);
        clickAndWait(By.xpath("//*[@id='dyntable']/thead/tr/th[6]/span[1]"), 3);
        clickAndWait(By.xpath("//*[@id='dyntable']/thead/tr/th[7]/span[1]"), 3);
        clickAndWait(By.xpath("//*[@id='dyntable']/thead/tr/th[8]/span[1]"), 3);
        showSuccessPopup("Sorting working properly on the list page");
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

    public static WebElement waitForVisibility(By locator, int timeout) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public static void impcalllist() 
    {
        clickAndWait(By.id("addRecord"), 3);
        clickAndWait(By.id("uploadcsv"), 3);
        
        String uniqueCalllistName = generateUniqueCalllistName();
        driver.findElement(By.id("uploadcsv_list_name")).sendKeys(uniqueCalllistName);

        // Ensure the file input element is visible and interactable
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement uploadElement = wait.until(ExpectedConditions.elementToBeClickable(By.id("csv_file")));

        // Provide the file path to upload
        uploadElement.sendKeys("C:\\Users\\Famcom\\Desktop\\blank.csv");

        // Click the save button
        clickAndWait(By.id("saveUploadcsv"), 3);
        //showSuccessPopup("Call list Created Successfully by upload CSV file");
    }

    public static String generateUniqueCalllistName() 
    {
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


    
    



    
}
