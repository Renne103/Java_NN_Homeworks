import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
/**

 The Person class represents a person with a first name, last name, middle name, and birth date.

 It provides methods to retrieve the person's name, gender, and age in years.
 */
public class Person {
    private String firstName;
    private String lastName;
    private String middleName;
    private LocalDate birthDate;
    /**

     Constructs a Person object with the given first name, last name, middle name, and birth date string.

     @param firstName the person's first name

     @param lastName the person's last name

     @param middleName the person's middle name

     @param birthDateString the person's birth date in the format dd.MM.yyyy

     @throws IllegalArgumentException if the birth date string is in an incorrect format or represents a date in the future
     */
    public Person(String firstName, String lastName, String middleName, String birthDateString) throws IllegalArgumentException {
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
            this.birthDate = LocalDate.parse(birthDateString, formatter);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Некорректный формат даты рождения. Используйте формат dd.MM.yyyy.");
        }

        LocalDate curDate = LocalDate.now();
        if (birthDate.isAfter(curDate)) {
            throw new IllegalArgumentException("Дата рождения не может быть в будущем.");
        }


        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        this.birthDate = LocalDate.parse(birthDateString, formatter);
    }
    /**

     Returns the person's first name.
     @return the person's first name
     */
    public String getFirstName() {
        return firstName;
    }
    /**

     Returns the person's last name.
     @return the person's last name
     */
    public String getLastName() {
        return lastName;
    }
    /**

     Returns the person's middle name.
     @return the person's middle name
     */
    public String getMiddleName() {
        return middleName;
    }
    /**

     Returns the person's gender based on the endings of their first name, middle name, and last name.

     @return "Женский" if the person is female, "Мужской" otherwise
     */
    public String getGender() {
        String firstNameLower = firstName.toLowerCase();
        String middleNameLower = middleName.toLowerCase();
        String lastNameLower = lastName.toLowerCase();

        if (middleNameLower.endsWith("вна") || middleNameLower.endsWith("чна") || lastNameLower.endsWith("ая") || lastNameLower.endsWith("на")) {
            return "Женский";
        } else {
            return "Мужской";
        }
    }

    /**

     Returns the person's age in years based on their birth date and the current date.
     @return the person's age in years as a string with the appropriate grammatical ending
     */
    public String getAgeInYears() {
        LocalDate curDate = LocalDate.now();
        Period period = Period.between(birthDate, curDate);
        int y = period.getYears();
        int lastDigit = y % 10;
        String ending;
        if (lastDigit == 1 && y != 11) {
            ending = "год";
        } else if (lastDigit >= 2 && lastDigit <= 4 && !(y >= 12 && y <= 14)) {
            ending = "года";
        } else {
            ending = "лет";
        }
        return y + " " + ending;
    }

}
