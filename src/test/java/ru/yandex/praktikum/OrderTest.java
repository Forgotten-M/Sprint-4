package ru.yandex.praktikum;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.yandex.praktikum.pages.AboutRent;
import ru.yandex.praktikum.pages.ConfirmOrderPage;
import ru.yandex.praktikum.pages.MainPage;
import ru.yandex.praktikum.pages.OrderPage;

@RunWith(Parameterized.class)
public class OrderTest extends BaseTest {

    private final String button;
    private final String name;
    private final String surname;
    private final String adress;
    private final String phoneNumber;
    private final String date;
    private final String color;
    private final String comment;
    private final String expectedHeaderConfirmOrder = "Заказ оформлен";
    private final int timeRent;
    private final int subwayNumber;

    public OrderTest(String button, String name, String surname, String adress, String phoneNumber, int subwayNumber, String date, int timeRent, String color, String comment) {
        this.button = button;
        this.name = name;
        this.surname = surname;
        this.adress = adress;
        this.phoneNumber = phoneNumber;
        this.subwayNumber = subwayNumber;
        this.date = date;
        this.timeRent = timeRent;
        this.color = color;
        this.comment = comment;

    }

    @Parameterized.Parameters
    public static Object[][] getParameters() {
        return new Object[][]{
                {"header", "Елена", "Сергеева", "улица Ленина, 12, квартира 6", "+79996665151", 2, "10.06.2024", 2, "grey", "Поскорее!"},
                {"howItOrder", "Иван", "Петров", "Трудовая, 3", "+79294487676", 3, "10.06.2024", 5, "black", "Домофон не работает"},
                {"header", "Валентина", "Зайцева", "ул. Кузнечная, 18, кв. 5", "89997778181", 5, "10.06.2024", 7, "bothColor", "-"},
        };
    }

    @Test
    public void upButtonOrder() {

        MainPage mainPage = new MainPage(driver);
        mainPage.orderButtonClick(button);

        OrderPage orderPage = new OrderPage(driver);
        orderPage
                .nameFieldClearAndWrite(name)
                .surnameFieldClearAndWrite(surname)
                .adressFieldClearAndWrite(adress)
                .phoneNumberFieldClearAndWrite(phoneNumber)
                .subwayStationListChoose(subwayNumber)
                .nextButtonClick();

        AboutRent aboutRent = new AboutRent(driver);
        aboutRent
                .waitForLoadRentPage()
                .dateRentClearAndWrite(date)
                .chooseTimeRent(timeRent)
                .chooseColorRent(color)
                .commentWrite(comment)
                .rentButtonClick();

        ConfirmOrderPage confirmOrderPage = new ConfirmOrderPage(driver);
        confirmOrderPage
                .waitLoadConfirmModal()
                .orderConfirmButton();

        Assert.assertThat("Окно подтверждения заказа не открылось",confirmOrderPage.getHeaderModal(), CoreMatchers.containsString(this.expectedHeaderConfirmOrder));

    }
}