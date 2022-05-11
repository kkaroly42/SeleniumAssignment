import org.junit.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;


class MainPage extends PageBase {

    private By usernameInputBy = By.xpath("//input[@name='tevenev']");
    private By passwordInputBy = By.xpath("//input[@name='pass']");
    private By loginButtonBy = By.xpath("//input[@src='/img_des/login_submit_46.gif']");
    
    public MainPage(WebDriver driver) {
        super(driver);
        this.driver.get("https://www.teveclub.hu/");
    }


    public AccountPage login(String username, String password) {
        this.waitAndReturnElement(usernameInputBy).sendKeys(username);
        this.waitAndReturnElement(passwordInputBy).sendKeys(password);
        this.waitAndReturnElement(loginButtonBy).click();
        return new AccountPage(this.driver);
    }

}
