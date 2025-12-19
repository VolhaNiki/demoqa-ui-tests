package selenium.base;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import selenium.utils.WaitUtils;


/**
 * A common class for all Page Objects.
 * Contains basic methods for interacting with elements,
 * including clicks, input, drop-down lists, checkboxes, alerts, and mouse actions.
 */

public class BasePage {
    protected WebDriver driver;
    protected WaitUtils waitUtils;
    protected Logger logger;
    protected Actions actions;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.waitUtils = new WaitUtils(driver);
        this.logger = LogManager.getLogger(this.getClass());
        this.actions = new Actions(driver);
        PageFactory.initElements(driver, this);
    }

    /** --- Basic methods of interaction --- **/

    protected void clickElement(WebElement element) {
        try {
            waitUtils.waitForElementToBeClickable(element).click();
            logger.info("Clicked element: " + element);
        } catch (Exception ex) {
            logger.info("Error typing into element: " + element, ex);
            throw ex;
        }

    }

    protected void type(WebElement element, String text) {
        try {
            waitUtils.waitForElementToBeVisible(element).clear();
            element.sendKeys(text);
            logger.info("Typed '" + text + "' into element: " + element);
        } catch (Exception ex) {
            logger.info("Error typing into element: " + element, ex);
            throw ex;
        }
    }

    protected void submit(WebElement element) {
        try {
            element.submit();
            logger.info("Submitted form at: " + element);
        } catch (Exception ex) {
            logger.error("Error submitting form: " + element, ex);
            throw ex;
        }
    }

    protected String getCurrentURL() {
        String url = driver.getCurrentUrl();
        logger.info("Current url: {}", url);
        return url;
    }

    protected void scrollToElement(WebElement element) {
        logger.info("Scrolling to element: {}", element);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", element);
        waitUtils.waitForElementToBeVisible(element);
    }

    protected String getPageTitle(){
        String title = driver.getTitle();
        logger.info("Retrieved page title: {}", title);
        return title;
    }

    protected String getText(WebElement element) {
        return waitUtils.waitForElementToBeVisible(element).getText();
    }

    protected boolean isDisplayed(WebElement element) {
        try {
            return waitUtils.waitForElementToBeVisible(element).isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }

    /** --- Working with drop-down lists --- **/

    protected void selectByVisibleText(WebElement element, String text) {
        try {
            Select select = new Select(element);
            select.selectByVisibleText(text);
            logger.info("Selected '{}' from dropdown {}", text, element);
        } catch (Exception e) {
            logger.error("Error selecting from dropdown: " + element, e);
            throw e;
        }
    }

    protected void selectByValue(WebElement element, String value) {
        Select select = new Select(element);
        select.selectByValue(value);
        logger.info("Selected value '{}' from {}", value, element);
    }

    protected void selectByIndex(WebElement element, int index) {
        Select select = new Select(element);
        select.selectByIndex(index);
        logger.info("Selected index {} from {}", index, element);
    }

    /** --- Working with checkboxes and radio buttons --- **/

    protected void check(WebElement element) {
        if (!element.isSelected()) {
            element.click();
            logger.info("Checked checkbox: " + element);
        }
    }

    protected void uncheck(WebElement element) {
        if (element.isSelected()) {
            element.click();
            logger.info("Unchecked checkbox: " + element);
        }
    }

    protected boolean isChecked(WebElement element) {
        return element.isSelected();
    }

    protected void selectRadioButton(WebElement element) {
        if (!element.isSelected()) {
            element.click();
            logger.info("Selected radio button: " + element);
        }
    }

    /** --- Working with alerts --- **/

    protected void acceptAlert() {
        try {
            Alert alert = waitUtils.waitForAlertIsPresent();
            logger.info("Accepting alert: " + alert.getText());
            alert.accept();
        } catch (TimeoutException e) {
            logger.warn("No alert to accept");
        }
    }

    protected void dismissAlert() {
        try {
            Alert alert = waitUtils.waitForAlertIsPresent();
            logger.info("Dismissing alert: " + alert.getText());
            alert.dismiss();
        } catch (TimeoutException e) {
            logger.warn("No alert to dismiss");
        }
    }

    protected String getAlertText() {
        try {
            String text = waitUtils.waitForAlertIsPresent().getText();
            logger.info("Alert text: " + text);
            return text;
        } catch (TimeoutException e) {
            logger.warn("No alert found");
            return "";
        }
    }

    protected void sendKeysToAlert(String text) {
        try {
            waitUtils.waitForAlertIsPresent().sendKeys(text);
            logger.info("Sent '{}' to alert", text);
        } catch (TimeoutException e) {
            logger.warn("No alert found to send keys");
        }
    }

    /** --- Mouse actions --- **/

    protected void hover(WebElement element) {
        actions.moveToElement(element).perform();
        logger.info("Hovered over: " + element);
    }

    protected void doubleClick(WebElement element) {
        actions.doubleClick(element).perform();
        logger.info("Double-clicked: " + element);
    }

    protected void rightClick(WebElement element) {
        actions.contextClick(element).perform();
        logger.info("Right-clicked: " + element);
    }

    protected boolean isElementDisplayed(WebElement element) {
        try {
            waitUtils.waitForElementToBeVisible(element);
            boolean isDisplayed = element.isDisplayed();
            logger.info("Element is displayed: {}", isDisplayed);
            return isDisplayed;
        } catch (Exception ex) {
            logger.warn("Element not displayed: {}", ex.getMessage());
            return false;
        }
    }

}
