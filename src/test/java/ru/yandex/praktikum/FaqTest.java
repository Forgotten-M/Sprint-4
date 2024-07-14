package ru.yandex.praktikum;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import ru.yandex.praktikum.pages.MainPage;

import static ru.yandex.praktikum.constants.AnswersConstants.*;

@RunWith(Parameterized.class)
public class FaqTest extends BaseTest {
    private final int numQuestion;
    private final int numAnswer;
    private final String expected;

    public FaqTest(int numQuestion, int numAnswer, String expected) {
        this.numQuestion = numQuestion;
        this.numAnswer = numAnswer;
        this.expected = expected;
    }

    @Parameters
    public static Object[][] getParameters() {
        return new Object[][]{
                {0, 0, TEXT_ANSWER_0},
                {1, 1, TEXT_ANSWER_1},
                {2, 2, TEXT_ANSWER_2},
                {3, 3, TEXT_ANSWER_3},
                {4, 4, TEXT_ANSWER_4},
                {5, 5, TEXT_ANSWER_5},
                {6, 6, TEXT_ANSWER_6},
                {7, 7, TEXT_ANSWER_7},
        };
    }

    @Test
    public void equalsAnswerQuestion() {
        MainPage mainPage = new MainPage(this.driver);
        String answerTextNow = mainPage
                .waitForLoadHomePage()
                .scrollFaq()
                .accordeonClick(mainPage.getQuestion(this.numQuestion))
                .getAnswerText(mainPage.getAnswer(this.numAnswer));
        Assert.assertEquals("Текст не соответствует", this.expected, answerTextNow);
    }
}