package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


public class ActionsLog {

    WebDriver driver;
    Users users;
    Date date;
    SimpleDateFormat simpleDateFormat;

    public ActionsLog(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver,this);
        users = new Users(driver);
        date =  new Date();
        simpleDateFormat = new SimpleDateFormat("dd MMM yyyy hh:mm:ss");
    }

    @FindBy(xpath = "//tr//td[1]") private List<WebElement> timestampColumn;
    @FindBy(xpath = "//tr//td[2]//span[1]/span[1]") private List<WebElement> actionColumn;
    @FindBy(xpath = "//tr//td[2]") private List<WebElement> actionColumnAll;
    @FindBy(xpath = "//span[contains(text(),'Timestamp')]") private WebElement timestampButton;
    @FindBy(xpath = "//span[contains(text(),'Action')]") private WebElement actionButton;
    @FindBy(css = "button[aria-label='Next Page']") private WebElement nextPageButton;
    @FindBy(css = "input[placeholder='Search by keyword']") private WebElement searchTextBox;

    public void sorting() throws InterruptedException {
        Thread.sleep(200);
//        users.sortingByOrderReverse(timestampColumn,timestampButton);
        actionButton.click();
        users.sortingByOrder(actionColumn,actionButton);
    }

    public void search(String text1, String text2) throws InterruptedException, AWTException {
        Thread.sleep(200);
        users.checkSearchResult(text1, timestampColumn);
        users.checkSearchResult(text2,actionColumn);
    }

    public void timestampActionCheck(String dateValue, String value, String first,String second, String third) {
        Assert.assertEquals(timestampColumn.get(0).getText(), dateValue);

        if (value.equals("DEACTIVATE")) {
            Assert.assertEquals(actionColumnAll.get(0).getText(), first + " deactivated feature Special offer for " + second + " from " + third);

        } else if (value.equals("ACTIVATE")) {
            Assert.assertEquals(actionColumnAll.get(0).getText(), first + " activated feature Special offer for " + second + " from " + third);
        }
        else if (value.equals("Suspend")) {
            Assert.assertEquals(actionColumnAll.get(0).getText(), first + " deactivated " + second + " from " + third);
        }
        else if (value.equals("Reactivate")) {
            Assert.assertEquals(actionColumnAll.get(0).getText(), first + " activated " + second + " from " + third);
        }
        else{
            Assert.assertEquals(false, true);
        }
    }
    }

