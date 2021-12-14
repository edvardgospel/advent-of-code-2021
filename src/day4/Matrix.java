package day4;

public class Matrix {
    public int n;
    public int[][] a;
    public int[][] b;
    public int bingoOrder;
    public int lastBingoNumber;

    public Matrix(int n) {
        this.n = n;
        this.a = new int[n][];
        this.b = new int[n][n];
        this.bingoOrder = 0;
        this.lastBingoNumber = 0;
    }

    public void fill(int [][] a) {
        this.a = a;
    }

    public void print() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(a[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public void markNumber(int number) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (a[i][j] == number) {
                    b[i][j] = 1;
                    return;
                }
            }
        }
    }

    public boolean isBingo() {
        if (isRowBingo() || isColumnBingo()) {
            return true;
        }
        return false;
    }

    private boolean isRowBingo() {
        for (int i = 0; i < n; i++) {
            int sum = 0;
            for (int j = 0; j < n; j++) {
                sum += b[i][j];
            }
            if (sum == n) {
                return true;
            }
        }
        return false;
    }

    private boolean isColumnBingo() {
        for (int i = 0; i < n; i++) {
            int sum = 0;
            for (int j = 0; j < n; j++) {
                sum += b[j][i];
            }
            if (sum == n) {
                return true;
            }
        }
        return false;
    }

    public int  sumOfNotMarked() {
        int sum = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (b[i][j] == 0) {
                    sum += a[i][j];
                }
            }
        }
        return sum;
    }

    public void setBingoOrder(int number) {
        this.bingoOrder = number;
    }

    public int getBingoOrder() {
        return bingoOrder;
    }

    public void setLastBingoNumber(int number) {
        this.lastBingoNumber = number;
    }

    public int getLastBingoNumber() {
        return lastBingoNumber;
    }

}
