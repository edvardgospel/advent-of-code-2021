package day5;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class HydrothermalVenture2 {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new FileReader("test5.txt"))) {
            int[][] matrix = new int[1000][1000];
            for (String line; (line = br.readLine()) != null; ) {
                String[] splitted = line.split(" -> ");
                int x1 = Integer.parseInt(splitted[0].split(",")[0]);
                int x2 = Integer.parseInt(splitted[1].split(",")[0]);
                int y1 = Integer.parseInt(splitted[0].split(",")[1]);
                int y2 = Integer.parseInt(splitted[1].split(",")[1]);

                if ((x1 == x2 && y1 != y2) || (x1 != x2 && y1 == y2)) {
                    if (x1 == x2) {
                        int min = Math.min(y1, y2);
                        int max = Math.max(y1, y2);
                        for (int i = min; i <= max; i++) {
                            matrix[i][x1]++;
                        }
                    }

                    if (y1 == y2) {
                        int min = Math.min(x1, x2);
                        int max = Math.max(x1, x2);
                        for (int i = min; i <= max; i++) {
                            matrix[y1][i]++;
                        }
                    }
                } else if (x1 != x2 || y1 != y2) {
                    if (x1 > x2 && y1 > y2) {
                        int i = x1;
                        int j = y1;
                        while (i >= x2) {
                            matrix[j][i]++;
                            i--;
                            j--;
                        }
                        continue;
                    }
                    if (x1 < x2 && y1 < y2) {
                        int i = x1;
                        int j = y1;
                        while (i <= x2) {
                            matrix[j][i]++;
                            i++;
                            j++;
                        }
                        continue;
                    }
                    if (x1 < x2 && y1 > y2) {
                        int i = x1;
                        int j = y1;
                        while (i <= x2) {
                            matrix[j][i]++;
                            i++;
                            j--;
                        }
                        continue;
                    }
                    if (x1 > x2 && y1 < y2) {
                        int i = x1;
                        int j = y1;
                        while (i >= x2) {
                            matrix[j][i]++;
                            i--;
                            j++;
                        }
                    }
                }
            }

            int count = countLargerThan1(matrix);
            System.out.println("Result: " + count);
        } catch (IOException e) {
            System.out.println("Error while reading file.");
        }
    }

    private static int countLargerThan1(int[][] matrix) {
        int count = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                if (matrix[i][j] > 1) {
                    count++;
                }
            }
        }
        return count;
    }

    private static void print(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                System.out.print(matrix[i][j] == 0 ? ". " : matrix[i][j] + " ");
            }
            System.out.println();
        }
       System.out.println();
    }
}
