import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class UserDataApp {

    public static void main(String[] args) {
        try {
            String input = "Иванов Иван Иванович 01.01.2000 1234567890 m";
            createUserFile(input);
        } catch (Exception e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }

    private static void createUserFile(String input) throws Exception {
        String[] data = input.split(" ");
        if (data.length != 6) {
            throw new Exception("Недопустимое количество параметров. Пожалуйста введите: Фамилия Имя Отчество ДатаРождения НомерТелефона Пол");
        }

        String surname = data[0];
        String name = data[1];
        String patronymic = data[2];
        String birthDate = data[3];
        String phoneNumber = data[4];
        String gender = data[5];

        if (!birthDate.matches("\\d{2}\\.\\d{2}\\.\\d{4}")) {
            throw new Exception("Неверный формат даты. Пожалуйста, введите дату в формате дд.мм.гггг");
        }

        if (!phoneNumber.matches("\\d+")) {
            throw new Exception("Неверный номер телефона. Пожалуйста, вводите только цифры без форматирования");
        }

        if (!gender.equals("f") && !gender.equals("m")) {
            throw new Exception("Неверный пол. Пожалуйста, введите f для женского пола или m для мужского.");
        }

        String fileName = surname + ".txt";
        String userData = surname + name + patronymic + birthDate + " " + phoneNumber + gender;

        try (FileWriter writer = new FileWriter(new File(fileName), true)) {
            writer.write(userData + "\n");
            System.out.println("Данные успешно сохранены в файл: " + fileName);
        } catch (IOException e) {
            throw new Exception("Ошибка записи в файла: " + e.getMessage());
        }
    }
}