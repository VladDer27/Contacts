package org.example;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
@Component
public class Storage {

    private final ArrayList<Contact> contacts = new ArrayList<>();

    public boolean add(Contact contact){
        return contacts.add(contact);
    }

    public boolean delete(String email){
        for(Contact contact : contacts){
            if(contact.getEmail().equals(email)){
                contacts.remove(contact);
                return true;
            }
        }
        return false;
    }

    public ArrayList<Contact> getAllContacts(){
        return contacts;
    }
}
