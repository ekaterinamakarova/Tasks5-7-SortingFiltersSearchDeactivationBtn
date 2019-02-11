package Pages;

import org.openqa.selenium.*;


import org.openqa.selenium.interactions.Locatable;
import org.openqa.selenium.interactions.internal.Coordinates;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.testng.Assert;


import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static java.util.concurrent.TimeUnit.SECONDS;

public class HelperClass {

    WebDriver driver;

    public HelperClass(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement waitForClikable(WebElement element) {
        return getWebDriverWait().until(ExpectedConditions.elementToBeClickable(element));
    }

    public Boolean waitForStaleness(WebElement element) {
        return getWebDriverWait().until(ExpectedConditions.stalenessOf(element));
    }

    public void implicitWait(WebDriver driver) {
        driver.manage().timeouts().implicitlyWait(10, SECONDS);
    }

    public Wait<WebDriver> getWebDriverWait() {
        return (Wait<WebDriver>) new FluentWait<>(driver)
                .withTimeout(25, SECONDS)
                .pollingEvery(250, MILLISECONDS)
                .ignoring(NoSuchElementException.class)
                .ignoring(StaleElementReferenceException.class);
    }

    public void scrollToElement(WebElement element) throws InterruptedException {
        Coordinates cor=((Locatable)element).getCoordinates();
        cor.inViewPort();
        Thread.sleep(1000);
    }

    public void sendESC(WebElement element, String string) {
        element.sendKeys(string);
    }

    public void assertText(String text, WebElement element){
        Assert.assertEquals(text, element.getText());
    }
}
