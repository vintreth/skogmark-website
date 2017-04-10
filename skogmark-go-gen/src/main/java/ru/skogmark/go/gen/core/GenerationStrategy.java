package ru.skogmark.go.gen.core;

import ru.skogmark.go.gen.core.domain.Wisdom;

/**
 * Created by SwEEp on 06.01.2017.
 */
@FunctionalInterface
public interface GenerationStrategy {
    Wisdom generate();
}
