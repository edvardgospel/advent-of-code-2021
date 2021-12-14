package day2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Dive1 {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new FileReader("test3.txt"))) {
            int sumOfBinaries = 0;
            int[] counter = new int[0];
            for (String strBinary; (strBinary = br.readLine()) != null; ) {
                sumOfBinaries++;
                if (sumOfBinaries == 1) {
                    counter = new int[strBinary.length()];
                }
                for (int i = 0; i < strBinary.length(); i++) {
                    if (strBinary.charAt(i) == '1') {
                        counter[i] = counter[i] + 1;
                    }
                }
            }
            StringBuilder gammaRate = new StringBuilder();
            StringBuilder epsilonRate = new StringBuilder();
            for (int i : counter) {
                if (i > sumOfBinaries / 2) {
                    gammaRate.append("1");
                    epsilonRate.append("0");
                } else {
                    gammaRate.append("0");
                    epsilonRate.append("1");
                }
            }
            System.out.println(Integer.parseInt(gammaRate.toString(),2) * Integer.parseInt(epsilonRate.toString(),2));
        } catch (IOException e) {
            System.out.println("Error while reading file.");
        }
    }
}
