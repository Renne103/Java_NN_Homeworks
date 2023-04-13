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
    private JTextField NameField;
    private JTextField lastNameField;
    private JTextField middleNameField;
    private JTextField birthField;
    private JButton button;
    private JTextArea resultArea;
    /**
     * Constructs a new PersonGUI object and sets up the graphical user interface.
     */
    public PersonGUI() {
        // Создаем главное окно
        JFrame frame = new JFrame("Person");
        frame.setSize(500, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        JPanel formPanel = new JPanel(new GridLayout(5, 2));
        formPanel.add(new JLabel("Фамилия:"));
        lastNameField = new JTextField();
        formPanel.add(lastNameField);
        formPanel.add(new JLabel("Имя:"));
        NameField = new JTextField();
        formPanel.add(NameField);
        formPanel.add(new JLabel("Отчество:"));
        middleNameField = new JTextField();
        formPanel.add(middleNameField);
        formPanel.add(new JLabel("Дата рождения:"));
        birthField = new JTextField("dd.mm.yyyy");
        formPanel.add(birthField);
        button = new JButton("Получить результат");
        formPanel.add(button);

        resultArea = new JTextArea();
        resultArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(resultArea);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String firstName = NameField.getText();
                String lastName = lastNameField.getText();
                String middleName = middleNameField.getText();
                String birthDateString = birthField.getText();

                Person person = new Person(firstName, lastName, middleName, birthDateString);

                String result = String.format("ФИО: %s %s.%s.\nПол: %s\nВозраст: %s",
                        person.getLastName(), person.getFirstName().charAt(0), person.getMiddleName().charAt(0),
                        person.getGender(), person.getAgeInYears());


                resultArea.setText(result);
            }
        });

        frame.add(formPanel, BorderLayout.NORTH);
        frame.add(scrollPane, BorderLayout.CENTER);

        frame.setVisible(true);
    }
}
