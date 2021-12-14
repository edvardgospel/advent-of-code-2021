package day8;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class SevenSegmentSearch1 {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new FileReader("test8.txt"))) {
            int count = 0;
            for (String strLine; (strLine = br.readLine()) != null; ) {
                String[] lines = strLine.split(" \\| ");
                String[] lines2 = lines[1].split(" ");
                for (String line : lines2) {
                    int length = line.length();
                    if (length == 2 || length == 3 || length == 4 || length == 7) {
                        count++;
                    }
                }
            }


            System.out.println("Result: " + count);
        } catch (IOException e) {
            System.out.println("Error while reading file.");
        }
    }
}
