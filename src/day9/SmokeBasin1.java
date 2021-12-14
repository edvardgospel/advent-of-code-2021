package day9;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import static java.lang.Character.getNumericValue;

public class SmokeBasin1 {
    public static void main(String[] args) {
        int[][] matrix = new int[100][];
        int count = 0;
        int sum = 0;
        try (BufferedReader br = new BufferedReader(new FileReader("test9.txt"))) {
            for (String strLine; (strLine = br.readLine()) != null; ) {
                matrix[count] = new int[strLine.length()];
                for (int i = 0; i < strLine.length(); i++) {
                    matrix[count][i] = getNumericValue(strLine.charAt(i));
                }
                count++;
            }

            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix[i].length; j++) {
                    if (!hasLowerOrEqualAdjacentElements(matrix, i, j)) {
                        System.out.println(matrix[i][j]);
                        sum += matrix[i][j] + 1;
                    }
                }
            }
            System.out.println("Result: " + sum);
        } catch (IOException e) {
            System.out.println("Error while reading file.");
        }
    }

    private static boolean hasLowerOrEqualAdjacentElements(int[][] matrix, int i, int j) {
        int el = matrix[i][j];
        //Tocheck: matrix[i-1][j], matrix[i][j-1], matrix[i+1][j], matrix[i][j+1]
        if (i > 0 && matrix[i - 1][j] <= el) {
            return true;
        }
        if (i < matrix.length - 1 && matrix[i + 1][j] <= el) {
            return true;
        }
        if (j > 0 && matrix[i][j - 1] <= el) {
            return true;
        }
        if (j < matrix[i].length - 1 && matrix[i][j + 1] <= el) {
            return true;
        }

        return false;
    }

    private static void print(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
