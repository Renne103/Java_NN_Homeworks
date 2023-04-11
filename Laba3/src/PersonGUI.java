import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
/**

 The PersonGUI class is a graphical user interface for entering and displaying information about a person.
 It allows the user to enter the person's first name, last name, middle name, and birth date, and displays the
 person's full name, gender, and age in years.
 */
public class PersonGUI {
    private JTextField firstNameField;
    private JTextField lastNameField;
    private JTextField middleNameField;
    private JTextField birthDateField;
    private JButton submitButton;
    private JTextArea resultArea;
    /**
     * Constructs a new PersonGUI object and sets up the graphical user interface.
     */
    public PersonGUI() {
        // Создаем главное окно
        JFrame frame = new JFrame("Person");
        frame.setSize(500, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Создаем панель с элементами формы
        JPanel formPanel = new JPanel(new GridLayout(5, 2));
        formPanel.add(new JLabel("Фамилия:"));
        lastNameField = new JTextField();
        formPanel.add(lastNameField);
        formPanel.add(new JLabel("Имя:"));
        firstNameField = new JTextField();
        formPanel.add(firstNameField);
        formPanel.add(new JLabel("Отчество:"));
        middleNameField = new JTextField();
        formPanel.add(middleNameField);
        formPanel.add(new JLabel("Дата рождения:"));
        birthDateField = new JTextField("dd.mm.yyyy");
        formPanel.add(birthDateField);
        submitButton = new JButton("Получить результат");
        formPanel.add(submitButton);

        // Создаем панель для вывода результата
        resultArea = new JTextArea();
        resultArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(resultArea);

        // Добавляем обработчик клика по кнопке "Получить результат"
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String firstName = firstNameField.getText();
                String lastName = lastNameField.getText();
                String middleName = middleNameField.getText();
                String birthDateString = birthDateField.getText();

                // Создаем объект класса Person с введенными данными
                Person person = new Person(firstName, lastName, middleName, birthDateString);

                // Формируем текст для вывода результата
                String result = String.format("ФИО: %s %s.%s.\nПол: %s\nВозраст: %s",
                        person.getLastName(), person.getFirstName().charAt(0), person.getMiddleName().charAt(0),
                        person.getGender(), person.getAgeInYears());

                // Выводим результат в текстовую область
                resultArea.setText(result);
            }
        });

        // Добавляем панели на главное окно
        frame.add(formPanel, BorderLayout.NORTH);
        frame.add(scrollPane, BorderLayout.CENTER);

        // Показываем окно
        frame.setVisible(true);
    }
}