package basicTests;

import com.github.javafaker.Faker;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import static utils.RandomUtils.*;

public class BasicPage {
    String randomMessage = getRandomStringText(22);

    Faker faker = new Faker();
    String FIO = faker.name().fullName(),
            userEmail = faker.internet().emailAddress(),
            userMobile = faker.phoneNumber().subscriberNumber(10),
            document = "document.doc";


    @Step ("Открыть главную старницу")
    public void openMainPage() {
        open("https://iac.spb.ru/");
        $(".header-page").shouldHave(text("Государственное унитарное предприятие, работающее в области информатизации и информационного обеспечения органов государственной власти Санкт-Петербурга и других организаций, а также предоставления услуг в сфере создания и использования современных информационных и телекоммуникационных систем, средств и технологий "));
    }

    @Step ("Открыть страницу с формой отправки письма")
    public void openSendMailPage() {
        openMainPage();
        $("#idtopmenu").click();
        $(byText("Написать нам")).click();
        $(".step-b3.text-center").shouldHave(text("Напишите нам"));
    }

    @Step ("Выбрать тип отправления")
    public void selectType() {
        $(".b24-form-field-list").click();
        $(byText("Запрос на практику")).click();
    }

    @Step ("Ввести ФИО")
    public void setFIO() {
        $((".b24-form-field-string"),0).click();
        getFocusedElement().sendKeys(FIO);
    }

    @Step ("Ввести email")
    public void setEmail() {
        $(byName("email")).setValue(userEmail);
    }

    @Step ("Ввести телефон")
    public void setMobile() {
        $(byName("phone")).setValue(userMobile);
    }

    @Step ("Напечатать тектс")
    public void typeText() {
        $((".b24-form-field-string"),1).scrollTo();
        $(".b24-form-control-text").click();
        getFocusedElement().sendKeys(randomMessage);
    }

    @Step ("Загрузить файл")
    public void uploadFile() {
        $(byText("Выбрать файл")).uploadFromClasspath("doc/" + document);
    }

    @Step ("Клик Отправить")
    public void pushTheButton() {
        $(withText("Отправить")).click();
    }
}
