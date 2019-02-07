package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class PriceCalendar {

    WebDriver driver;
    public PriceCalendar(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "//span[contains(text(),'LAUNCH SPECIAL')]/../..") private WebElement launchSpecialButton;

    public void checkButtonDeactivation() throws InterruptedException {
        Thread.sleep(500);
       Assert.assertFalse(launchSpecialButton.getAttribute("title").isEmpty());
    }

    public void checkButtonActivation(){
        Assert.assertTrue(launchSpecialButton.getAttribute("title").isEmpty());
    }
}
