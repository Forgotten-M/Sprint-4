package ru.yandex.praktikum.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

//страница с модальным окном подтверждения заказа
public class ConfirmOrderPage {
    private final WebDriver driver;
    //хедер модального окна
    private final By headerModal = By.className("Order_ModalHeader__3FDaJ");
    //кнопка "Да" в окне подтверждения заказа
    private final By confirmButton = By.xpath(".//div[contains(@class, 'Order_Modal')]//button[text() = 'Да']");


    public ConfirmOrderPage(WebDriver driver) {
        this.driver = driver;
    }

    public ConfirmOrderPage waitLoadConfirmModal() {
        (new WebDriverWait(this.driver, Duration.ofSeconds(15L))).until((driver) -> {
            return driver.findElement(this.headerModal).getText() != null && !driver.findElement(this.headerModal).getText().isEmpty();
        });
        return this;
    }

    public ConfirmOrderPage orderConfirmButton() {
        driver.findElement(confirmButton).click();
        return this;
    }

    public String getHeaderModal() {
        return driver.findElement(headerModal).getText();
    }
}
