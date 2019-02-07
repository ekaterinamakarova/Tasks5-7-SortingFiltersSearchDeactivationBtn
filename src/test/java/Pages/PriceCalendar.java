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

    @FindBy(xpath = "//span[contains(text(),'LAUNCH SPECIAL')]/..") private WebElement launchSpecialButton;

    public void checkButtonDeactivation(){
        Assert.assertFalse(launchSpecialButton.isEnabled());
    }

    public void checkButtonActivation(){
        Assert.assertTrue(launchSpecialButton.isEnabled());
    }
}
