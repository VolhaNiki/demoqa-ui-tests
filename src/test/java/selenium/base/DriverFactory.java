package selenium.base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import selenium.utils.ConfigReader;

import java.util.Optional;

public class DriverFactory {
    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    private static final Logger logger = LoggerFactory.getLogger(DriverFactory.class);
    static String browser = Optional.ofNullable(System.getProperty("browser"))
            .orElse(ConfigReader.getBrowser())
            .toLowerCase();
    public static WebDriver createBrowser(){
        logger.info("Initializing browser: {}", browser);
        try {
            switch (browser.toLowerCase()){
                case "edge" -> {
                    EdgeOptions options = new EdgeOptions();
                    options.addArguments("--disable-blink-features=AutomationControlled");
                    WebDriverManager.edgedriver().setup();
                    driver.set(new EdgeDriver(options));
                }
                case "firefox" -> {
                    WebDriverManager.firefoxdriver().setup();
                    driver.set(new FirefoxDriver());
                }
                case "chrome" -> {
                    WebDriverManager.chromedriver().clearResolutionCache();
                    WebDriverManager.chromedriver().setup();
                    ChromeOptions options = new ChromeOptions();
                    options.addArguments("--disable-blink-features=AutomationControlled");
                    options.addArguments("--lang=en");
                    driver.set(new ChromeDriver(options));
                }
                default -> throw new IllegalArgumentException("Unsupported browser: " + browser);
            }
            driver.get().manage().window().maximize();
            logger.info("Browser {} initialized successfully", browser);
            return driver.get();
        } catch (Exception e){
            logger.error("Failed to initialize browser: {}", browser, e);
            throw new RuntimeException("Failed to initialize browser: " + browser, e);
        }
    }

    public static WebDriver getDriver(){
        return driver.get();
    }

    public static void quitDriver(){
        WebDriver webDriver = driver.get();
        if (webDriver != null){
            try {
                logger.info("Quitting browser");
                webDriver.quit();
            } catch (Exception ex){
                logger.error("Error while quitting driver: " + ex.getMessage());
            } finally {
                driver.remove();
            }
        }
    }
}
