package utils;

import com.github.javafaker.Faker;

import java.util.Locale;

public class RandomUtils {
    Faker faker = new Faker(new Locale("en-US"));

    public String generateFirstName() {
        return faker.name().firstName();
    }

    public String generateLastName() {
        return faker.name().lastName();
    }

    public String generateEmail() {
        return faker.internet().emailAddress();
    }

    public String selectRandomGender(String[] array) {
        return array[faker.number().numberBetween(0, array.length - 1)];
    }

    public String generateRandomPhoneNumber() {
        return faker.number().digits(10);
    }

    public String generateRandomLetters() {
        return faker.letterify("??????????");
    }

    public String generateYearOfBirthDay() {
        return String.valueOf(faker.number().numberBetween(1900, 2024));
    }

    public String generateMonthOfBirthDay() {
        String month = String.valueOf(faker.number().numberBetween(0, 11));

        switch (month) {
            case ("0"):
                return "January";

            case ("1"):
                return "February";

            case ("2"):
                return "March";

            case ("3"):
                return "April";

            case ("4"):
                return "May";

            case ("5"):
                return "June";

            case ("6"):
                return "July";

            case ("7"):
                return "August";

            case ("8"):
                return "September";

            case ("9"):
                return "October";

            case ("10"):
                return "November";

            case ("11"):
                return "December";

            default:
                return "January";
        }

    }

    public String generateDayOfBirthDay() {
        int value = faker.number().numberBetween(1, 28);

        if (value <= 9)
            return "0" + String.valueOf(value);
        else
            return String.valueOf(value);
    }

    public String[] generateSubjects(String[] array) {

        int arrayLength = faker.number().numberBetween(1, array.length);
        String[] newArray = new String[arrayLength];

        for (int i = 0; i < newArray.length; i++) {
            newArray[i] = faker.options().option(array);
            array = removeElementFromArray(newArray[i], array);
        }
        return newArray;

    }

    public String[] generateHobbies(String[] array) {
        int arrayLength = faker.number().numberBetween(1, array.length);
        String[] newArray = new String[arrayLength];

        for (int i = 0; i < newArray.length; i++) {
            newArray[i] = faker.options().option(array);
            array = removeElementFromArray(newArray[i], array);
        }
        return newArray;
    }

    public String chooseRandomFile(String[] arrayOfFileNames)
    {
        return faker.options().option(arrayOfFileNames);
    }

    public String generateAddress() {
        return String.valueOf(faker.address().streetAddress());
    }

    public String[] generateStateAndCity(String[] arrayOfStates, String[][] arrayOfCities) {
        int stateNumber = faker.number().numberBetween(0, 3);
        int cityNumber = faker.number().numberBetween(0, arrayOfCities[stateNumber].length);
        String[] resultArray = new String[2];
        resultArray[0] = arrayOfStates[stateNumber];
        resultArray[1] = arrayOfCities[stateNumber][cityNumber];

        return resultArray;
    }

    private String[] removeElementFromArray(String element, String[] array) {

        int newLength = 0;

        for (int i = 0; i < array.length; i++) {
            if (array[i] != element) {
                newLength++;
            }
        }
        String[] resultArray = new String[newLength];
        newLength = 0;

        for (int i = 0; i < array.length; i++) {
            if (array[i] != element) {
                resultArray[newLength++] = array[i];
            }
        }

        return resultArray;
    }


}
