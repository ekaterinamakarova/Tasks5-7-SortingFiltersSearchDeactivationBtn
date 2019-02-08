import Pages.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;

public class TestClass {

    private ChromeDriver driver;

    FileReaderClass readerClass = new FileReaderClass();
    private Initial initial;
    private SignIn signIn;
    private Users users;
    private ActionsLog actionsLog;
    private PriceCalendar priceCalendar;

    @BeforeClass
    public void setUp() throws IOException {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setCapability(CapabilityType.PAGE_LOAD_STRATEGY, "normal");
        options.addArguments("start-maximized");
        options.addArguments("--no-sandbox");
        driver = new ChromeDriver(options);
        driver.get(readerClass.readFromFile(0));
        initial = new Initial(driver);
        signIn = new SignIn(driver);
        users= new Users(driver);
        actionsLog = new ActionsLog(driver);
        priceCalendar =  new PriceCalendar(driver);

    }

    @Test(description = "Login with credentials")
    public void login () throws IOException {
        initial.toSignPage();
        signIn.signin(readerClass.readFromFile(1),readerClass.readFromFile(2));
    }

//    @Test(description = "Users page sorting checking", dependsOnMethods = {"login"})
//    public void usersChecking() throws  InterruptedException, AWTException {
//        initial.toUsersPage();
//        driver.navigate().refresh();
//        users.sort();
//    }
//
//    @Test(description = "Filters checking at Users page", dependsOnMethods = {"usersChecking"})
//    public void filterChecking() throws InterruptedException, AWTException {
//        users.allFilters();
//        users.specialOfferFilter();
//        users.hotel_activeFilter();
//        users.hotel_suspendedFilter();
//        users.tourOperator_suspendedFilter();
//        users.tourOperator_activeFilter();
//    }
//
//    @Test(description = "Search checking at Users page", dependsOnMethods = {"filterChecking"})
//    public void searchChecking() throws InterruptedException {
//        driver.navigate().refresh();
//        users.searchCheck("123", "FLEXAR", "HOTEL");
//    }

//    @Test(description = "Actions Log page's sorting and search checking", dependsOnMethods = {"searchChecking"})
//    public void actionsLogCheck() throws InterruptedException, AWTException {
//        initial.toActionsLog();
//        actionsLog.sorting();
//        actionsLog.search("31", "Super Admin");
//    }

    @Test(description = "Deactivation of special offer", dependsOnMethods = {"login"})
    public void deactivateOffer() throws InterruptedException, IOException {
        initial.toUsersPage();
        users.search("autoAccountHO@autoAccountHO.com");
        String value = users.activationDeaktivation();
        initial.logOut();
        initial.toSignPage();
        signIn.signin(readerClass.readFromFile(3),readerClass.readFromFile(4));
        initial.toPriceCalendar();
        priceCalendar.checkButtonActivation(value);
    }

    @Test(description = "Activation of special offer", dependsOnMethods = {"deactivateOffer"})
    public void activateOffer() throws InterruptedException, IOException {
        initial.logOut();
        initial.toSignPage();
        signIn.signin(readerClass.readFromFile(1),readerClass.readFromFile(2));
        initial.toUsersPage();
        users.search("autoAccountHO@autoAccountHO.com");
        String value = users.activationDeaktivation();
        initial.logOut();
        initial.toSignPage();
        signIn.signin(readerClass.readFromFile(3),readerClass.readFromFile(4));
        initial.toPriceCalendar();
        priceCalendar.checkButtonActivation(value);
    }


    @AfterTest
    public void exit () {
        //driver.quit();
    }
}
