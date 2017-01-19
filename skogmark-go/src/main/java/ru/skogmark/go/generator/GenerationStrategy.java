package ru.skogmark.go.generator;

import ru.skogmark.go.domain.Wisdom;

/**
 * Created by SwEEp on 06.01.2017.
 */
@FunctionalInterface
public interface GenerationStrategy {
    Wisdom generate();
}
