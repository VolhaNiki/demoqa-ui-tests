package playwright;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.testng.Assert;
import org.testng.annotations.Test;

public class pwTest {
    @Test
    public void chromeTest(){
        try(Playwright playwright = Playwright.create()){
            Browser browser = playwright.chromium().launch(
                    new BrowserType.LaunchOptions()
                            .setHeadless(false)
            );
            Page page = browser.newPage();
            page.navigate("https://google.com");
            page.screenshot();

            Assert.assertTrue(page.title().toLowerCase().contains("google"));
        }
    }

    @Test
    public void firefoxTest(){

        Page page = Playwright
                .create()
                .firefox()
                .launch(new BrowserType.LaunchOptions().setHeadless(false))
                .newPage();
        page.navigate("https://google.com");
        page.screenshot();

        Assert.assertTrue(page.title().toLowerCase().contains("google"));
    }

//    public String browsersTest(String browser){
//        Browser browserType = new B
//        Page page = Playwright
//                .create().
//                .br
//                .launch(new BrowserType.LaunchOptions().setHeadless(false))
//                .newPage();
//        page.navigate("https://google.com");
//        page.screenshot();
//    }

}
