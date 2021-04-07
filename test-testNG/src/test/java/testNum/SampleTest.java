package testNum;

import browser.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.*;
import java.util.concurrent.TimeUnit;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;


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

    @Test
    public void defaultContent() {
        WebDriverManager.chromedriver().setup();
        d = new ChromeDriver();
        d.get(ya);
        d.switchTo().activeElement().sendKeys("дробышевский" + Keys.ENTER);
//        d.findElement(By.xpath("/html/body/table/tbody/tr[2]/td/form/div[2]/button")).click();
    }

    @Test
    public void backForwardRefresh() {
        WebDriverManager.chromedriver().setup();
        d = new ChromeDriver();

        logger.info("go to: {}", ya);
        d.get(ya);
        logger.info("current page: {}", d.getTitle());

        logger.info("go to: {}", url);
        d.get(url);
        logger.info("current page: {}", d.getTitle());

        logger.info("go back");
        d.navigate().back();
        logger.info("current page: {}", d.getTitle());

        logger.info("go forward");
        d.navigate().forward();
        logger.info("current page: {}", d.getTitle());

        logger.info("refresh");
        d.navigate().refresh();
        logger.info("current page: {}", d.getTitle());
    }

    @Test
    public void gosUslugi() {
        WebDriverManager.chromedriver().setup();
        d = new ChromeDriver();
        String gu = "https://lk.fss.ru/recipient/#/eln/card/910038795854";

        logger.info("go to: {}", gu);
        d.get(gu);

//        Map<new Cookie(name), new Cookie(value)> kukies = d.manage().getCookies();
        Cookie cookie1 = new Cookie("JSESSIONID", "BB7E0A15FA5489BC498425A3FB02B462");
        Cookie cookie2 = new Cookie("oiosaml-fragment", "%23/");
        Cookie cookie3 = new Cookie("SERVERID", "server02");
        Cookie cookie4 = new Cookie("_idp_authn_lc_key", "cc2c6448-02e9-4d84-b3f9-01285521909a");
        Cookie cookie5 = new Cookie("SCS", "mJsGGi7u67MMZhUW2bCOo6KaWBvd69eMXmmg_0GTlWCXJa3ZX6Tc9A0QGWlspfHfQ97PttyQ-jYi2qhnEGXISvVQVABVlA3xvgM3sU5142jBlamU_DoJ9kblrprKqyxQpmxnqCdPeX6jXLlAHvH_nB7jOcgaKxqGujDN-xG_d_1DLBrTZP7XAnVuSewRmkSqi5Kfi9xCQIkGb0aHKOR84zNMSOQz9CDFG1A_a3JKEGdFny5_Dz6nVmHR6WDnphkmjG6IsIeZglHxEe-q6sofaw88V5AzX-w6PdoKhlrfadTtI9f0fRaJVlxHnP1rfOJvuEhaMpcazUxSjp8thJMyDN6jyVSgYzWNI7GNfDUkCFw|MTYwMjY5NjA5Mg|U0gxQVMxMjhDQkM|avVNhqGtuUsvuQ2xWNqCqg|no5xi2cf3aV6wZs-drwMzLfeNGw");
        Cookie cookie6 = new Cookie("idp_id", "647a7f09f9549cba6fc801d73ebe593a");
        Cookie cookie7 = new Cookie("usi_portal", "\n" +
                "\t\trBBoGV+HM5x7TQNiffDhAg==\n");
        Cookie cookie8 = new Cookie("ctx_id", "ffffffffaf18760845525d5f4f58455e445a4a423660");
        Cookie cookie9 = new Cookie("timezone", "3");
        Cookie cookie10 = new Cookie("userSelectedLanguage", "ru");
        Cookie cookie11 = new Cookie("_ym_uid", "1602696094124794551");
        Cookie cookie12 = new Cookie("_ym_d", "1602696094");
        Cookie cookie13 = new Cookie("JSESSIONID", "8202c3466fb0a32fca65966b71f8");
        Cookie cookie14 = new Cookie("_ym_isad", "2");
        Cookie cookie15 = new Cookie("_ym_visorc_52235404", "b");
        Cookie cookie16 = new Cookie("_idp_authn_id", "phone%3A%252B79277519903");
        Cookie cookie17 = new Cookie("login_value", "+7%28927%297519903");
        d.manage().addCookie(cookie1);
        d.manage().addCookie(cookie2);
        d.manage().addCookie(cookie3);
        d.manage().addCookie(cookie4);
        d.manage().addCookie(cookie5);
        d.manage().addCookie(cookie6);
        d.manage().addCookie(cookie7);
        d.manage().addCookie(cookie8);
        d.manage().addCookie(cookie9);
        d.manage().addCookie(cookie10);
        d.manage().addCookie(cookie11);
        d.manage().addCookie(cookie12);
        d.manage().addCookie(cookie13);
        d.manage().addCookie(cookie14);
        d.manage().addCookie(cookie15);
        d.manage().addCookie(cookie16);
        d.manage().addCookie(cookie17);
        System.out.println(d.manage().getCookies());
    }

    @Test(description = "run site and use web elements")
    public void useWebElement() {
        WebDriverManager.chromedriver().setup();
        d = new ChromeDriver();
        String otus = "https://otus.ru";
        d.get(otus);
        WebElement element = d.findElement(By.xpath("//a[@title='Отзывы']"));
        d.navigate().refresh();
        element.click();

    }

    @Test(description = "poluchenye atributov google.com, dumaet chto ya mashina((")
    public void getYa() {
        WebDriverManager.chromedriver().setup();
        d = new ChromeDriver();
        String google = "https://google.com";
        d.get(google);
        WebElement input = d.findElement(By.xpath("//input[@name='q']"));
        Assert.assertTrue(input.isDisplayed());
        System.out.println(input.getAttribute("class"));
    }


    @Test(description = "poisk knopki prinat' uchastie")
    public void knopka() {
        WebDriverManager.chromedriver().setup();
        d = new ChromeDriver();
        String otus = "https://otus.ru";
        d.get(otus);
        WebElement vybrKurs = d.findElement(By.xpath("/html/body/div[1]/div/div[2]/div[3]/div/a/div[2]/div"));
        System.out.println(d.findElement(By.xpath("/html/body/div[1]/div/div[2]/div[3]/div/a/div[2]/div")));
        System.out.println(vybrKurs.isDisplayed());
        System.out.println(vybrKurs.isEnabled());
        vybrKurs.click();
    }

    @Test(description = "получение информации из поисковой строки")
    public void getInfoSearchString() {
        WebDriverManager.chromedriver().setup();
        d = new ChromeDriver();
        d.get(ya);
        d.switchTo().activeElement().sendKeys("дробышевский");
        System.out.println(d.switchTo().activeElement().getAttribute("value"));
    }

    @Test(description = "получить текущее время два раза и сравнить значения")
    public void getTime() {
        String bootStrap = "https://ng-bootstrap.github.io/#/components/alert/examples";
        WebDriverManager.chromedriver().setup();
        d = new ChromeDriver();
        d.get(bootStrap);
        WebElement changeMessage = d.findElement(By.xpath("/html/body/ngbd-app/div/component-wrapper/div/div/div[2]/section/div[1]/ng-component/ngbd-widget-demo[3]/div/div/div/ngbd-alert-selfclosing/p[3]/button"));
        changeMessage.click();
        WebElement time = d.findElement(By.xpath("//div[@class='card-body']//ngb-alert[contains(text(), 'Message successfully changed')]"));
        ExpectedConditions.visibilityOf(time);
        String useTime = time.getText();
        System.out.println(useTime);

        ExpectedConditions.not(visibilityOf(time));

        changeMessage.click();
        WebElement time2 = d.findElement(By.xpath("//div[@class='card-body']//ngb-alert[contains(text(), 'Message successfully changed')]"));
        ExpectedConditions.visibilityOf(time2);
        String useTime2 = time2.getText();
        System.out.println(useTime2);

        Assert.assertNotEquals(useTime, useTime2);
    }

    @Test(description = "homeWork YANDEX")
    public void getYandex() {
        WebDriverManager.chromedriver().setup();
        d = new ChromeDriver();
        logger.info("Переход на сайт яндекса");
        d.get("https://market.yandex.ru/catalog--smartfony-v-samare/16814639/list?hid=91491&glfilter=16816262%3A16816264&onstock=1&local-offers-first=1");
        logger.info("Нажать на кнопку \"показать все\"");
        d.findElement(By.xpath("//*[@id=\"search-prepack\"]/div/div[3]/div/div/div[2]/div[3]/div/div/fieldset/footer/button")).click();
        logger.info("Поиск по тексту xiaomi");
        d.findElement(By.xpath("//*[@id=\"7893318-suggester\"]")).sendKeys("xiaomi");
        logger.info("Выбор из списка производителей - xiaomi");
        d.findElement(By.xpath("//*[@id=\"search-prepack\"]/div/div[3]/div/div/div[2]/div[2]/div/div/fieldset/ul/li/div/a/label/div/span")).click();
        logger.info("Сортировка по цене");
        d.findElement(By.xpath("/html/body/div[2]/div[3]/div[3]/div[3]/div/div[1]/button[2]")).click();
        logger.info("Выбор первого телефона");
        d.findElement(By.xpath("/html/body/div[2]/div[3]/div[3]/div[4]/div/div[1]/div/div/div/article[1]/div[2]/div[1]/div")).click();
        logger.info("Выбор второго телефона");
        d.findElement(By.xpath("/html/body/div[2]/div[3]/div[3]/div[4]/div/div[1]/div/div/div/article[2]/div[2]/div[1]/div")).click();
        logger.info("Переход к сравнению");
        d.findElement(By.xpath("/html/body/div[2]/div[3]/div[6]/div/div/div[3]/a")).click();
        //незаконченный тест (забанили в яндексе)
    }

    @Test(description = "allerts")
    public void getAllert(){
        WebDriverManager.chromedriver().setup();
        d = new ChromeDriver();

        d.get("https://htmlweb.ru/java/js1.php");
        Alert alert = d.switchTo().alert();
        System.out.println(alert.getText());
        alert.accept();
        d.findElement(By.cssSelector("code"));
        d.quit();
    }


}