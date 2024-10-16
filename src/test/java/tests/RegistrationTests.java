package tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.RegistrationPage;
import utils.RandomUtils;

import static io.qameta.allure.Allure.step;

@Tag("demoqa")
public class RegistrationTests extends TestBase {


    private final RegistrationPage registrationPage = new RegistrationPage();
    private final RandomUtils randomUtils = new RandomUtils();

    private final String firstName = randomUtils.generateFirstName();
    private final String lastName = randomUtils.generateLastName();
    private final String userEmail = randomUtils.generateEmail();
    private final String[] genders = {"Male", "Female", "Other"};
    private final String gender = randomUtils.selectRandomGender(genders);
    private final String userNumber = randomUtils.generateRandomPhoneNumber();
    private final String negativeUserNumber = randomUtils.generateRandomLetters();
    private final String dayOfBirth = randomUtils.generateDayOfBirthDay();
    private final String monthOfBirth = randomUtils.generateMonthOfBirthDay();
    private final String yearOfBirth = randomUtils.generateYearOfBirthDay();
    private final String[] subjectsBase = {"Accounting",
            "Arts",
            "Biology",
            "Chemistry",
            "Civics",
            "Commerce",
            "Computer Science",
            "Economics",
            "English",
            "Hindi",
            "History",
            "Maths",
            "Physics",
            "Social Studies"};
    private final String[] subjects = randomUtils.generateSubjects(subjectsBase);
    private final String[] hobbiesBase = {"Sports", "Reading", "Music"};
    private final String[] hobbies = randomUtils.generateHobbies(hobbiesBase);
    private final String[] pictureNames = {"example.jpg", "example2.jpg", "example3.jpg"};
    private final String chosenPictureName = randomUtils.chooseRandomFile(pictureNames);
    private final String address = randomUtils.generateAddress();
    private final String[] stateBase = {"NCR", "Uttar Pradesh", "Haryana", "Rajasthan"};
    private final String[][] cityBase = {{"Delhi", "Gurgaon", "Noida"},
            {"Agra", "Lucknow", "Merrut"},
            {"Karnal", "Panipat"},
            {"Jaipur", "Jaiselmer"}};
    private final String[] stateAndCityResult = randomUtils.generateStateAndCity(stateBase, cityBase);

    @Test
    @DisplayName("Полностью заполняем и проверяем форму DemoQA")
    public void registrationWithPageObjectsTest() {
        step("Заполняем форму и жмем кнопку Submit", () -> {
                    registrationPage.setFirstName(firstName)
                            .setLastName(lastName)
                            .setUserEmail(userEmail)
                            .setGender(gender)
                            .setUserNumber(userNumber)
                            .setDateOfBirth(dayOfBirth, monthOfBirth, yearOfBirth)
                            .setSubjects(subjects)
                            .setHobbies(hobbies)
                            .uploadPicture(chosenPictureName)
                            .setAddress(address)
                            .setStateAndCity(stateAndCityResult)
                            .pushSubmitButton();
                }
        );

        step("Проверяем результаты", () -> {
            registrationPage.checkResult("Student Name", firstName + " " + lastName)
                    .checkResult("Student Email", userEmail)
                    .checkResult("Gender", gender)
                    .checkResult("Mobile", userNumber)
                    .checkResult("Date of Birth", dayOfBirth + " " + monthOfBirth + "," + yearOfBirth)
                    .checkResult("Subjects", subjects)
                    .checkResult("Hobbies", hobbies)
                    .checkResult("Address", address)
                    .checkResult("State and City", stateAndCityResult[0] + " " + stateAndCityResult[1]);
            if (Configuration.browser != "firefox") {
                registrationPage.checkResult("Picture", chosenPictureName);
            }
        });
    }

    @Test
    @DisplayName("Заполняем и проверяем минимально необходимые данные")
    public void minimalDataRequiredTest() {
        step("Заполняем форму и жмем кнопку Submit", () -> {
            registrationPage.setFirstName(firstName)
                    .setLastName(lastName)
                    .setGender(gender)
                    .setUserNumber(userNumber)
                    .pushSubmitButton();
        });

        step("Проверяем результаты", () -> {
            registrationPage.checkResult("Student Name", firstName + " " + lastName)
                    .checkResult("Gender", gender)
                    .checkResult("Mobile", userNumber);
        });
    }

    @Test
    @DisplayName("Негативный тест на заполнение некорректного номера телефона")
    public void negativePhoneNumberTest() {
        step("Вводим некоректный номер телефона и ждем кнопку Submit", () -> {
            registrationPage.setUserNumber(negativeUserNumber)
                    .pushSubmitButton();
        });

        step("Проверяем, что таблица с результатами не появилась", () -> {
            registrationPage.checkNegativeResult();
        });
    }

}
