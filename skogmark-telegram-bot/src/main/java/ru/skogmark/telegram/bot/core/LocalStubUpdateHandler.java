package ru.skogmark.telegram.bot.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LocalStubUpdateHandler implements UpdateHandler {
    private static final Logger log = LoggerFactory.getLogger(LocalStubUpdateHandler.class);

    @Override
    public void start() {
        log.info("Starting UpdateHandler stub");
    }

    @Override
    public void stop() {
        log.info("Stopping UpdateHandler stub");
    }
}
