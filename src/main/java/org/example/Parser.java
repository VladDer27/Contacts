package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

@Component
public class Parser {

//    @Value("${app.root}")
    private String path = "src/main/resources/default-contacts.txt";

    public Parser() {
    }


    public void write(Storage storage){
        try(FileWriter writer = new FileWriter(path, false))
        {
            for(Contact contact : storage.getAllContacts()){
                String text = contact.toString();
                writer.write(text + "\n");
            }
            writer.flush();
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }
    }

    public void read(Storage storage){
        try {
            File file = new File(path);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(";");
                if (parts.length == 3) {
                    Contact contact = new Contact(parts[0], parts[1], parts[2]);
                    storage.add(contact);
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
