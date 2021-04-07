package testNum;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class HomeWorkYandex {
    private static final Logger logger = LogManager.getLogger(SampleTest.class);
    WebDriver dr = new ChromeDriver();

    @Test(description = "homeWork YANDEX")
    public void getYandex() {
        WebDriverManager.chromedriver().setup();
        logger.info("Переход на сайт яндекса");
        dr.get("https://market.yandex.ru/catalog--smartfony-v-samare/16814639/list?hid=91491&glfilter=16816262%3A16816264&onstock=1&local-offers-first=1");
        logger.info("Поиск по тексту xiaomi");
        dr.findElement(By.xpath("//*[@id=\"7893318-suggester\"]")).sendKeys("xiaomi");
        logger.info("Выбор из списка производителей - xiaomi");
        dr.findElement(By.xpath("//*[@id=\"search-prepack\"]/div/div[3]/div/div/div[2]/div[2]/div/div/fieldset/ul/li/div/a/label/div/span")).click();
        logger.info("Сортировка по цене");
        dr.findElement(By.xpath("/html/body/div[2]/div[3]/div[3]/div[3]/div/div[1]/button[2]")).click();
        logger.info("Выбор первого телефона");
        dr.findElement(By.xpath("/html/body/div[2]/div[3]/div[3]/div[4]/div/div[1]/div/div/div/article[1]/div[2]/div[1]/div")).click();
        logger.info("Выбор второго телефона");
        dr.findElement(By.xpath("/html/body/div[2]/div[3]/div[3]/div[4]/div/div[1]/div/div/div/article[2]/div[2]/div[1]/div")).click();
        logger.info("Переход к сравнению");
        dr.findElement(By.xpath("/html/body/div[2]/div[3]/div[6]/div/div/div[3]/a")).click();
    }

}
