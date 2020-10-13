import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;


public class SampleTest {
    WebDriver d;
    String url = "https://otus.com";
    String ya = "https://ya.ru";
    private static final Logger logger = LogManager.getLogger(SampleTest.class);

    public static BrowserFactory createBrowserByName(BrowserTypes type) {
        if (type.name().equalsIgnoreCase("chrome")) {
            return new ChromeBrowserFactory();
        } else if (type.name().equalsIgnoreCase("firefox")) {
            return new FireFoxBrowserFactory();
        } else {
            throw new RuntimeException(type + " is unknown speciality.");
        }
    }

    @Test
    public void openPage() {
        BrowserFactory browserFactory = createBrowserByName(BrowserTypes.CHROME);
        Browser browser = browserFactory.createBrowser();
        browser.create(url);
        browser.setDown();
    }


    @Test
    public void setCookie() {
        WebDriverManager.chromedriver().setup();
        d = new ChromeDriver();

        Cookie cookie = new Cookie("Otus5", "value5");

        d.get(url);
        d.manage().addCookie(new Cookie("Otus1", "value1"));
        d.manage().addCookie(new Cookie("Otus2", "value2"));
        d.manage().addCookie(new Cookie("Otus3", "value3"));
        d.manage().addCookie(new Cookie("Otus4", "value4"));
        d.manage().addCookie(cookie);

        System.out.println(d.manage().getCookies());
        System.out.println(d.manage().getCookieNamed("Otus1"));
        d.manage().deleteCookieNamed("Otus3");
        d.manage().deleteCookie(cookie);
        d.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
    }

    @Test
    public void implicitlyWait() {
        WebDriverManager.chromedriver().setup();
        d = new ChromeDriver();
        d.get(url);
        d.manage().timeouts().pageLoadTimeout(0, TimeUnit.SECONDS);
        d.close();
    }

    @Test
    public void fullScreen() {
        WebDriverManager.chromedriver().setup();
        d = new ChromeDriver();
        d.get(url);
        System.out.println(d.manage().window().getPosition());
        d.manage().window().setSize(new org.openqa.selenium.Dimension(800, 600));
        d.manage().window().setPosition(new Point(100, 100));
        System.out.println(d.manage().window().getPosition());
        d.close();
    }

    @Test
    public void headlessChrome() {
        WebDriverManager.chromedriver().setup();
        d = new ChromeDriver();
        d.get(ya);
        ChromeOptions options = new ChromeOptions();
        options.addArguments("headless");
        d.close();
    }

    @Test
    public void implicitWait() {
        WebDriverManager.chromedriver().setup();
        d = new ChromeDriver();
        d.get(ya);
        WebElement input = d.findElement(By.xpath("//*[@id='text']"));
        input.sendKeys("Чемоданова Алена Юрьевна");
        WebElement search = d.findElement(By.xpath("/html/body/table/tbody/tr[2]/td/form/div[2]/button"));
        search.click();
        WebElement vkSearch = d.findElement(By.xpath("//*[@id=\'search-result\']/li[2]/div/h2/a"));
        new WebDriverWait(d, 10).until(ExpectedConditions.elementToBeClickable(vkSearch));
        vkSearch.click();
        logger.info("Current URL: " + d.getCurrentUrl());
        logger.info("title: " + d.getTitle());

        Set<String> handles = d.getWindowHandles();
        List<String> tabs = new ArrayList<>(handles);

        tabs.forEach(logger::info);

        String currentTab = d.getWindowHandle();
        logger.info("Current tab: {}" + currentTab);

        tabs.remove(currentTab);
        String anotherTab = tabs.get(0);
        logger.info("Another: {}", anotherTab);
        logger.info("Switch");
        d.switchTo().window(anotherTab);
        logger.info("Current: {}", d.getWindowHandle());
        logger.info("Current URL: " + d.getCurrentUrl());
        logger.info("title: " + d.getTitle());
        d.switchTo().window(currentTab);
        d.close();
        d.quit();
    }
}
