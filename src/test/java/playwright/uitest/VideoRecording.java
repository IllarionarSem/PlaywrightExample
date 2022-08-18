package playwright.uitest;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.Page;
import org.testng.annotations.Test;

import java.nio.file.Paths;

public class VideoRecording extends BaseTest {

    @Test
    public void videoRecordingTest() {

        context = browser.newContext(new Browser.NewContextOptions()
                .setRecordVideoDir(Paths.get("video/"))
                .setRecordVideoSize(640, 480));

        Page page = context.newPage();
        page.navigate("https://en.wikipedia.org/wiki/JPEG");
        String videoPath = page.video().path().toString();
        System.out.println(videoPath);
    }
}
