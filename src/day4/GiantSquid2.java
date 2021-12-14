package day4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GiantSquid2 {
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

            int bingoOrder = 0;
            for (int i : numbers) {
                for (Matrix matrix : matrixes) {
                    matrix.markNumber(i);
                }
                for (Matrix matrix : matrixes) {
                    if (matrix.isBingo() && matrix.getBingoOrder() == 0) {
                        matrix.setBingoOrder(++bingoOrder);
                        if (bingoOrder == 100) {
                            System.out.println("----------");
                            System.out.println(i * matrix.sumOfNotMarked()); // Result
                            System.out.println("----------");
                        }
                        matrix.setLastBingoNumber(i);
                    }
                }
            }

            for (Matrix m : matrixes) {
                m.print();
                System.out.println(m.getBingoOrder());
            }

            int maxOrder = 0;
            int sumNotMarked = 0;
            int lastBingoNumber = 0;
            for (Matrix matrix : matrixes) {
                if (matrix.getBingoOrder() > maxOrder) {
                    maxOrder = matrix.getBingoOrder();
                    lastBingoNumber = matrix.getLastBingoNumber();
                    sumNotMarked = matrix.sumOfNotMarked();
                }
            }
            System.out.println(" asd" + lastBingoNumber);
            System.out.println(sumNotMarked);
            System.out.println(lastBingoNumber * sumNotMarked);

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
