package data;

import utils.RandomUtils;

public class TestData {
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

    public RandomUtils getRandomUtils() {
        return randomUtils;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public String[] getGenders() {
        return genders;
    }

    public String getGender() {
        return gender;
    }

    public String getUserNumber() {
        return userNumber;
    }

    public String getNegativeUserNumber() {
        return negativeUserNumber;
    }

    public String getDayOfBirth() {
        return dayOfBirth;
    }

    public String getMonthOfBirth() {
        return monthOfBirth;
    }

    public String getYearOfBirth() {
        return yearOfBirth;
    }

    public String[] getSubjectsBase() {
        return subjectsBase;
    }

    public String[] getSubjects() {
        return subjects;
    }

    public String[] getHobbiesBase() {
        return hobbiesBase;
    }

    public String[] getHobbies() {
        return hobbies;
    }

    public String[] getPictureNames() {
        return pictureNames;
    }

    public String getChosenPictureName() {
        return chosenPictureName;
    }

    public String getAddress() {
        return address;
    }

    public String[] getStateBase() {
        return stateBase;
    }

    public String[][] getCityBase() {
        return cityBase;
    }

    public String[] getStateAndCityResult() {
        return stateAndCityResult;
    }
}
