package day6;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Lanternfish1 {

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new FileReader("test6.txt"))) {
            int[] lanternFishez = toIntArray(br.readLine().split(","));
            List<Integer> lanternFishes = new ArrayList<>();
            for (int lf : lanternFishez) {
                lanternFishes.add(lf);
            }
            int result = lanternFishes.size();
            int addCounter = 0;
            for (int i = 0; i < 200; i++) {//204394337
                System.out.println(i + " -- ");
                for (int j = 0; j < lanternFishes.size(); j++) {
                    if (lanternFishes.get(j).equals(0)) {
                        addCounter++;
                        lanternFishes.set(j, 6);
                    } else {
                        Integer element = lanternFishes.get(j);
                        lanternFishes.set(j, --element);
                    }
                }
                for (int ii = 0; ii < addCounter; ii++) {
                    lanternFishes.add(8);
                }
                result += addCounter;
                addCounter = 0;
            }
            System.out.println(result);
        } catch (IOException e) {
            System.out.println("Error while reading file.");
        }
    }

    private static int[] toIntArray(String[] numberStrs) {
        int[] numbers = new int[numberStrs.length];
        for (int i = 0; i < numberStrs.length; i++) {
            numbers[i] = Integer.parseInt(numberStrs[i]);
        }
        return numbers;
    }
}
