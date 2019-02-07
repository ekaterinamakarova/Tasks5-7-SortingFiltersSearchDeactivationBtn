package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Initial {

    @FindBy(css = "a[href='/welcome/sign-in']") private WebElement signStr;
    @FindBy(css = "svg[title='Manage Groups']") private WebElement manageGroupsIcon;
    @FindBy(css="[title='Calendar Notes']") private WebElement calendarNotesIcon;
    @FindBy(xpath = "//div[contains(text(),'Admin')]//button[@type='button']") private WebElement adminButton;
    @FindBy(xpath = "//a[contains(text(),'Users')]") private WebElement usersButton;
    @FindBy(xpath = "//a[contains(text(),'Actions Log')]") private WebElement actionsLogButton;
    @FindBy(xpath = "//li[contains(text(),'Logout')]") private WebElement logOutButton;
    @FindBy(css = "svg[title='Price Calendar']") private WebElement priceCalendarButton;

    WebDriver driver;
    HelperClass helper;

    public  Initial(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver,this);
        helper = new HelperClass(driver);
        helper.implicitWait(driver);
    }

    public void toSignPage(){
        signStr.click();
    }


    public void toUsersPage(){
        adminButton.click();
        usersButton.click();
    }

    public void toActionsLog() throws InterruptedException {
        adminButton.click();
        Thread.sleep(100);
        actionsLogButton.click();
    }

    public void logOut() throws InterruptedException {
        Thread.sleep(100);
        adminButton.click();
        logOutButton.click();
    }

    public void toPriceCalendar(){
        priceCalendarButton.click();
    }




}
