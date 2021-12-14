package day13;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TransparentOrigami2 {

    public static int ROW;//  894   15
    public static int COL;// 1311  11
    public static int X;
    public static int Y;

    public static void main(String[] args) {
        initRowAndCol();
        try (BufferedReader br = new BufferedReader(new FileReader("test13.txt"))) {
            boolean[][] transparentPaper = initTransparentPaper(br);
            List<String> instructions = initInstructions(br);
            for (String instruction : instructions) {
                if (instruction.charAt(0) == 'x') {
                    X = Integer.parseInt(instruction.split("=")[1]);
                    foldLeftTransparentPaper(transparentPaper);
                } else if (instruction.charAt(0) == 'y') {
                    Y = Integer.parseInt(instruction.split("=")[1]);
                    foldUpTransparentPaper(transparentPaper);
                }
            }
            print(transparentPaper);
        } catch (IOException e) {
            System.out.println("Error while reading file.");
        }
    }

    private static boolean[][] initTransparentPaper(BufferedReader br) throws IOException {
        boolean[][] matrix = new boolean[ROW][COL];
        for (String line; (line = br.readLine()) != null; ) {
            if (line.equals("")) {
                break;
            }
            String[] splitted = line.split(",");
            matrix[Integer.parseInt(splitted[1])][Integer.parseInt(splitted[0])] = true;
        }
        return matrix;
    }

    private static List<String> initInstructions(BufferedReader br) throws IOException {
        List<String> instructions = new ArrayList<>();
        for (String line; (line = br.readLine()) != null; ) {
            String instruction = line.split(" ")[2];
            instructions.add(instruction);
        }
        return instructions;
    }

    private static void foldUpTransparentPaper(boolean[][] transparentPaper) {
        for (int i = 0; i < Y; i++) {
            for (int j = 0; j < COL; j++) {
                transparentPaper[i][j] = transparentPaper[i][j] || transparentPaper[ROW - i - 1][j];
            }
        }
        ROW = Y;
    }

    private static void foldLeftTransparentPaper(boolean[][] transparentPaper) {
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < X; j++) {
                transparentPaper[i][j] = transparentPaper[i][j] || transparentPaper[i][COL - j - 1];
            }
        }
        COL = X;
    }

    private static int countDots(boolean[][] transparentPaper) {
        int count = 0;
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                if (transparentPaper[i][j]) {
                    count++;
                }
            }
        }
        return count;
    }

    public static void print(boolean[][] matrix) {
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                System.out.print(matrix[i][j] ? "░" : " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    private static void initRowAndCol() {
        try (BufferedReader br = new BufferedReader(new FileReader("test13.txt"))) {
            int leftMax = 0;
            int rightMax = 0;
            for (String line; !(line = br.readLine()).equals(""); ) {
                String[] splitted = line.split(",");
                if (Integer.parseInt(splitted[0]) > leftMax) {
                    leftMax = Integer.parseInt(splitted[0]);
                }
                if (Integer.parseInt(splitted[1]) > rightMax) {
                    rightMax = Integer.parseInt(splitted[1]);
                }
            }
            ROW = ++rightMax;
            COL = ++leftMax;
        } catch (IOException e) {
            System.out.println("Error while reading file.");
        }
    }

}
