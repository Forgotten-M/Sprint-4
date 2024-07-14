package ru.yandex.praktikum;

import org.junit.Test;
import static org.junit.Assert.assertTrue;
import ru.yandex.praktikum.pages.MainPage;

public class IncorrectNumberTest extends BaseTest{

    //проверка страницы при неправильном вводе номера заказа
    @Test
    public void orderNotFound() {

        MainPage mainPage = new MainPage(driver);
        mainPage.clickOrderStatusButton();
        mainPage.enterOrderNumber("тысяча двеннадцать");
        mainPage.clickGoButton();
        assertTrue(mainPage.notFoundImageIsDisplayed());
    }
}
