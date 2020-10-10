import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class SampleTest {

    public static BrowserFactory createBrowserByName(BrowserTypes type) {
        if (type.name().equalsIgnoreCase("chrome")){
            return new ChromeBrowserFactory();
        } else if (type.name().equalsIgnoreCase("firefox")){
            return new FireFoxBrowserFactory();
        } else {
            throw new RuntimeException(type + " is unknown speciality.");
        }
    }
    @Test
    public void openPage() {
        BrowserFactory browserFactory = createBrowserByName(BrowserTypes.CHROME);
        Browser browser = browserFactory.createBrowser();
        browser.create("https://otus.com/");
        browser.setDown();
    }

}
