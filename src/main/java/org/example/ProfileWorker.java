package org.example;

import org.springframework.stereotype.Component;

@Component
public class ProfileWorker {
    private final EnvParser envParser;

    public ProfileWorker(EnvParser envParser) {
        this.envParser = envParser;
    }

    public void doWrite(Storage storage) {
        envParser.write(storage);
    }

    public void doRead(Storage storage) {
        envParser.read(storage);
    }
}
