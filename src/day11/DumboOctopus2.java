package day11;

public class DumboOctopus2 {

    public static final int SIZE = 10;

    public static void main(String[] args) {
        int[][] input1 = {
                {5, 4, 8, 3, 1, 4, 3, 2, 2, 3},
                {2, 7, 4, 5, 8, 5, 4, 7, 1, 1},
                {5, 2, 6, 4, 5, 5, 6, 1, 7, 3},
                {6, 1, 4, 1, 3, 3, 6, 1, 4, 6},
                {6, 3, 5, 7, 3, 8, 5, 4, 7, 8},
                {4, 1, 6, 7, 5, 2, 4, 6, 4, 5},
                {2, 1, 7, 6, 8, 4, 1, 7, 2, 1},
                {6, 8, 8, 2, 8, 8, 1, 1, 3, 4},
                {4, 8, 4, 6, 8, 4, 8, 5, 5, 4},
                {5, 2, 8, 3, 7, 5, 1, 5, 2, 6}
        };

        int[][] input = {
                {4, 4, 7, 2, 5, 6, 2, 2, 6, 4},
                {8, 6, 3, 1, 5, 1, 7, 8, 2, 7},
                {7, 2, 3, 2, 1, 4, 4, 1, 4, 6},
                {2, 4, 4, 7, 1, 6, 3, 8, 2, 4},
                {1, 2, 3, 5, 2, 7, 2, 6, 7, 1},
                {5, 1, 3, 3, 5, 2, 7, 1, 4, 6},
                {6, 5, 1, 1, 3, 7, 2, 4, 1, 7},
                {3, 8, 4, 1, 8, 4, 1, 6, 1, 4},
                {8, 6, 2, 1, 3, 6, 8, 7, 8, 2},
                {3, 2, 4, 6, 3, 3, 6, 6, 7, 7},
        };

        int step = 1;
        while (sum(input) != 0) {

            for (int i = 0; i < input.length; i++) {
                for (int j = 0; j < input[i].length; j++) {
                    incrementEnergyLevels(input, i, j);
                }
            }
            getNumberOfFlashes(input);
            resetFlashes(input);
            step++;
        }
        System.out.println(step-1);
    }

    private static int sum(int[][] input) {
        int sum = 0;
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                sum += input[i][j];
            }
        }
        return sum;
    }

    private static void incrementEnergyLevels(int[][] energyLevels, int i, int j) {
        if (energyLevels[i][j] > 9) {
            return;
        }
        energyLevels[i][j]++;
        if (energyLevels[i][j] > 9) {
            if (isOnMatrix(i - 1) && energyLevels[i - 1][j] < 10) {
                incrementEnergyLevels(energyLevels, i - 1, j);
            }
            if (isOnMatrix(i + 1) && energyLevels[i + 1][j] < 10) {
                incrementEnergyLevels(energyLevels, i + 1, j);
            }
            if (isOnMatrix(j - 1) && energyLevels[i][j - 1] < 10) {
                incrementEnergyLevels(energyLevels, i, j - 1);
            }
            if (isOnMatrix(j + 1) && energyLevels[i][j + 1] < 10) {
                incrementEnergyLevels(energyLevels, i, j + 1);
            }
            if (isOnMatrix(i - 1) && isOnMatrix(j - 1) && energyLevels[i - 1][j - 1] < 10) {
                incrementEnergyLevels(energyLevels, i - 1, j - 1);
            }
            if (isOnMatrix(i + 1) && isOnMatrix(j + 1) && energyLevels[i + 1][j + 1] < 10) {
                incrementEnergyLevels(energyLevels, i + 1, j + 1);
            }
            if (isOnMatrix(i - 1) && isOnMatrix(j + 1) && energyLevels[i - 1][j + 1] < 10) {
                incrementEnergyLevels(energyLevels, i - 1, j + 1);
            }
            if (isOnMatrix(i + 1) && isOnMatrix(j - 1) && energyLevels[i + 1][j - 1] < 10) {
                incrementEnergyLevels(energyLevels, i + 1, j - 1);
            }
        }
    }

    private static int getNumberOfFlashes(int[][] input) {
        int count = 0;
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (input[i][j] > 9) {
                    count++;
                }
            }
        }
        return count;
    }

    private static void resetFlashes(int[][] input) {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (input[i][j] > 9) {
                    input[i][j] = 0;
                }
            }
        }
    }

    private static boolean isOnMatrix(int index) {
        return index >= 0 && index < SIZE;
    }


    public static void print(int[][] matrix) {
        for (int[] row : matrix) {
            for (int i : row) {
                System.out.print(i == 0 ? "\u001B[32m" + i + "  " : "\u001B[0m" + i + "  ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
