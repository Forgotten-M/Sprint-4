package ru.yandex.praktikum;

import org.junit.Test;
import ru.yandex.praktikum.pages.MainPage;
import static org.junit.Assert.*;

public class LogoTest extends BaseTest{

    //Тестирование нажатия на логотип "Самоката"

    @Test
    public void clickLogoScooterTest() {
        MainPage mainPage = new MainPage(driver);
        String expectedUrl = driver.getCurrentUrl();
        mainPage.logoScooterClick();
        assertEquals("Пользователь не попал на главную страницу 'Самоката'", expectedUrl, driver.getCurrentUrl());
    }
    //Тестирование нажатия на логотип Яндекса. Тест должен выдать ошибку, так как идёт переадресация не на https://yandex.ru/
    @Test
    public void clickLogoYandexTest() {
        MainPage mainPage = new MainPage(driver);
        String expectedUrl = driver.findElement(mainPage.getHeaderLogoYandex()).getAttribute("href");
        mainPage.logoYandexClick();
        assertEquals("Пользователь не попал на главную страницу Яндекс", expectedUrl, driver.getCurrentUrl());
    }
}
