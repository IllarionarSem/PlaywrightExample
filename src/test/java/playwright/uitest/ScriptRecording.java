package playwright.uitest;

import org.testng.annotations.Test;

public class ScriptRecording extends BaseTest {

    private static final String URL = "https://en.wikipedia.org/wiki/JPEG";

    @Test
    public void scriptRecording() {
        page.navigate(URL);
        page.pause();
    }
}
