package org.example;

import org.example.config.DefaultAppConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    private final static String NAME_REGEX = "([А-я][а-я]+\\s?){3}";
    private final static String PHONE_REGEX = "\\+89[0-9]{9}";
    private final static String EMAIL_REGEX = "\\w+@[a-z]+.[a-z]+";

    public static boolean isRegex(String regex, String data) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(data);
        return matcher.matches();
    }

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(DefaultAppConfig.class);
        Storage storage = context.getBean(Storage.class);
        EnvParser parser = context.getBean(EnvParser.class);
        parser.read(storage);
        Scanner scanner = new Scanner(System.in);

        boolean work = true;
        while (work) {
            System.out.println("Выберите команду: \n" +
                    "1 - Вывести все контакты\n" +
                    "2 - Добавить контакт\n" +
                    "3 - Удалить контакт\n" +
                    "4 - Выйти из программы");


            String choice = scanner.next();
            switch (choice) {
                case "1": {
                    System.out.println("Ваши контакты: ");
                    if (storage.getAllContacts().isEmpty()) System.out.println("Ваш список контактов пуст!");
                    for (Contact contact : storage.getAllContacts())
                        System.out.println(contact);
                    break;
                }
                case "2": {
                    System.out.println("Введите контакт в формате «Ф. И. О;номер телефона;адрес электронной почты»: ");
                    scanner.nextLine();
                    String input = scanner.nextLine();
                    String[] data = input.split(";");
                    if (data.length != 3) {
                        System.out.println("Введен неверный формат!");
                        break;
                    }
                    if (!isRegex(NAME_REGEX, data[0])) {
                        System.out.println("Неправильно введен ФИО!");
                        break;
                    }
                    if (!isRegex(PHONE_REGEX, data[1])) {
                        System.out.println("Неправильно введен номер телефона!");
                        break;
                    }
                    if (!isRegex(EMAIL_REGEX, data[2])) {
                        System.out.println("Неправильно введена почта!");
                        break;
                    }
                    Contact contact = context.getBean(Contact.class);
                    contact.setFullName(data[0]);
                    contact.setPhoneNumber(data[1]);
                    contact.setEmail(data[2]);
                    System.out.println(storage.add(contact) ? "Добавлен новый контакт!" : "Ошибка добавления нового контакта!");
                    break;
                }
                case "3": {
                    System.out.println("Введите email контакта, который хотите удалить: ");
                    String email = scanner.next();
                    System.out.println(storage.delete(email) ? "Контакт удален!" : "Неверно введен email!");
                    break;
                }
                case "4": {
                    parser.write(storage);
                    work = false;
                    break;
                }
                default: {
                    System.out.println("Неверна введена команда! Попробуйте еще раз!");
                    break;
                }

            }
        }
    }
}
