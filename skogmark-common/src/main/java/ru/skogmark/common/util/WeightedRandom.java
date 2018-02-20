package ru.skogmark.common.util;

/**
 * Util methods for helping working with weighted random
 */
public class WeightedRandom {
    /**
     * Pick random index from array of weights
     *
     * @param weights array, every item of this array represents weight value
     * @return index of array item
     */
    public int pick(float[] weights) {
        // Computing the total weight of all weights together
        float totalWeight = 0.0f;
        for (float i : weights) {
            totalWeight += i;
        }
        // Choosing a random item
        int randomIndex = -1;
        float random = (float) Math.random() * totalWeight;
        for (int i = 0; i < weights.length; ++i) {
            random -= weights[i];
            if (random <= .0f) {
                randomIndex = i;
                break;
            }
        }
        return randomIndex;
    }
}
