package day3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

class BinaryDiagnostic2 {

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new FileReader("test3.txt"))) {
            Map<Integer, List<String>> binaries = new HashMap<>();
            for (String strBinary; (strBinary = br.readLine()) != null; ) {
                binaries.computeIfAbsent(0, integer -> new ArrayList<>()).add(strBinary);
            }

            int k = 0;
            while (binaries.get(k).size() > 1) {
                char toRemove = whatToRemove(binaries.get(k), k);
                for (int i = 0; i < binaries.get(k).size(); i++) {
                    if (binaries.get(k).get(i).charAt(k) != toRemove) {
                        binaries.computeIfAbsent(k + 1, integer -> new ArrayList<>()).add(binaries.get(k).get(i));
                    }
                }
                k++;
            }

            String oxygenGenerator = binaries.get(Collections.max(binaries.keySet())).get(0);
            binaries.keySet().removeIf(kk -> !(kk.equals(0)));

            k = 0;
            while (binaries.get(k).size() > 1) {
                char toRemove = whatToRemove2(binaries.get(k), k);
                for (int i = 0; i < binaries.get(k).size(); i++) {
                    if (binaries.get(k).get(i).charAt(k) != toRemove) {
                        binaries.computeIfAbsent(k + 1, integer -> new ArrayList<>()).add(binaries.get(k).get(i));
                    }
                }
                k++;
            }

            String CO2scrubber = binaries.get(Collections.max(binaries.keySet())).get(0);
            System.out.println(Integer.parseInt(oxygenGenerator, 2));
            System.out.println(Integer.parseInt(CO2scrubber, 2));
            System.out.println(Integer.parseInt(oxygenGenerator, 2) * Integer.parseInt(CO2scrubber, 2));
        } catch (IOException e) {
            System.out.println("Error while reading file.");
        }
    }

    private static char whatToRemove(List<String> strings, int i) {
        int oneSum = countOnesAtPosition(strings, i);
        int zeroSum = counZerosAtPosition(strings, i);
        if (oneSum >= zeroSum) {
            return '0';
        }
        return '1';
    }

    private static char whatToRemove2(List<String> strings, int i) {
        int oneSum = countOnesAtPosition(strings, i);
        int zeroSum = counZerosAtPosition(strings, i);
        if (oneSum >= zeroSum) {
            return '1';
        }
        return '0';
    }

    private static int countOnesAtPosition(List<String> strings, int i) {
        int counter = 0;
        for (int j = 0; j < strings.size(); j++) {
            if (strings.get(j).charAt(i) == '1') {
                counter++;
            }
        }
        return counter;
    }

    private static int counZerosAtPosition(List<String> strings, int i) {
        int counter = 0;
        for (int j = 0; j < strings.size(); j++) {
            if (strings.get(j).charAt(i) == '0') {
                counter++;
            }
        }
        return counter;
    }

}