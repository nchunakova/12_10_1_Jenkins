package qa.nchunakova.tests.demoqa;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.hidden;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;
import static java.lang.String.format;

@Tag("demoqa")
public class FillFormWithTestDataTests extends TestBase  {

    String firstName = "Sia",
            lastName = "Bamberg",
            userEmail = "sia@bamberg.com",
            gender = "Female",
            userNumber = "0123456789",
            currentAddress = "My street 2/1";

    @Test
    @DisplayName("Successful fill registration test")
    void fillFormTest(){
        step("Open registration form", () -> {
                    open("/automation-practice-form");
                    executeJavaScript("$('footer').remove()");
                    executeJavaScript("$('#fixedban').remove()");
                });

        step("Fill registration form", () -> {
                    $("#firstName").setValue(firstName);
                    $("#lastName").setValue(lastName);
                    $("#userEmail").setValue(userEmail);
                    $("#genterWrapper").$(byText(gender)).click();
                    $("#userNumber").setValue(userNumber);
                    $("#dateOfBirthInput").click();
                    $(".react-datepicker__month-select").selectOption("July");
                    $(".react-datepicker__year-select").selectOption("2008");
                    $(".react-datepicker__day--030:not(.react-datepicker__day--outside-month)").click();
                    $("#subjectsInput").setValue("E").pressEnter(); // todo edit subjects
                    $("#hobbiesWrapper").$(byText("Reading")).click();
                    $("#uploadPicture").uploadFromClasspath("images/gymnocalycium-monvillei-mm814.jpg");
                    $("#currentAddress").setValue(currentAddress);
                    $("#state").click();
                    $(byText("Rajasthan")).click();
                    $("#city").click();
                    $(byText("Jaiselmer")).click();
                    $("#submit").click();
                });

        step("Verify form data", () -> {
            $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));
            $(".table.table-dark.table-striped.table-bordered.table-hover").shouldHave(text(firstName),
                    text(lastName), text(gender), text(userNumber), text("30 July,2008"), text("English"),
                    text("Reading"), text("gymnocalycium-monvillei-mm814.jpg"), text(currentAddress),
                    text("Rajasthan Jaiselmer"));
            $("#closeLargeModal").click();
            $(".modal-content").shouldBe(hidden);
        });
    }
}