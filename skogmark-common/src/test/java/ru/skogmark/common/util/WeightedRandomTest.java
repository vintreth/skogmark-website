package ru.skogmark.common.util;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Tests for {@link WeightedRandom}
 */
public class WeightedRandomTest {
    private WeightedRandom weightedRandom = new WeightedRandom();

    @Test
    public void shouldReturnIndexOfTheMostWeightedItemRatherThenAnotherOnes_1000times_sorted() throws Exception {
        // given
        float[] weights = {1.0f, 0.7f, 0.4f, 0.3f, 0.1f, 0.05f, 0.02f, 0.01f};

        // when
        Multiset<Integer> indexes = HashMultiset.create();
        for (int i = 0; i < 1000; i++) {
            indexes.add(weightedRandom.pick(weights));
        }

        // then
        System.out.println(indexes);

    }
}