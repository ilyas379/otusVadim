package browser;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class FireFoxBrowser implements Browser {
    protected static WebDriver driver;
    private Logger logger = LogManager.getLogger(FireFoxBrowser.class);

    @Override
    public void create(String url) {
        WebDriverManager.chromedriver().setup();
        driver = new FirefoxDriver();
        logger.info("Драйвер поднят");
        driver.get(url);
        logger.info("Открыта страница сайта " + url);
    }

    @Override
    public void setDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
