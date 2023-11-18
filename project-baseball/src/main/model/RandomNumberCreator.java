package main.model;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

public class RandomNumberCreator {
    private static final int MIN_NUMBER = 1;
    private static final int MAX_NUMBER = 9;
    private static final int NUMBER_LENGTH = 3;
    private String randomNumber;

    public RandomNumberCreator() {
        this.randomNumber = generateRandomNumber();
    }

    public String getRandomNumber() {
        return randomNumber;
    }

    private String generateRandomNumber() {
        Set<Integer> randomDigitSet = new HashSet<>();
        while (randomDigitSet.size() < NUMBER_LENGTH) {
            int randomDigit = MIN_NUMBER + ThreadLocalRandom.current().nextInt(MAX_NUMBER);
            randomDigitSet.add(randomDigit);
        }

        StringBuilder randomNumberBuilder = new StringBuilder();
        for (int randomDigit : randomDigitSet) {
            randomNumberBuilder.append(randomDigit);
        }
        return randomNumberBuilder.toString();
    }
}