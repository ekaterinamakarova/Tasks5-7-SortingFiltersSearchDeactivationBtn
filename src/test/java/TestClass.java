import Pages.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TestClass {

    private ChromeDriver driver;

    FileReaderClass readerClass = new FileReaderClass();
    private Initial initial;
    private SignIn signIn;
    private Users users;
    private ActionsLog actionsLog;
    private PriceCalendar priceCalendar;
    Date date;
    SimpleDateFormat simpleDateFormat;

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
        date =  new Date();
        simpleDateFormat = new SimpleDateFormat("dd MMM yyyy hh:mm:ss");
    }

    @Test(description = "Login with credentials")
    public void login () throws IOException {
        initial.toSignPage();
        signIn.signin(readerClass.readFromFile(1),readerClass.readFromFile(2));
    }

    @Test(description = "Users page sorting checking", dependsOnMethods = {"login"})
    public void usersChecking() throws  InterruptedException, AWTException {
        initial.toUsersPage();
        driver.navigate().refresh();
        users.sort();
    }

    @Test(description = "Filters checking at Users page", dependsOnMethods = {"usersChecking"})
    public void filterChecking() throws InterruptedException, AWTException {
        users.allFilters();
        users.specialOfferFilter();
        users.hotel_activeFilter();
        users.hotel_suspendedFilter();
        users.tourOperator_suspendedFilter();
        users.tourOperator_activeFilter();
    }

    @Test(description = "Search checking at Users page", dependsOnMethods = {"filterChecking"})
    public void searchChecking() throws InterruptedException {
        driver.navigate().refresh();
        users.searchCheck("123", "FLEXAR", "HOTEL");
    }

    @Test(description = "Actions Log page's sorting and search checking", dependsOnMethods = {"searchChecking"})
    public void actionsLogCheck() throws InterruptedException, AWTException {
        initial.toActionsLog();
        actionsLog.sorting();
        actionsLog.search("31", "Super Admin");
    }

    @Test(description = "Deactivation/Activation of special offer", dependsOnMethods = {"actionsLogCheck"})
    public void deactivateOffer() throws InterruptedException, IOException, ParseException {
        initial.toUsersPage();
        users.search(readerClass.readFromFile(3));
        String value1 = users.activationDeaktivation();
        String dateNow1 = simpleDateFormat.format(date);
        initial.toActionsLog();
        actionsLog.timestampActionCheck(dateNow1, value1, readerClass.readFromFile(2), readerClass.readFromFile(4), readerClass.readFromFile(5) );
        initial.logOut();
        initial.toSignPage();
        signIn.signin(readerClass.readFromFile(3),readerClass.readFromFile(4));
        initial.toPriceCalendar();
        priceCalendar.checkButtonActivation(value1);
        initial.logOut();
        initial.toSignPage();
        signIn.signin(readerClass.readFromFile(1),readerClass.readFromFile(2));
        initial.toUsersPage();
        users.search(readerClass.readFromFile(3));
        String value2 = users.activationDeaktivation();
        String dateNow2 = simpleDateFormat.format(date);
        initial.toActionsLog();
        actionsLog.timestampActionCheck(dateNow2,value2, readerClass.readFromFile(2), readerClass.readFromFile(4), readerClass.readFromFile(5) ); 
        initial.logOut();
        initial.toSignPage();
        signIn.signin(readerClass.readFromFile(3),readerClass.readFromFile(4));
        initial.toPriceCalendar();
        priceCalendar.checkButtonActivation(value2);
        initial.logOut();
        initial.toSignPage();
        signIn.signin(readerClass.readFromFile(1),readerClass.readFromFile(2));
    }

    @Test(description = "Suspend user action and its checking", dependsOnMethods = {"deactivateOffer"})
    public void suspendUser() throws InterruptedException, IOException, ParseException {
        for (int i = 0; ; i++) {
            if (i > 1) {
                break;
            } else {
                initial.toUsersPage();
                users.search("autoAccountHO@autoAccountHO.com");
                String value = users.suspendReactivate();
                String dateNow = simpleDateFormat.format(date);
                initial.toActionsLog();
                actionsLog.timestampActionCheck(dateNow,value, readerClass.readFromFile(2), readerClass.readFromFile(4), readerClass.readFromFile(5));///
                if (value.equals("Suspend")) {
                    initial.logOut();
                    initial.toSignPage();
                    signIn.signin(readerClass.readFromFile(3), readerClass.readFromFile(4));
                    signIn.suspendReactivateCheck(value);
                    driver.navigate().refresh();
                } else if (value.equals("Reactivate")) {
                    initial.logOut();
                    initial.toSignPage();
                    signIn.signin(readerClass.readFromFile(3), readerClass.readFromFile(4));
                    signIn.suspendReactivateCheck(value);
                    initial.logOut();
                    driver.navigate().refresh();
                    initial.toSignPage();
                }
                signIn.signin(readerClass.readFromFile(1), readerClass.readFromFile(2));
            }
        }
    }

    @AfterTest
    public void exit () {
        driver.quit();
    }
}
