package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class UiActions {
    
    private WebDriver driver;
    private AsyncSeleniumHelper waitHelper;

    public UiActions(WebDriver driver) {
        this.driver = driver;
        this.waitHelper = new AsyncSeleniumHelper(driver);
    }

    public void sendKeys(By locator, String text) {
        WebElement element = waitHelper.waitForVisibility(locator);
        element.clear();
        element.sendKeys(text);
    }

    public void click(By locator) {
        waitHelper.waitForClickability(locator).click();
    }

    public String getText(By locator) {
        return waitHelper.waitForVisibility(locator).getText();
    }
}
