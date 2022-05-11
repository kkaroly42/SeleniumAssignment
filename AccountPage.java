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


class AccountPage extends PageBase {

    private By logoutButtonBy = By.xpath("//img[@name='menu7']");
    private By bodyBy = By.xpath("//body");
    private By accountsettingsBy = By.xpath("//img[@name='menu0']");

    public AccountPage(WebDriver driver) {
        super(driver);
    }
    
    public String getBody() {
        return this.waitAndReturnElement(bodyBy).getText();
    }

    public MainPage logout() {
        this.waitAndReturnElement(logoutButtonBy).click();
        return new MainPage(this.driver);
    }

    public AccountSettingsPage getAccountSettingsPage() {
        this.waitAndReturnElement(accountsettingsBy).click();
        return new AccountSettingsPage(this.driver);
    }
           
}
