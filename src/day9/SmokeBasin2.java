package day9;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.lang.Character.getNumericValue;

public class SmokeBasin2 {

    private static final int ROW = 100;
    private static final int COL = 100;

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new FileReader("test9.txt"))) {
            int[][] matrix = createMatrix(br);
            List<Integer> basins = findBasins(matrix);
            basins.sort(Collections.reverseOrder());
            System.out.println(basins.get(0) * basins.get(1) * basins.get(2));
        } catch (IOException e) {
            System.out.println("Error while reading file.");
        }
    }

    private static int[][] createMatrix(BufferedReader br) throws IOException {
        int[][] matrix = new int[ROW][COL];
        int lineNumber = 0;
        for (String strLine; (strLine = br.readLine()) != null; ) {
            matrix[lineNumber] = new int[strLine.length()];
            for (int j = 0; j < strLine.length(); j++) {
                matrix[lineNumber][j] = getNumericValue(strLine.charAt(j));
            }
            lineNumber++;
        }
        return matrix;
    }

    private static List<Integer> findBasins(int[][] matrix) {
        List<Integer> basins = new ArrayList<>();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (!hasLowerOrEqualAdjacentElements(matrix, i, j)) {
                    basins.add(findBasin(matrix, new boolean[ROW][COL], i, j, 1));
                }
            }
        }
        return basins;
    }

    private static int findBasin(int[][] matrix, boolean[][] visited, int i, int j, int sum) {
        visited[i][j] = true;
        if (isOnMatrix(i - 1, ROW) && notHighestPoint(matrix[i - 1][j]) && !visited[i - 1][j]) {
            sum = findBasin(matrix, visited, i - 1, j, sum + 1);
        }
        if (isOnMatrix(i + 1, ROW) && notHighestPoint(matrix[i + 1][j]) && !visited[i + 1][j]) {
            sum = findBasin(matrix, visited, i + 1, j, sum + 1);
        }
        if (isOnMatrix(j - 1, COL) && notHighestPoint(matrix[i][j - 1]) && !visited[i][j - 1]) {
            sum = findBasin(matrix, visited, i, j - 1, sum + 1);
        }
        if (isOnMatrix(j + 1, COL) && notHighestPoint(matrix[i][j + 1]) && !visited[i][j + 1]) {
            sum = findBasin(matrix, visited, i, j + 1, sum + 1);
        }
        return sum;
    }

    private static boolean isOnMatrix(int index, int n) {
        return index >= 0 && index < n;
    }

    private static boolean notHighestPoint(int element) {
        return element != 9;
    }

    private static boolean hasLowerOrEqualAdjacentElements(int[][] matrix, int i, int j) {
        int el = matrix[i][j];
        if (i > 0 && matrix[i - 1][j] <= el) {
            return true;
        }
        if (i < matrix.length - 1 && matrix[i + 1][j] <= el) {
            return true;
        }
        if (j > 0 && matrix[i][j - 1] <= el) {
            return true;
        }
        return j < matrix[i].length - 1 && matrix[i][j + 1] <= el;
    }

}
