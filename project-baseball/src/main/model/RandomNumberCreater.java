package main.model;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

public class RandomNumberCreater {
    static final int NUMBER_LENGTH = 3;
    private static final Random defaultRandom = ThreadLocalRandom.current();

    String randomNumber;

    public void RandomNumber() {
        this.randomNumber = this.getRandomNumber();
    }

    public String getRandomNumber() {
        Set<Integer> randomDigitSet = new HashSet<>();

        while (true) {
            int randomDigit = 1 + defaultRandom.nextInt(9);
            randomDigitSet.add(randomDigit);
            if (randomDigitSet.size() == NUMBER_LENGTH) {
                break;
            }
        }

        String randomNumberString = "";
        for(int randomDigit : randomDigitSet) {
            randomNumberString += String.valueOf(randomDigit);
        }
        return randomNumberString;
    }
}
