package ru.yandex.praktikum.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

//Страница "Для кого самокат"
public class OrderPage {
    private final WebDriver driver;
    //поле ввода "Имя"
    private final By nameField = By.xpath("//input[@class=\"Input_Input__1iN_Z Input_Responsible__1jDKN\" and @placeholder=\"* Имя\"]");
    //поле ввода "Фамилия"
    private final By surnameField = By.xpath("//input[@class=\"Input_Input__1iN_Z Input_Responsible__1jDKN\" and @placeholder=\"* Фамилия\"]");
    //поле ввода "Адрес"
    private final By adressField = By.xpath("//input[@class=\"Input_Input__1iN_Z Input_Responsible__1jDKN\" and @placeholder=\"* Адрес: куда привезти заказ\"]");
    //поле выбора "Станция метро"
    private final By subwayStationList = By.xpath("//input[@class=\"select-search__input\"]");
    private final String subwayName = ".//button[@value='%s']";
    //поле ввода "Телефон: на него позвонит курьер"
    private final By phoneNumberField = By.xpath(".//input[contains(@placeholder, 'Телефон')]");
    //кнопка "Далее"
    private final By nextButton = By.xpath("//button[@class=\"Button_Button__ra12g Button_Middle__1CSJM\"]");


    public OrderPage(WebDriver driver) {
        this.driver = driver;
    }

    public OrderPage nextButtonClick() {
        driver.findElement(nextButton).click();
        return this;
    }

    public OrderPage nameFieldClearAndWrite(String name) {
        driver.findElement(nameField).clear();
        driver.findElement(nameField).sendKeys(name);
        return this;
    }

    public OrderPage surnameFieldClearAndWrite(String surname) {
        driver.findElement(surnameField).clear();
        driver.findElement(surnameField).sendKeys(surname);
        return this;
    }

    public OrderPage adressFieldClearAndWrite(String adress) {
        driver.findElement(adressField).clear();
        driver.findElement(adressField).sendKeys(adress);
        return this;
    }

    public OrderPage phoneNumberFieldClearAndWrite(String phoneNumber) {
        driver.findElement(phoneNumberField).clear();
        driver.findElement(phoneNumberField).sendKeys(phoneNumber);
        return this;
    }


    public OrderPage subwayStationListChoose(int stateNumber) {
        driver.findElement(subwayStationList).click();
        By newSubway = By.xpath(String.format(subwayName, stateNumber));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(newSubway));
        driver.findElement(newSubway).click();
        return this;
    }


}