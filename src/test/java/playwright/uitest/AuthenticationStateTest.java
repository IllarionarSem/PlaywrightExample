package playwright.uitest;

import com.microsoft.playwright.*;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.nio.file.Paths;

public class AuthenticationStateTest {

    // provide your credentials to login on a web page, and then save it to a storage state to reuse it for tests

    String email = "";
    String password = "";
    Playwright playwright;
    Browser browser;
    BrowserContext context;
    Page page;
    String path = "src/test/resources/storage.json";
    String url = "";
    String loginUrl = "";

    @Test
    public void checkIsUserLoggedIn() {
        // user should be logged in
        page.navigate(url);
    }

    //etc 30+ tests

    @BeforeSuite
    public void beforeSuite() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setSlowMo(1000).setHeadless(false));
        context = browser.newContext();
        page = context.newPage();
        page.navigate(loginUrl);
        page.fill("input[name='login']", email);
        page.fill("input[name='password']", password);
        page.click("//input[@type='submit']");
        context.storageState(new BrowserContext.StorageStateOptions().setPath(Paths.get(path)));
        browser.close();
    }

    @BeforeTest
    public void beforeTest() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setSlowMo(1000).setHeadless(false));
        context = browser.newContext(new Browser.NewContextOptions().setStorageStatePath(Paths.get(path)));
        page = context.newPage();
    }
}
