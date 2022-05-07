package qa.nchunakova.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.github.javafaker.Faker;
import io.qameta.allure.*;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qa.nchunakova.pages.RegistrationFormPage;

import java.nio.charset.StandardCharsets;

import static java.lang.String.format;

public class FillFormWithPageObjectsTests {

    RegistrationFormPage registrationFormPage = new RegistrationFormPage();

    Faker faker = new Faker();

    String firstName = faker.name().firstName(),
            lastName = faker.name().lastName(),
            expectedFullName = format("%s %s", firstName, lastName),
            userEmail = faker.internet().emailAddress(),
            gender = "Female",
            userNumber = faker.numerify("##########"),
            subject = "English",
            hobbies = "Reading",
            pictureLocation = "images/gymnocalycium-monvillei-mm814.jpg",
            expectedPictureName = pictureLocation.substring(7),
            currentAddress = faker.address().fullAddress(),
            state = "Rajasthan",
            city = "Jaiselmer",
            expectedFullLocationName = format("%s %s", state, city);

    @BeforeAll
    static void SetUp(){
//        Configuration.holdBrowserOpen = true;
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1980x1080";
    }

    @Owner("nchunakova")
    @Severity(SeverityLevel.BLOCKER)
    @Feature("Заполнение, отправка и проверка данных пользователя (feature)")
    @Story("Регистрация пользователей (user story)")
    @Test
    @DisplayName("Заполнение формы регистрации")
    void fillFormTest(){

        SelenideLogger.addListener("allure", new AllureSelenide());
        registrationFormPage.openPage()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setEmail(userEmail)
                .setGender(gender)
                .setMobile(userNumber)
                .setBirthDate("30", "July", "2008")
                .setSubjects(subject)
                .setHobbies(hobbies)
                .setMyPicture(pictureLocation)
                .setAddress(currentAddress)
                .setState(state)
                .setCity(city)
                .submitForm();

        //Assertions block
        registrationFormPage.checkResult("Student Name", expectedFullName)
                .checkResult("Student Email", userEmail)
                .checkResult("Gender", gender)
                .checkResult("Mobile", userNumber)
                .checkResult("Date of Birth", "30 July,2008")
                .checkResult("Subjects", subject)
                .checkResult("Hobbies", hobbies)
                .checkResult("Picture", expectedPictureName)
                .checkResult("Address", currentAddress)
                .checkResult("State and City", expectedFullLocationName)
                .closePage();
    }
}
