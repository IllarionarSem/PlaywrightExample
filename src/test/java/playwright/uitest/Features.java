package playwright.uitest;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.Proxy;
import org.testng.annotations.Test;

import java.nio.file.Paths;

public class Features extends BaseTest {

    @Test
    public void network() {

        //set
        BrowserContext context = browser.newContext(new Browser.NewContextOptions()
                .setHttpCredentials("user", "pswd"));
        Page page = context.newPage();

        //proxy configuration
        Browser proxyBrowser = playwright.chromium().launch(new BrowserType.LaunchOptions()
                .setProxy(new Proxy("")
                        .setUsername("username")
                        .setPassword("pswd")));

        //record network activity as an http activity archive file HAR
        BrowserContext httpActivity = browser.newContext(new Browser.NewContextOptions()
                .setRecordHarPath(Paths.get("")));

        // websocket
        page.onWebSocket(ws -> {
            ws.onFrameSent(frameData -> System.out.println(frameData.text()));
            ws.onFrameReceived(frameData -> System.out.println(frameData.text()));
            ws.onClose(ws1 -> System.out.println("WebSocket closed"));
        });
    }
}
