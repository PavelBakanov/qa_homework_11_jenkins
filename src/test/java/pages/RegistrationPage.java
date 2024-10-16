package pages;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import pages.components.CalendarComponent;
import pages.components.ResultTableOfRegistration;

import java.util.Objects;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class RegistrationPage {
    private final SelenideElement firstNameLocator = $("#firstName");
    private final SelenideElement lastNameLocator = $("#lastName");
    private final SelenideElement userEmailLocator = $("#userEmail");
    private final SelenideElement genderLocator = $("#genterWrapper");
    private final SelenideElement userNumberLocator = $("#userNumber");
    private final SelenideElement dateOfBirthLocator = $("#dateOfBirthInput");
    private final SelenideElement subjectsLocator = $("#subjectsInput");
    private final SelenideElement hobbiesLocator = $("#hobbiesWrapper");
    private final SelenideElement uploadPictureLocator = $("#uploadPicture");
    private final SelenideElement currentAddressLocator = $("#currentAddress");
    private final SelenideElement stateCityWrapperLocator = $("#stateCity-wrapper");
    private final SelenideElement stateLocator = $("#state");
    private final SelenideElement cityLocator = $("#city");
    private final SelenideElement submitButtonLocator = $("#submit");

    private final String resultTableLocator = ".table-responsive";

    private final CalendarComponent calendarComponent = new CalendarComponent();
    private final ResultTableOfRegistration resultTableOfRegistration = new ResultTableOfRegistration();


    public RegistrationPage openPage() {
        open("/automation-practice-form");
        return this;
    }

    public RegistrationPage removeBanner() {
        executeJavaScript("$('#fixedban').remove()");
        executeJavaScript("$('footer').remove()");
        return this;
    }

    public RegistrationPage setFirstName(String value) {
        firstNameLocator.scrollTo().setValue(value);
        return this;
    }

    public RegistrationPage setLastName(String value) {
        lastNameLocator.scrollTo().setValue(value);
        return this;
    }

    public RegistrationPage setUserEmail(String value) {
        userEmailLocator.scrollTo().setValue(value);
        return this;
    }

    public RegistrationPage setGender(String value) {
        genderLocator.$(byText(value)).scrollTo().click();
        return this;
    }

    public RegistrationPage setUserNumber(String value) {
        userNumberLocator.scrollTo().setValue(value);
        return this;
    }

    public RegistrationPage setDateOfBirth(String day, String month, String year) {
        dateOfBirthLocator.scrollTo().click();
        calendarComponent.setDate(day, month, year);
        return this;
    }

    public RegistrationPage setSubjects(String[] array) {
        for (int i = 0; i < array.length; i++) {
            subjectsLocator.scrollTo().setValue(array[i]).pressEnter();
        }
        return this;
    }

    public RegistrationPage setHobbies(String[] array) {
        for (int i = 0; i < array.length; i++) {
            hobbiesLocator.$(byText(array[i])).scrollTo().click();
        }
        return this;
    }

    public void uploadPicture(String value) {
        if (!Objects.equals(Configuration.browser, "firefox")) {
            uploadPictureLocator.scrollTo().uploadFromClasspath(value);
        }
    }

    public RegistrationPage setAddress(String value) {
        currentAddressLocator.scrollTo().setValue(value);
        return this;
    }

    public RegistrationPage setStateAndCity(String[] stateAndCityArray) {
        stateLocator.scrollTo().click();
        stateCityWrapperLocator.$(byText(stateAndCityArray[0])).scrollTo().click();
        cityLocator.scrollTo().click();
        stateCityWrapperLocator.$(byText(stateAndCityArray[1])).scrollTo().click();
        return this;
    }

    public void pushSubmitButton() {
        submitButtonLocator.scrollTo().click();
    }

    public RegistrationPage checkResult(String key, String value) {

        resultTableOfRegistration.checkResultOfRegistration(resultTableLocator, key, value);
        return this;
    }

    public RegistrationPage checkResult(String key, String[] arrayOfValues) {

        for (int i = 0; i < arrayOfValues.length; i++) {
            resultTableOfRegistration.checkResultOfRegistration(resultTableLocator, key, arrayOfValues[i]);
        }

        return this;
    }


    public void checkNegativeResult() {
        resultTableOfRegistration.checkNegativeResult(resultTableLocator);
    }


}
