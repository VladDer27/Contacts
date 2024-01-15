package org.example;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

@Component
public class Storage {

    private final ArrayList<Contact> contacts = new ArrayList<>();

    public boolean add(Contact contact) {
        return contacts.add(contact);
    }

    public boolean delete(String email) {
        for (Contact contact : contacts) {
            if (contact.getEmail().equals(email)) {
                contacts.remove(contact);
                return true;
            }
        }
        return false;
    }

    public ArrayList<Contact> getAllContacts() {
        return contacts;
    }

    @Profile("init")
    public void initContactsFromFile(String path) {
        try {
            File file = new File(path);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(";");
                if (parts.length == 3) {
                    Contact contact = new Contact(parts[0], parts[1], parts[2]);
                    contacts.add(contact);
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Проблемы с чтением файла! ");
        }
    }
}
