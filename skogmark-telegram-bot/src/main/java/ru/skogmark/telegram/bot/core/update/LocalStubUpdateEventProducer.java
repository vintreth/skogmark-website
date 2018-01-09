package ru.skogmark.telegram.bot.core.update;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class LocalStubUpdateEventProducer implements UpdateEventProducer {
    private static final Logger log = LoggerFactory.getLogger(LocalStubUpdateEventProducer.class);

    @Override
    public void start() {
        log.info("Starting UpdateEventProducer stub");
    }

    @Override
    public void stop() {
        log.info("Stopping UpdateEventProducer stub");
    }
}
