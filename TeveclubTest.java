import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.Arrays;
import java.util.stream.Collectors;

import org.junit.*;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import io.github.bonigarcia.wdm.WebDriverManager;

import org.openqa.selenium.support.ui.*;

public class TeveclubTest {

    private WebDriver driver;
    private WebDriverWait wait;

    public int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    @Before
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void MainPageTest() {
        MainPage mainPage = new MainPage(this.driver);
        Assert.assertTrue(mainPage.getBodyText().contains("Nagyon sok teve"));
    }

    @Test
    public void testLogin() {
        MainPage mainPage = new MainPage(this.driver);
        //Assert.assertTrue(mainPage.getFooterText().contains("2021 ELTE Faculty of Informatics"));
        AccountPage loggedInMainPage = mainPage.login("Teszter", "Selenium");
        Assert.assertTrue(loggedInMainPage.getBodyText().contains("Teszter,"));
        Assert.assertTrue(this.driver.getTitle().contains("TeveClub!"));
    }

    @Test
    public void testLogOut() {
        MainPage mainPage = new MainPage(this.driver);
        //Assert.assertTrue(mainPage.getFooterText().contains("2021 ELTE Faculty of Informatics"));
        AccountPage loggedInMainPage = mainPage.login("Teszter", "Selenium");
        MainPage loggedOutMainPage = loggedInMainPage.logout();
        Assert.assertTrue(loggedOutMainPage.getBodyText().contains("TEVE TOP10"));
    }

    @Test
    public void testImageUpload() {
        MainPage mainPage = new MainPage(this.driver);
        AccountPage loggedInPage = mainPage.login("Teszter", "Selenium");
        AccountSettingsPage accountSettings = loggedInPage.getAccountSettingsPage();
        //accountSettings.uploadImage("D:\\testing\\seleniumtest\\src\\test\\java\\testing.jpg");
        Assert.assertTrue(accountSettings.ImageThere());
    }

    @Test
    public void testUserDescModification() {
        MainPage mainPage = new MainPage(this.driver);
        AccountPage loggedInPage = mainPage.login("Teszter", "Selenium");
        AccountSettingsPage accountSettings = loggedInPage.getAccountSettingsPage();
        Integer [] randomDate = {getRandomNumber(1980, 2010), getRandomNumber(1,12), getRandomNumber(1, 30)};
        Integer gender = getRandomNumber(0,1);
        accountSettings.changeDesc(randomDate, gender);
        AccountViewPage viewPage = accountSettings.getViewPage();
        String monthAsString = String.format("%02d", randomDate[1]);
        String dayAsString = String.format("%02d", randomDate[2]);
        String joinedDate = String.valueOf(randomDate[0]) + "-" + monthAsString + "-" + dayAsString;
        Assert.assertTrue(viewPage.getBodyText().contains(joinedDate));
    }

    @Test
    public void testBrowserHistory() {
        MainPage mainPage = new MainPage(this.driver);
        AccountPage loggedInPage = mainPage.login("Teszter", "Selenium");
        String title = this.driver.getTitle();
        AccountSettingsPage accountSettings = loggedInPage.getAccountSettingsPage();
        this.driver.navigate().back();
        Assert.assertEquals(title, this.driver.getTitle());
    }

    
    @After
    public void close() {
        if (driver != null) {
            driver.quit();
        }
    }
}
