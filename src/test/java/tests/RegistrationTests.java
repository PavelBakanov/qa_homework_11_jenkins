package tests;

import com.codeborne.selenide.Configuration;
import data.TestData;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.RegistrationPage;

import java.util.Objects;

import static io.qameta.allure.Allure.step;

@Tag("demoqa")
public class RegistrationTests extends TestBase {

    private final RegistrationPage registrationPage = new RegistrationPage();
    private final TestData testData = new TestData();

    @Test
    @DisplayName("Полностью заполняем и проверяем форму DemoQA")
    public void registrationWithPageObjectsTest() {
        step("Заполняем форму и жмем кнопку Submit", () -> {
                    registrationPage.setFirstName(testData.getFirstName())
                            .setLastName(testData.getLastName())
                            .setUserEmail(testData.getUserEmail())
                            .setGender(testData.getGender())
                            .setUserNumber(testData.getUserNumber())
                            .setDateOfBirth(testData.getDayOfBirth(), testData.getMonthOfBirth(), testData.getYearOfBirth())
                            .setSubjects(testData.getSubjects())
                            .setHobbies(testData.getHobbies())
                            .setAddress(testData.getAddress())
                            .setStateAndCity(testData.getStateAndCityResult());
                    registrationPage.uploadPicture(testData.getChosenPictureName());
                    registrationPage.pushSubmitButton();
                }
        );

        step("Проверяем результаты", () -> {
            registrationPage.checkResult("Student Name", testData.getFirstName() + " " + testData.getLastName())
                    .checkResult("Student Email", testData.getUserEmail())
                    .checkResult("Gender", testData.getGender())
                    .checkResult("Mobile", testData.getUserNumber())
                    .checkResult("Date of Birth", testData.getDayOfBirth() + " "
                            + testData.getMonthOfBirth() + ","
                            + testData.getYearOfBirth())
                    .checkResult("Subjects", testData.getSubjects())
                    .checkResult("Hobbies", testData.getHobbies())
                    .checkResult("Address", testData.getAddress())
                    .checkResult("State and City", testData.getStateAndCityResult()[0] + " "
                            + testData.getStateAndCityResult()[1]);
            if (!Objects.equals(Configuration.browser, "firefox")) {
                registrationPage.checkResult("Picture", testData.getChosenPictureName());
            }
        });
    }

    @Test
    @DisplayName("Заполняем и проверяем минимально необходимые данные")
    public void minimalDataRequiredTest() {
        step("Заполняем форму и жмем кнопку Submit", () -> {
            registrationPage.setFirstName(testData.getFirstName())
                    .setLastName(testData.getLastName())
                    .setGender(testData.getGender())
                    .setUserNumber(testData.getUserNumber())
                    .pushSubmitButton();
        });

        step("Проверяем результаты", () -> {
            registrationPage.checkResult("Student Name", testData.getFirstName() + " " + testData.getLastName())
                    .checkResult("Gender", testData.getGender())
                    .checkResult("Mobile", testData.getUserNumber());
        });
    }

    @Test
    @DisplayName("Негативный тест на заполнение некорректного номера телефона")
    public void negativePhoneNumberTest() {
        step("Вводим некоректный номер телефона и ждем кнопку Submit", () -> {
            registrationPage.setUserNumber(testData.getNegativeUserNumber())
                    .pushSubmitButton();
        });

        step("Проверяем, что таблица с результатами не появилась", () -> {
            registrationPage.checkNegativeResult();
        });
    }

}
