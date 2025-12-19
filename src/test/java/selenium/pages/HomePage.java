package selenium.pages;

import selenium.base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

    public HomePage(WebDriver driver) {
        super(driver);

    }

    @FindBy(css = ".category-cards")
    private WebElement pageTitle;

    @FindBy(xpath = "//h5[contains(text(), 'Elements')]/ancestor::div[@class='card mt-4 top-card']")
    private WebElement elementsCard;

    @FindBy(xpath = "//h5[contains(text(), 'Forms')]/ancestor::div[@class='card mt-4 top-card']")
    private WebElement formsCard;

    @FindBy(xpath = "//h5[contains(text(), 'Alerts, Frame & Windows')]/ancestor::div[@class='card mt-4 top-card']")
    private WebElement  alertsCard;

    @FindBy(xpath = "//h5[contains(text(), 'Widgets')]/ancestor::div[@class='card mt-4 top-card']")
    private WebElement widgetsCard;

    @FindBy(xpath = "//h5[contains(text(), 'Interactions')]/ancestor::div[@class='card mt-4 top-card']")
    private WebElement interactionsCard;

    @FindBy(xpath = "//h5[contains(text(), 'Book Store Application')]/ancestor::div[@class='card mt-4 top-card']")
    private WebElement bookStoreCard;

    public boolean isPageOpened(){
       return isElementDisplayed(pageTitle);
    }

    public ElementsPage openElements(){
        scrollToElement(elementsCard);
        clickElement(elementsCard);
        logger.info("Opened section: Elements");
        return new ElementsPage(driver);
    }

    public FormsPage openForms(){
        scrollToElement(formsCard);
        clickElement(formsCard);
        logger.info("Opened section: Forms");
        return new FormsPage(driver);
    }

    public AlertsPage openAlerts(){
        scrollToElement(alertsCard);
        clickElement(alertsCard);
        logger.info("Opened section: Alerts");
        return new AlertsPage(driver);
    }

    public WidgetsPage openWidgets(){
        scrollToElement(widgetsCard);
        clickElement(widgetsCard);
        logger.info("Opened section: Widgets");
        return new WidgetsPage(driver);
    }

    public InteractionsPage openInteractions(){
        scrollToElement(interactionsCard);
        clickElement(interactionsCard);
        logger.info("Opened section: Interactions");
        return new InteractionsPage(driver);
    }

    public BookStorePage openBookStore(){
        scrollToElement(bookStoreCard);
        clickElement(bookStoreCard);
        logger.info("Opened section: BookStore");
        return new BookStorePage(driver);
    }
}
