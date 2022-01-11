package basicTests;

import com.github.javafaker.Faker;
import io.qameta.allure.Step;
import org.junit.jupiter.api.*;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;

public class BasicPositiveTests extends TestBase {

    BasicPage BasicPage = new BasicPage();


    @Test
    @Tags({@Tag("positive"), @Tag("ready")})
    public void fillFormTest() {
        BasicPage.openMainPage();
        BasicPage.openSendMailPage();
        BasicPage.selectType();
        BasicPage.setFIO();
        BasicPage.setEmail();
        BasicPage.setMobile();
        BasicPage.typeText();
//        BasicPage.uploadFile();
        BasicPage.pushTheButton();
    }

    @Test
    @Tags({@Tag("positive"), @Tag("ready")})
    public void negativeFillFormTest() {

        step("Открыть главную страницу", () -> {
            open(BASE_URL);
        });

        step("Открыть страницу с формой отправки письма", () -> {
            step("Клик 'О центре'", () ->
                    $("#idtopmenu").click());
            step("Клик \"Написать нам\" ", () ->
                    $(byText("Написать нам")).click());
            step("Проверить загрузку формы", () ->
                    $(".step-b3.text-center").shouldHave(text("Напишите нам")));
        });

        step("Нажать 'Отправить'", () -> {
            $(withText("Отправить")).click();
        });

        step("Проверить сообщения об ошибках", () -> {
            step("Проверить сообщение в поле \"Тип обращения\" ", () ->
                    $((".b24-form-control-alert-message"), 0).click());
            step("Проверить сообщение в поле \"ФИО\"'", () ->
                    $((".b24-form-control-alert-message"), 1).click());
            step("Проверить сообщение в поле \"email\" ", () ->
                    $((".b24-form-control-alert-message"), 2).click());
            step("Проверить сообщение в поле \"Текст сообщения\" ", () ->
                    $((".b24-form-control-alert-message"), 5).click());
        });
    }


    @Test
    @Tags({@Tag("positive"), @Tag("ready")})
    public void findProjectTest() {

        step("Открыть главную страницу", () -> {
            open(BASE_URL);
        });
        step("Открыть страницу \"Проекты и системы\" ", () -> {
            $(byText("Проекты и системы")).click();
        });
        step("Произвести поиск по слову \"университет\"", () -> {
            $((".form-control"), 0).setValue("университет").pressEnter();
        });
        step("Проверить строку выдачи", () -> {
            $(".search-advanced-result").shouldHave(text("университет"));
        });
    }


        @Test
        @Tags({@Tag("positive"), @Tag("ready")})
        public void searchJobTest() {

            step("Открыть главную страницу", () -> {
                open(BASE_URL);
            });
            step("Открыть страницу \"Вакансии\" ", () -> {
                $(byText("Вакансии")).click();
            });
            step("Перейти в раздел \"Вакансии\"", () -> {
                $(".jobs").click();
            });
            step("", () -> {
                $((".step-4"),1).shouldHave(text("Тестировщик"));
            });
            $(withText("Тестировщик/QA-инженер")).click();

    }
}
