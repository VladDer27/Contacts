package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@Profile("init")
@PropertySource("classpath:application-init.properties")
public class InitContactsLoader {
    private final Storage storage;

    @Value("${app.root}")
    private String path;

    @Autowired
    public InitContactsLoader(Storage storage) {
        this.storage = storage;
    }

    @PostConstruct
    public void initContacts() {
        storage.initContactsFromFile(path);
    }
}
