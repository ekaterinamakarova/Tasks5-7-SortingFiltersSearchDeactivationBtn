package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SignIn {

    @FindBy(css = "input[name='email']") private WebElement email;
    @FindBy(css = "input[name='password']") private WebElement password;
    @FindBy(css = "button[type='submit']") private WebElement signIn;

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
}
