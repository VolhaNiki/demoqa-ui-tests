package selenium.cucumber.steps;

import selenium.base.DriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

public class Hooks {
    private final static Logger logger = LogManager.getLogger(Hooks.class);
    private WebDriver driver;

    @Before
    public void setUp(){
        logger.info("Cucumber Before hook: Initializing browser");
        driver = DriverFactory.createBrowser();
    }

    @After
    public void tearDown(){
        logger.info("Cucumber After hook: Quitting browser");
        DriverFactory.quitDriver();
    }

    public WebDriver getDriver() {
        return driver;
    }
}
