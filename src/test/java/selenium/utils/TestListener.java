package selenium.utils;

import selenium.base.DriverFactory;
import io.qameta.allure.Attachment;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;

/**
 * TestNG listener to attach screenshots to Allure on failure.
 */
public class TestListener implements ITestListener {
    @Override
    public void onTestFailure(ITestResult result) {
        // take screenshot and attach
        attachScreenshot();
    }

        @Attachment(value = "screenshot", type = "image/png")
        public byte[] attachScreenshot() {
            try {
                var driver = DriverFactory.getDriver();
                if (driver == null) {
                    return new byte[0];
                }
                File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                byte[] content = FileUtils.readFileToByteArray(src);
                return content;
            } catch (IOException | ClassCastException e) {
                return new byte[0];
            }
        }
}
