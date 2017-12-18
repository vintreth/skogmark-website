package ru.skogmark.go.gen.core;

import ru.skogmark.go.api.Wisdom;

/**
 * Central wisdom generator interface
 */
public interface WisdomGenerator {
    /**
     * Generates single wisdom
     *
     * @return generated wisdom entity
     */
    Wisdom generateOne();

    /**
     * Multiple wisdom generation
     *
     * @param count count of wisdoms.
     *              Max wisdoms limit should be implemented in child classes and it depends on inner implementation logic
     * @return generated wisdom entities
     */
    Wisdom[] generateMany(int count);
}
