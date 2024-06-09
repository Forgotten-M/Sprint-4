package ru.yandex.praktikum;

import ru.yandex.praktikum.constants.Url;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public abstract class BaseTest {
    WebDriver driver;


    @Before
    public void setUp() {
        //выбор браузера. неиспользуемый браузер закомментировать
//        WebDriverManager.chromedriver().setup();
//        driver = new ChromeDriver();
        WebDriverManager.firefoxdriver().setup();
       driver = new FirefoxDriver();
        driver.get(Url.URL_SCOOTER);
    }

    //закрыть браузер после тестов
    @After
    public void cleanUp() {
        driver.quit();
    }
}
