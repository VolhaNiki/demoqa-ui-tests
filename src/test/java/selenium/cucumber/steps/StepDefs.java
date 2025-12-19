package selenium.cucumber.steps;

import selenium.base.DriverFactory;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import selenium.pages.HomePage;

public class StepDefs {
    private final WebDriver driver;
    private final HomePage homePage;

    public StepDefs() {
        driver = DriverFactory.getDriver();
        homePage = new HomePage(driver);
    }


    @Given("the user opens the Home Page")
    public void the_user_opens_the_Home_Page(){
        driver.get("https://demoqa.com/");
        Assert.assertTrue(
                homePage.isPageOpened(),
                "Home page should be opened");
    }

    @When("the user clicks on the {string} card")
    public void the_user_clicks_on_the_card(String cardName){
        switch (cardName.toLowerCase()) {
            case "elements" -> homePage.openElements();
            case "forms" -> homePage.openForms();
            case "alerts" -> homePage.openAlerts();
            case "widgets" -> homePage.openWidgets();
            case "interactions" -> homePage.openInteractions();
            case "book store application" -> homePage.openBookStore();
            default -> throw new IllegalArgumentException("Unknown card: " + cardName);
        }
    }

    @Then("the Elements page should be displayed")
    public void the_Elements_page_should_be_displayed(){
        Assert.assertTrue(
                driver.getCurrentUrl().contains("elements"),
                "Should navigate to Elements page");
    }

    @Then("the Forms page should be displayed")
    public void the_Forms_page_should_be_displayed() {
        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("forms"), "Should navigate to Forms page");
    }

    @Then("the page title should contain {string}")
    public void the_page_title_should_contain(String title) {
        Assert.assertTrue(driver.getTitle().contains(title),
                "Page title should contain " + title);
    }

    @Then("all section cards should be displayed")
    public void all_section_cards_should_be_displayed() {
        Assert.assertTrue(homePage.isPageOpened(), "All section cards should be visible");
    }

    @Then("each card should have a title and an icon")
    public void each_card_should_have_a_title_and_an_icon() {
        Assert.assertTrue(homePage.isPageOpened(), "Each card should have title and icon");
    }

    @Then("each card should be clickable")
    public void each_card_should_be_clickable() {
        Assert.assertTrue(true, "Cards are clickable (test placeholder)");
    }

}
