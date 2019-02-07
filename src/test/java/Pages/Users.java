package Pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Users {

    WebDriver driver;
    HelperClass helper;

    public Users(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        helper = new HelperClass(driver);
    }

    @FindBy(xpath = "//tr//td[1]//p[1]") private List<WebElement> usernameColumn;
    @FindBy(xpath = "//tr//td[1]//p[2]") private List<WebElement> usernameEmail;
    @FindBy(xpath = "//tr//td[2]") private List<WebElement> companyNameColumn;
    @FindBy(xpath = "//tr//td[3]") private List<WebElement> companyTypeColumn;
    @FindBy(xpath = "//tr//td[4]") private List<WebElement> statusColumn;
    @FindBy(xpath = "//span[contains(text(),'Company name')]") private WebElement companyNameSortBtn;
    @FindBy(xpath = "//span[contains(text(),'Username')]") private WebElement usernameSortBtn;
    @FindBy(xpath = "//span[contains(text(),'Company type')]") private WebElement companyTypeBtn;
    @FindBy(xpath = "//span[contains(text(),'Status')]") private WebElement statusButton;
    @FindBy(css = "button[aria-label='Next Page']") private WebElement nextPageButton;
    @FindBy(css = "button[aria-label='Previous Page']") private WebElement previousPageButton;
    @FindBy(xpath = "//h1") private WebElement pageTitle;
    @FindBy(xpath = "//button[@title='Filter list']") private WebElement filterButton;
    @FindBy(xpath = "//span[contains(text(),'HOTEL')]/../../../li[1]") private WebElement companyTypeTitle;
    @FindBy(xpath = "//span[contains(text(),'ACTIVE')]/../../../li[1]") private WebElement userStatusTitle;
    @FindBy(xpath = "//span[contains(text(),'Special offers')]/../../../li[1]") private WebElement featuresTitle;
    @FindBy(xpath = "//span[contains(text(),'HOTEL')]/../..//span//input") private WebElement HOTELcheckbox;
    @FindBy(xpath = "//span[contains(text(),'TOUR OPERATOR')]/../..//span//input") private WebElement TOUR_OPERATORcheckbox;
    @FindBy(xpath = "//span[contains(text(),'ACTIVE')]/../..//span//input") private WebElement ACTIVEcheckbox;
    @FindBy(xpath = "//span[contains(text(),'SUSPENDED')]/../..//span//input") private WebElement SUSPENDEDcheckbox;
    @FindBy(xpath = "//span[contains(text(),'Special offers')]/../..//span//input") private WebElement SpecialOffersCheckbox;
    @FindBy(css = "tr td:nth-of-type(5) svg") private List<WebElement> points;
    @FindBy(xpath = "//span[contains(text(),'Features...')]") private WebElement featuresBtn;
    @FindBy(xpath = "//div[2]/div[2]/div[1]/button[1]") private WebElement deactivateFeature;
    @FindBy(xpath = "//span[contains(text(),'CLOSE')]") private WebElement closeBtn;
    @FindBy(css = "input[placeholder='Search by keyword']") private WebElement searchTextBox;


    public void sort() throws InterruptedException, AWTException {
        Thread.sleep(200);
        System.out.println("------------------------------------------1--------------------------------");
        sortingByOrder(usernameColumn, usernameSortBtn);
        System.out.println("------------------------------------------2--------------------------------");
        helper.scrollToElement(pageTitle);
        companyNameSortBtn.click();
        sortingByOrderReverse(companyNameColumn, companyNameSortBtn);
        System.out.println("------------------------------------------3--------------------------------");
        helper.scrollToElement(pageTitle);
        companyTypeBtn.click();
        sortingByOrder(companyTypeColumn, companyTypeBtn);
        System.out.println("------------------------------------------4---------------------------------");
        helper.scrollToElement(pageTitle);
        statusButton.click();
        sortingByOrderReverse(statusColumn, statusButton);


    }

    public void sortingByOrder(List<WebElement> column, WebElement button) throws InterruptedException {
        Thread.sleep(200);
        do {
            ArrayList<String> obtainedList = new ArrayList<>();
            for (WebElement we : column) {
                obtainedList.add(we.getText());
            }
            obtainedList.removeAll(Arrays.asList(""));
            ArrayList<String> sortedList = new ArrayList<>();
            for (String s : obtainedList) {
                sortedList.add(s);
            }
            Collections.sort(sortedList, String.CASE_INSENSITIVE_ORDER);
            System.out.println(obtainedList);
            System.out.println(sortedList);
            Assert.assertTrue(obtainedList.equals(sortedList));
            nextPageButton.click();
        }
        while (nextPageButton.isEnabled());

        helper.scrollToElement(pageTitle);
        button.click();

        do {
            ArrayList<String> obtainedList = new ArrayList<>(); //по возрастанию
            for (WebElement we : column) {
                obtainedList.add(we.getText());
            }
            ArrayList<String> sortedList = new ArrayList<>();
            for (String s : obtainedList) {
                sortedList.add(s);
            }
            Collections.sort(sortedList, Collections.reverseOrder());
            System.out.println(obtainedList);
            System.out.println(sortedList);
            Assert.assertTrue(sortedList.equals(obtainedList));
            previousPageButton.click();
        }
        while (previousPageButton.isEnabled());

    }


    public void sortingByOrderReverse(List<WebElement> column, WebElement button) throws InterruptedException {
       Thread.sleep(200);
        do {
            ArrayList<String> obtainedList = new ArrayList<>();
            for (WebElement we : column) {
                obtainedList.add(we.getText());
            }
            ArrayList<String> sortedList = new ArrayList<>();
            for (String s : obtainedList) {
                sortedList.add(s);
            }
            Collections.sort(sortedList, Collections.reverseOrder(String.CASE_INSENSITIVE_ORDER));
            System.out.println(obtainedList);
            System.out.println(sortedList);
            Assert.assertTrue(obtainedList.equals(sortedList));
            nextPageButton.click();
        }
        while (nextPageButton.isEnabled());

        helper.scrollToElement(pageTitle);
        button.click();

        do {
            ArrayList<String> obtainedList = new ArrayList<>();
            for (WebElement we : column) {
                obtainedList.add(we.getText());
            }
            ArrayList<String> sortedList = new ArrayList<>();
            for (String s : obtainedList) {
                sortedList.add(s);
            }
            Collections.sort(sortedList, String.CASE_INSENSITIVE_ORDER);
            System.out.println(obtainedList);
            System.out.println(sortedList);
            Assert.assertTrue(sortedList.equals(obtainedList));
            previousPageButton.click();
        }
        while (previousPageButton.isEnabled());

    }

    public void allFilters() {
        Assert.assertTrue(filterButton.isDisplayed());
        filterButton.click();
        driver.navigate().refresh();
        filterButton.click();
        helper.assertText("CompanyType", companyTypeTitle);
        helper.assertText("User status", userStatusTitle);
        helper.assertText("Features", featuresTitle);
        Assert.assertTrue(HOTELcheckbox.isSelected());
        Assert.assertTrue(TOUR_OPERATORcheckbox.isSelected());
        Assert.assertTrue(ACTIVEcheckbox.isSelected());
        Assert.assertTrue(SUSPENDEDcheckbox.isSelected());
        Assert.assertFalse(SpecialOffersCheckbox.isSelected());
    }

    public void specialOfferFilter() throws InterruptedException {
        SpecialOffersCheckbox.click();
        driver.switchTo().activeElement().sendKeys(Keys.ESCAPE);
        Thread.sleep(500);
        for (int i = 0; i < points.size(); i++) {
           points.get(i).click();
           featuresBtn.click();
            Assert.assertTrue(deactivateFeature.isDisplayed());
            closeBtn.click();
            Thread.sleep(500);
        }

    }

    public void hotel_activeFilter() throws AWTException, InterruptedException {
        filterButton.click();
        Thread.sleep(200);
        TOUR_OPERATORcheckbox.click();
        SUSPENDEDcheckbox.click();
        driver.switchTo().activeElement().sendKeys(Keys.ESCAPE);
        Thread.sleep(300);
        checkFilterResult(companyTypeColumn, "HOTEL", statusColumn, "ACTIVE");

    }

    public void hotel_suspendedFilter() throws InterruptedException, AWTException {
        driver.switchTo().activeElement().click();
        Thread.sleep(200);
        ACTIVEcheckbox.click();
        SUSPENDEDcheckbox.click();
        driver.switchTo().activeElement().sendKeys(Keys.ESCAPE);
        Thread.sleep(300);
        checkFilterResult(companyTypeColumn, "HOTEL", statusColumn, "SUSPENDED");
    }

    public void tourOperator_suspendedFilter() throws InterruptedException, AWTException {
        driver.switchTo().activeElement().click();
        Thread.sleep(200);
        HOTELcheckbox.click();
        TOUR_OPERATORcheckbox.click();
        driver.switchTo().activeElement().sendKeys(Keys.ESCAPE);
        Thread.sleep(300);
        checkFilterResult(companyTypeColumn, "TOUR OPERATOR", statusColumn, "SUSPENDED");
    }

    public void tourOperator_activeFilter() throws InterruptedException, AWTException {
        driver.switchTo().activeElement().click();
        Thread.sleep(200);
        SUSPENDEDcheckbox.click();
        ACTIVEcheckbox.click();
        driver.switchTo().activeElement().sendKeys(Keys.ESCAPE);
        Thread.sleep(300);
        checkFilterResult(companyTypeColumn, "TOUR OPERATOR", statusColumn, "ACTIVE");
    }


    public void checkFilterResult(List<WebElement> column1, String text1, List<WebElement> column2, String text2) throws AWTException, InterruptedException {
        do {
            ArrayList<String> resultList_column1 = new ArrayList<>();
            for (WebElement we : column1) {
                resultList_column1.add(we.getText());
            }
            for (String s : resultList_column1) {
                Assert.assertEquals(text1, s);
            }

            ArrayList<String> resultList_column2 = new ArrayList<>();
            for (WebElement we : column2) {
                resultList_column2.add(we.getText());
            }
            for (String s : resultList_column2) {
                Assert.assertEquals(text2, s);
            }

            driver.switchTo().activeElement().sendKeys(Keys.ESCAPE);
            Thread.sleep(500);
            if (nextPageButton.isEnabled()) {
                nextPageButton.click();
            }
        }
        while (nextPageButton.isEnabled());


    }

    public void searchCheck(String username, String compName, String compType) throws InterruptedException {
        checkSearchResultUsername(username, usernameColumn, usernameEmail);
        checkSearchResult(compName, companyNameColumn);
        checkSearchResult(compType, companyTypeColumn);
    }


    public void checkSearchResultUsername(String text, List<WebElement> column, List<WebElement> anothercolumn) throws InterruptedException {
        boolean result = false;
        Thread.sleep(200);
        searchTextBox.sendKeys(text);
        do {
            ArrayList<String> resultList_column = new ArrayList<>();
            for (WebElement we : column) {
                resultList_column.add(we.getText());
            }
            for (WebElement we : anothercolumn) {
                resultList_column.add(we.getText());
            }
            for (String s : resultList_column) {
                if (s.contains(text)) {
                    result = true;
                    Assert.assertTrue(result);
                }
            }
        }
            while (nextPageButton.isEnabled());
            searchTextBox.clear();
        }

    public void checkSearchResult(String text, List<WebElement> column) throws InterruptedException {
        boolean result = false;
        Thread.sleep(200);
        searchTextBox.sendKeys(text);
        do {
            ArrayList<String> resultList_column = new ArrayList<>();
            for (WebElement we : column) {
                resultList_column.add(we.getText());
            }

            for (String s : resultList_column) {
                if (s.contains(text)) {
                    result = true;
                    Assert.assertTrue(result);
                }
            }
        }
        while (nextPageButton.isEnabled());
        searchTextBox.clear();
    }

    public void search(String string) throws InterruptedException {
        Thread.sleep(200);
        searchTextBox.sendKeys(string);
    }

    public void activationDeaktivation(){
        points.get(0).click();
        featuresBtn.click();
        deactivateFeature.click();
        closeBtn.click();
    }

    }

