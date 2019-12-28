import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chome.ChromeDriver;
 
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
 
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
 
import org.junit.Assert;
 
public class Example  {
  public static void main(String[] args) {
 
    // Create an instance of the driver
    WebDriver driver = new ChromeDriver();
 
    // Navigate to a web page
    driver.get("http://qainterview.merchante-solutions.com:8080/admin/users/new");
 
    // Perform actions on HTML elements, entering text and submitting the form
    WebElement usernameElement     = driver.findElement(By.id("new_user"));
    WebElement passwordElement     = driver.findElement(By.id("user_password_input"));
    WebElement emailElement     = driver.findElement(By.id("user_email_input"));
    WebElement submitElement        = driver.findElement(By.id("user_submit_action"));
 
    usernameElement.sendKeys("jimmy yu");
    passwordElement.sendKeys("asdfqwer");
 
    //passwordElement.submit(); // submit by text input element
    submitElement.submit();        // submit by form element
 
 
    // Anticipate web browser response, with an explicit wait
    WebDriverWait wait = new WebDriverWait(driver, 10);
    WebElement messageElement = wait.until(
           ExpectedConditions.presenceOfElementLocated(By.id("loginResponse"))
            );
 
    // test success
    String output = driver.findElement(By.xpath("/html/body/div[1]/div[5]/div/div/div[1]/div[2]/div[1]/div")).getText();
    if (output.contains("User was successfully created")) = true)
{    
    String successMsg             = "success";
    Assert.assertEquals (message, successMsg);
}
  // test unsuccessful login
    else if (    String output = driver.findElement(By.xpath("/html/body/div[1]/div[5]/div/div/div[1]/div[2]/div[1]/div")).getText();
    output.contains("502 Bad Gateway"))
    {    String successMsg             = "bad gateway";
    Assert.assertEquals (message, successMsg);}   
  // test field was left blank
    else if (    String output = driver.findElement(By.xpath("/html/body/div[1]/div[5]/div/div/div[1]/div[2]/div[1]/div")).getText();
    output.contains("Username or password can't be blank"))
    {
           String successMsg             = "field was left blank";
    Assert.assertEquals (message, successMsg);
    }
 // invalid email
    else if (    String output = driver.findElement(By.xpath("/html/body/div[1]/div[5]/div/div/div[1]/div[2]/div[1]/div")).getText();
    output.contains("is invalid"))
    {
           String successMsg             = "invalid email, please use valid email";
    Assert.assertEquals (message, successMsg);
    }

    else if (    String output = driver.findElement(By.xpath("/html/body/div[1]/div[5]/div/div/div[1]/div[2]/div[1]/div")).getText();
    output.contains("internal server error"))
    {
           String successMsg             = "internal server error, please refresh the page or go back";
    Assert.assertEquals (message, successMsg);
    }
    // Conclude a test
    driver.quit();
 
  }
}