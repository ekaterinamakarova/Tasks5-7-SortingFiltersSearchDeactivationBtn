package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class PriceCalendar {

    WebDriver driver;
    Users users;
    public PriceCalendar(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver,this);
        users= new Users(driver);
    }

    @FindBy(xpath = "//span[contains(text(),'LAUNCH SPECIAL')]/../..") private WebElement launchSpecialButton;



    public void checkButtonActivation(String value) throws InterruptedException {
        if(value.equals("DEACTIVATE")) {
            Assert.assertFalse(launchSpecialButton.getAttribute("title").isEmpty());
        }
        else if (value.equals("DEACTIVATE")) {
            Assert.assertTrue(launchSpecialButton.getAttribute("title").isEmpty());
        }
        else {
            Assert.assertEquals(false,true);
        }

    }


}
