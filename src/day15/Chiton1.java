package day15;

import java.io.IOException;
import java.util.Comparator;
import java.util.PriorityQueue;

class Chiton1 {

    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int ROW;
    static int COL;

    static class Cell {
        int x;
        int y;
        int distance;

        Cell(int x, int y, int distance) {
            this.x = x;
            this.y = y;
            this.distance = distance;
        }
    }

    static class distanceComparator implements Comparator<Cell> {
        public int compare(Cell a, Cell b) {
            return Integer.compare(a.distance, b.distance);
        }
    }

    static boolean isInsideGrid(int i, int j) {
        return (i >= 0 && i < ROW &&
                j >= 0 && j < COL);
    }

    static int shortestPath(int[][] grid, int row, int col) {
        int[][] dist = new int[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                dist[i][j] = Integer.MAX_VALUE;
            }
        }
        dist[0][0] = grid[0][0];
        PriorityQueue<Cell> pq = new PriorityQueue<>(row * col, new distanceComparator());
        pq.add(new Cell(0, 0, dist[0][0]));
        while (!pq.isEmpty()) {
            Cell curr = pq.poll();
            for (int i = 0; i < 4; i++) {
                int rows = curr.x + dx[i];
                int cols = curr.y + dy[i];
                if (isInsideGrid(rows, cols)) {
                    if (dist[rows][cols] > dist[curr.x][curr.y] + grid[rows][cols]) {
                        if (dist[rows][cols] != Integer.MAX_VALUE) {
                            Cell adj = new Cell(rows, cols, dist[rows][cols]);
                            pq.remove(adj);
                        }
                        dist[rows][cols] = dist[curr.x][curr.y] + grid[rows][cols];
                        pq.add(new Cell(rows, cols,
                                dist[rows][cols]));
                    }
                }
            }
        }
        return dist[row - 1][col - 1];
    }

    public static void main(String[] args) throws IOException {
        int[][] test = Test.test2;
        ROW = test.length;
        COL = test[0].length;
        System.out.println(shortestPath(test, ROW, COL) - test[0][0]);
    }
}