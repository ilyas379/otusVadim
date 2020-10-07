import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class FirstTest {


    WebDriver driver;

    @BeforeTest
    public void setUp() {
        System.out.println("Тесты начались");
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    @Test
    public void testOTUS() {

        driver.get("https://otus.ru");
        System.out.println("==========   " + driver.getTitle() + "   ===========");
    }

    @AfterTest
    public void quit() {
        if (driver != null) {
            driver.close();
            driver.quit();
        }
    }
}
