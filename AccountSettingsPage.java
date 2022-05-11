import org.junit.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;


class AccountSettingsPage extends PageBase {

    private By imageInputBy = By.xpath("//input[@name='foto']");
    private By submitButtonBy = By.xpath("//form[@name='modifyfoto']//input[@type='SUBMIT']");
    private By imageBy = By.xpath("//form[@name='modifyfoto']//img");
    private By bodyBy = By.xpath("//body");
    private By userinfoLinkBy = By.xpath("//form[@name='modifypers']//a[@href]");
    private By userinfoTextBoxBy = By.xpath("//form[@name='modifypers']//textarea");
    private By userinfoSubmitButtonBy = By.xpath("//input[@name='modifypers']");
    private By userBirthYearBy = By.xpath("//input[@name='szulev']");
    private By userBirthMonthBy = By.xpath("//select[@name='szulho']");
    private By userBirthDayBy = By.xpath("//select[@name='szulnap']");
    private By userGenderBy = By.xpath("//select[@name='nem']");

    public AccountSettingsPage(WebDriver driver) {
        super(driver);
    }
    
    public String getBody() {
        return this.waitAndReturnElement(bodyBy).getText();
    }

    public void uploadImage(String path) {
        this.waitAndReturnElement(imageInputBy).sendKeys(path);
        this.waitAndReturnElement(submitButtonBy).click();
    }

    public Boolean ImageThere() {
	    WebElement ImageFile = this.waitAndReturnElement(imageBy);   
        Boolean ImagePresent = (Boolean) ((JavascriptExecutor)driver).executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0", ImageFile);
        return ImagePresent;
	}

    

    public void changeDesc(Integer [] date, Integer genderInt) {
        this.waitAndReturnElement(userBirthYearBy).clear();
        this.waitAndReturnElement(userBirthYearBy).sendKeys(String.valueOf(date[0]));
        Select month = new Select(this.waitAndReturnElement(userBirthMonthBy));
        month.selectByIndex(date[1]);
        Select day = new Select(this.waitAndReturnElement(userBirthDayBy));
        day.selectByIndex(date[2]);
        Select gender = new Select(this.waitAndReturnElement(userGenderBy));
        gender.selectByIndex(genderInt);
        this.waitAndReturnElement(userinfoTextBoxBy).clear();
        this.waitAndReturnElement(userinfoTextBoxBy).sendKeys("Hello there");
        this.waitAndReturnElement(userinfoSubmitButtonBy).click();
    }
    
    public AccountViewPage getViewPage() {
        this.waitAndReturnElement(userinfoLinkBy).click();
        return new AccountViewPage(this.driver);
    }
           
}
