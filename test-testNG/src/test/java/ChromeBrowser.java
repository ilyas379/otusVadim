import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ChromeBrowser implements Browser {

    public static WebDriver driver;
    private Logger logger = LogManager.getLogger(ChromeBrowser.class);

    @Override
    public void create(String url) {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        logger.info("Драйвер поднят");
        driver.get(url);
        logger.info("Открыта страница сайта " + url);

    }

    @Override
    public void setDown() {
        if (driver != null) {
            driver.quit();
        }
        logger.info("Программа закончила работать");
    }
}
