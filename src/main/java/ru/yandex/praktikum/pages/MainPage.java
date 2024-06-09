package ru.yandex.praktikum.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainPage {
    private final WebDriver driver;
    private final String question = "accordion__heading-";
    private final String answer = "accordion__panel-";

    //логотип "Самоката"
    private final By logoScooter = By.xpath("//img[@alt='Scooter']");
    //логотип Яндекса
    private final By logoYandex = By.xpath("//a[@class='Header_LogoYandex__3TSOI']");

    //верхняя кнопка "Заказать"
    private final By headerOrderButton = By.xpath("//*[@class=\"Header_Nav__AGCXC\"]/button[@class=\"Button_Button__ra12g\"]");
    //нижняя кнопка "Заказать"
    private final By howItOrderButton = By.xpath("//div[@class=\"Home_FinishButton__1_cWm\"]/button");
    //блок вопросов
    private final By faqBlock = By.className("Home_FourPart__1uthg");
    //блок как это работает
    private final By howItOrder = By.className("Home_FinishButton__1_cWm");

    //кнопка "Статус заказа"
    private final By orderStatus = By.xpath("//button[text()='Статус заказа']");
    //поле ввода "Введите номер заказа"
    private final By orderNumberInput = By.xpath("//input[@placeholder='Введите номер заказа']");
    //кнопка "Go!"
    private final By goButton = By.xpath("//button[text()='Go!']");
    //сообщение "Такого заказа нет"
    private final By notFoundImage = By.xpath("//img[@alt='Not found']");

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    public MainPage accordeonClick(By question) {
        this.driver.findElement(question).click();
        return this;
    }

    public String getAnswerText(By answer) {
        return this.driver.findElement(answer).getText();
    }

    public MainPage scrollFaq() {
        WebElement element = this.driver.findElement(this.faqBlock);
        ((JavascriptExecutor) this.driver).executeScript("arguments[0].scrollIntoView();", new Object[]{element});
        return this;
    }

    public MainPage scrollHowItOrder() {
        WebElement element = this.driver.findElement(this.howItOrder);
        ((JavascriptExecutor) this.driver).executeScript("arguments[0].scrollIntoView();", new Object[]{element});
        return this;
    }

    public MainPage waitForLoadHomePage() {
        (new WebDriverWait(this.driver, Duration.ofSeconds(15L))).until((driver) -> {
            return driver.findElement(this.headerOrderButton).getText() != null && !driver.findElement(this.headerOrderButton).getText().isEmpty();
        });
        return this;
    }

    public MainPage waitForLoadHowItOrder() {
        (new WebDriverWait(this.driver, Duration.ofSeconds(15L))).until((driver) -> {
            return driver.findElement(this.howItOrder).getText() != null && !driver.findElement(this.howItOrder).getText().isEmpty();
        });
        return this;
    }

    public MainPage orderButtonClick(String chooseButton) {
        if (chooseButton.equals("header")) {
            waitForLoadHomePage();
            driver.findElement(headerOrderButton).click();
        } else if (chooseButton.equals("howItOrder")) {
            scrollHowItOrder();
            waitForLoadHowItOrder();
            driver.findElement(howItOrderButton).click();
        }

        return this;
    }

    public By getQuestion(int numQuestion) {
        return By.id(String.format(question + "%d", numQuestion));
    }

    public By getAnswer(int numAnswer) {
        return By.id(String.format(answer + "%d", numAnswer));
    }

    //Методы для поиска несуществующего заказа
    public MainPage clickOrderStatusButton() {
        this.driver.findElement(orderStatus).click();
        return this;
    }

    public MainPage enterOrderNumber(String orderNumber) {
        this.driver.findElement(orderNumberInput).sendKeys(orderNumber);
        return this;
    }

    public MainPage clickGoButton() {
        this.driver.findElement(goButton).click();
        return this;
    }
    public boolean notFoundImageIsDisplayed() {
        return this.driver.findElement(notFoundImage).isDisplayed();
    }

    //Метод для клика по логотипу "Самокат"
    public MainPage logoScooterClick() {
        this.driver.findElement(logoScooter).click();
        return this;
    }

    //Метод для клика по логотипу Яндекс
    public void logoYandexClick() {
        driver.findElement(logoYandex).click();
        for (String windowHandle : driver.getWindowHandles()) {
            if (!windowHandle.equals(driver.getWindowHandle())) {
                //Перейти на открытую вкладку
                driver.switchTo().window(windowHandle);
                break;
            }
        }
    }

    //Получить локатор заголовка Яндекс
    public By getHeaderLogoYandex() {
        return logoYandex;
    }
}