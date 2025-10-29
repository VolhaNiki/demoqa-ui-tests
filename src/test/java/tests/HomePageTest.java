package tests;

import base.BaseTest;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.ElementsPage;
import pages.HomePage;

/**
 * Tests for the demoqa.com homepage
 */

@Epic("DemoQA UI")
@Feature("Home Page")
public class HomePageTest extends BaseTest {

    @Test(description = "Проверить, что главная страница открывается и переход в раздел Elements работает")
    @Story("Home page navigation")
    @Severity(SeverityLevel.CRITICAL)
    public void openElementsSectionTest() {
        HomePage homePage = new HomePage(driver);
        Assert.assertTrue(homePage.isPageOpened(), "Home page should be opened");

        ElementsPage elementsPage = homePage.openElements();
        Assert.assertTrue(driver.getCurrentUrl().contains("elements"), "Should navigate to Elements section");
    }
}
