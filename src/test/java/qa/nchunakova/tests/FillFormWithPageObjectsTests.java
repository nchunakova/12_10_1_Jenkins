package qa.nchunakova.tests;

import com.codeborne.selenide.Configuration;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import qa.nchunakova.pages.RegistrationFormPage;

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

    @Test
    void fillFormTest(){

        registrationFormPage.openPage()
                .setFirstName(firstName) // reuse, cause RegistrationFormPage methods aren't void
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
