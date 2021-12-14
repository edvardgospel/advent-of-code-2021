package day4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GiantSquid1 {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new FileReader("test4.txt"))) {
            int[] numbers = toIntArray(br.readLine().trim().split(","));
            List<Matrix> matrixes = new ArrayList<>();
            while (br.readLine() != null) {
                Matrix matrix = new Matrix(5);
                int[][] mints = new int[5][];
                mints[0] = toIntArray(br.readLine().trim().split("\\s+"));
                mints[1] = toIntArray(br.readLine().trim().split("\\s+"));
                mints[2] = toIntArray(br.readLine().trim().split("\\s+"));
                mints[3] = toIntArray(br.readLine().trim().split("\\s+"));
                mints[4] = toIntArray(br.readLine().trim().split("\\s+"));
                matrix.fill(mints);
                matrixes.add(matrix);
            }

            for (Matrix m : matrixes)
                m.print();

            for (int i : numbers) {
                for (Matrix matrix : matrixes) {
                    matrix.markNumber(i);
                }
                for (Matrix matrix : matrixes) {
                    if (matrix.isBingo()) {
                        int sum = matrix.sumOfNotMarked();
                        System.out.println(sum * i);
                        return;
                    }
                }
            }

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
