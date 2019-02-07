package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.awt.*;
import java.util.List;

public class ActionsLog {

    WebDriver driver;
    Users users;

    public ActionsLog(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver,this);
        users = new Users(driver);
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
       // users.sortingByOrderReverse(timestampColumn,timestampButton);
        actionButton.click();
        users.sortingByOrder(actionColumn,actionButton);

    }

    public void search(String text1, String text2) throws InterruptedException, AWTException {
        Thread.sleep(200);
        users.checkSearchResult(text1, timestampColumn);
        users.checkSearchResult(text2,actionColumn);
    }
}
