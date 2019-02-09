package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SignIn {

    @FindBy(css = "input[name='email']") private WebElement email;
    @FindBy(css = "input[name='password']") private WebElement password;
    @FindBy(css = "button[type='submit']") private WebElement signIn;
    @FindBy(xpath = "//form[1]/div[1]/div[4]/p[1]") private WebElement wrongLoginHint;
    @FindBy(xpath = "//h1[contains(text(),'Welcome')]") private WebElement welcomeTitle;

    WebDriver driver;
    public  SignIn(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    public void signin(String mail, String pass){
        email.sendKeys(mail);
        password.sendKeys(pass);
        signIn.click();
    }

    public void suspendReactivateCheck(String value){
        if(value.equals("Suspend")) {
            Assert.assertEquals(wrongLoginHint.getText(), "Wrong Email or Password");
        }
        else if (value.equals("Reactivate")) {
          Assert.assertTrue(welcomeTitle.isDisplayed());
        }
        else {
            Assert.assertEquals(false,true);
        }
    }
}
