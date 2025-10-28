package base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import utils.ConfigReader;

public class DriverFactory {
    private static final ThreadLocal<WebDriver> THREAD_DRIVER = new ThreadLocal<>();
    private static final Logger logger = LogManager.getLogger(DriverFactory.class);

    public static WebDriver getDriver() {
        return THREAD_DRIVER.get();
    }

    public static void createDriver(){
        String browser = ConfigReader.getBrowser().toLowerCase();
        WebDriver driver;

        switch (browser){
            case "edge" -> {
                logger.info("Initializing Edge browser");
                EdgeOptions edgeOptions = new EdgeOptions();
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver(edgeOptions);
            }
            case "firefox" -> {
                logger.info("Initializing Firefox browser");
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver(firefoxOptions);
            }
            case "chrome" -> {
                logger.info("Initializing Chrome browser");
                ChromeOptions chromeOptions = new ChromeOptions();
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver(chromeOptions);
            }
            default -> {
                logger.info("Unsupported browser: {}. Falling back to Edge browser", browser);
                EdgeOptions edgeOptions = new EdgeOptions();
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver(edgeOptions);
            }
        }
        driver.manage().window().maximize();
        THREAD_DRIVER.set(driver);
    }

    public static void quitDriver() {
        WebDriver driver = THREAD_DRIVER.get();
        if (driver != null) {
            driver.quit();
            THREAD_DRIVER.remove();
            logger.info("Driver was removed");
        }
    }
}
