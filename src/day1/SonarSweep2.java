package day1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Queue;

public class SonarSweep2 {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new FileReader("test1.txt"))) {
            Queue<Integer> queue = new ArrayDeque<>();
            int prev = Integer.MAX_VALUE;
            int result = 0;
            for (String strLine; (strLine = br.readLine()) != null; ) {
                int value = Integer.parseInt(strLine);
                queue.add(value);
                if (queue.size() == 3) {
                    int sum = getSum(queue);
                    if (sum > prev) {
                        result++;
                    }
                    prev = sum;
                    queue.remove();
                }
            }
            System.out.println(result);
        } catch (IOException e) {
            System.out.println("Error while reading file.");
        }
    }

    private static int getSum(Queue<Integer> queue) {
        int sum = 0;
        for (int val : queue) {
            sum += val;
        }
        return sum;
    }
}
