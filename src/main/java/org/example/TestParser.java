package org.example;

public class TestParser implements EnvParser {
    @Override
    public void write(Storage storage) {
        System.out.println("Не записываю в файл!");
    }

    @Override
    public void read(Storage storage) {
        System.out.println("Не читаю файл!");
    }
}
