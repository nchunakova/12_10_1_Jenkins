package qa.nchunakova.pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import qa.nchunakova.pages.components.CalendarComponent;

import static com.codeborne.selenide.Condition.hidden;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class RegistrationFormPage {
    CalendarComponent calendar = new CalendarComponent();

    // locators
    SelenideElement firstNameInput = $("#firstName");
    String formHeaderText = "Student Registration Form";

    // actions
    public RegistrationFormPage openPage(){
        open("/automation-practice-form");
        $(".practice-form-wrapper").shouldHave(text(formHeaderText));
        executeJavaScript("$('footer').remove()");
        executeJavaScript("$('#fixedban').remove()");
        //Selenide.zoom(0.70);
        return this;
    }

    public RegistrationFormPage setFirstName(String value){
        firstNameInput.setValue(value);

        return this;
    }

// only for example, we don't clear anything in the test
//public RegistrationFormPage clearFirstName(String value){
//    firstNameInput.clear(); // thus we don't have to indicate css locator twice
//    return this;
//}

    public RegistrationFormPage setLastName(String value){
        $("#lastName").setValue(value);

        return this;
    }

    public RegistrationFormPage setEmail(String value){
        $("#userEmail").setValue(value);

        return this;
    }

    public RegistrationFormPage setGender(String value){
        $("#genterWrapper").$(byText(value)).click();

        return this;
    }

    public RegistrationFormPage setMobile(String value){
        $("#userNumber").setValue(value);

        return this;
    }

    public RegistrationFormPage setBirthDate(String day, String month, String year){
        $("#dateOfBirthInput").click();
        calendar.setDate(day, month, year);

        return this;
    }

    public RegistrationFormPage setSubjects(String value){
        $("#subjectsInput").setValue(value).pressEnter(); // todo edit subjects

        return this;
    }

    public RegistrationFormPage setHobbies(String value){
        $("#hobbiesWrapper").$(byText(value)).click();

        return this;
    }

    public RegistrationFormPage setMyPicture(String value){
        $("#uploadPicture").uploadFromClasspath(value); // file to upload from resources

        return this;
    }

    public RegistrationFormPage setAddress(String value){
        $("#currentAddress").setValue(value);

        return this;
    }

    public RegistrationFormPage setState(String value){
        $("#state").click();
        $(byText(value)).click();

        return this;
    }

    public RegistrationFormPage setCity(String value){
        $("#city").click();
        $(byText(value)).click();

        return this;
    }

    public RegistrationFormPage submitForm(){
        $("#submit").click();
        $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));

        return this;
    }

    public RegistrationFormPage checkResult(String key, String value){
        $(".table-responsive").$(byText(key)).parent().shouldHave(text(value));

        return this;
    }

    public RegistrationFormPage closePage(){
        $("#closeLargeModal").click();
        $(".modal-content").shouldBe(hidden);

        return this;
    }
}
