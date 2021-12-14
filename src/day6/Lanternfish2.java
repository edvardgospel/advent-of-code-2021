package day6;

import java.util.Arrays;
import java.util.List;

public class Lanternfish2 {

    public static void main(String[] args) {
        long[] fishes = new long[9];
        List<Integer> initialStates = Arrays.asList(4, 3, 4, 5, 2, 1, 1, 5, 5, 3, 3, 1, 5, 1, 4, 2, 2, 3, 1, 5, 1, 4, 1, 2, 3, 4, 1, 4, 1, 5, 2, 1, 1, 3, 3, 5, 1, 1, 1, 1, 4, 5, 1, 2, 1, 2, 1, 1, 1, 5, 3, 3, 1, 1, 1, 1, 2, 4, 2, 1, 2, 3, 2, 5, 3, 5, 3, 1, 5, 4, 5, 4, 4, 4, 1, 1, 2, 1, 3, 1, 1, 4, 2, 1, 2, 1, 2, 5, 4, 2, 4, 2, 2, 4, 2, 2, 5, 1, 2, 1, 2, 1, 4, 4, 4, 3, 2, 1, 2, 4, 3, 5, 1, 1, 3, 4, 2, 3, 3, 5, 3, 1, 4, 1, 1, 1, 1, 2, 3, 2, 1, 1, 5, 5, 1, 5, 2, 1, 4, 4, 4, 3, 2, 2, 1, 2, 1, 5, 1, 4, 4, 1, 1, 4, 1, 4, 2, 4, 3, 1, 4, 1, 4, 2, 1, 5, 1, 1, 1, 3, 2, 4, 1, 1, 4, 1, 4, 3, 1, 5, 3, 3, 3, 4, 1, 1, 3, 1, 3, 4, 1, 4, 5, 1, 4, 1, 2, 2, 1, 3, 3, 5, 3, 2, 5, 1, 1, 5, 1, 5, 1, 4, 4, 3, 1, 5, 5, 2, 2, 4, 1, 1, 2, 1, 2, 1, 4, 3, 5, 5, 2, 3, 4, 1, 4, 2, 4, 4, 1, 4, 1, 1, 4, 2, 4, 1, 2, 1, 1, 1, 1, 1, 1, 3, 1, 3, 3, 1, 1, 1, 1, 3, 2, 3, 5, 4, 2, 4, 3, 1, 5, 3, 1, 1, 1, 2, 1, 4, 4, 5, 1, 5, 1, 1, 1, 2, 2, 4, 1, 4, 5, 2, 4, 5, 2, 2, 2, 5, 4, 4);
        for (int initialState : initialStates) {
            fishes[initialState]++;
        }

        for (int i = 0; i < 256; i++) {
            long fish0 = fishes[0];
            rotLeft(fishes);
            fishes[6] += fish0;
            fishes[8] += fish0;
        }

        long sum = 0;
        for (long fish : fishes) {
            sum += fish;
        }
        System.out.println(sum);
    }

    static void rotLeft(long[] a) {
        for (int i = 0; i < a.length - 1; i++) {
            a[i] = a[i + 1];
        }
        a[a.length - 1] = 0;
    }

}
