package base;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import pages.HomePage;
import utils.ConfigReader;
import utils.TestListener;

/**
 * Base test with setup and teardown.
 */
@Listeners({TestListener.class})
public class BaseTest {
    protected WebDriver driver;
    protected final Logger logger = LogManager.getLogger(this.getClass());

    @BeforeClass
    @Parameters({"browser"})
    public void beforeClass(@Optional String browser) {
        if (browser != null && !browser.isEmpty()) {
            System.setProperty("browser", browser);
        }
    }

    @BeforeMethod
    public void setUp() {
        driver = DriverFactory.createBrowser();
        driver.get(ConfigReader.getBaseUrl());
        logger.info("Opened URL: " + ConfigReader.getBaseUrl());
    }

    @AfterMethod
    public void tearDown() {
        logger.info("Quit driver");
        DriverFactory.quitDriver();
    }
}
