package utils;

import java.util.Random;

public class useRandom {
    public int randomRange(int min, int max) {
        Random rand = new Random();
        int result = (int) (rand.nextInt(max - min + 1) + min);

        return result;

    }
}
