package ru.skogmark.go.gen.core;

import ru.skogmark.go.api.Wisdom;

/**
 * New flexible implementation of {@link WisdomGenerator}
 */
public class FlexibleWisdomGenerator implements WisdomGenerator {
    @Override
    public Wisdom generateOne() {
        return null;
    }

    @Override
    public Wisdom[] generateMany(int count) {
        return new Wisdom[0];
    }
}
