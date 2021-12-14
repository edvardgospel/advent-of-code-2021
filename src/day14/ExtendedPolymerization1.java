package day14;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ExtendedPolymerization1 {

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new FileReader("test14.txt"))) {
            String polymerTemplate = br.readLine();
            br.readLine();
            Map<String, String> pairInsertionRules = initPairInsertionRules(br);

            StringBuilder str = new StringBuilder().append(polymerTemplate.charAt(0));
            for (int steps = 0; steps < 10; steps++) {
                for (int i = 0; i < polymerTemplate.length() - 1; i++) {
                    String el = "" + polymerTemplate.charAt(i) + polymerTemplate.charAt(i + 1);
                    String s = pairInsertionRules.get(el);
                    str.append(s).append(polymerTemplate.charAt(i + 1));
                }
                polymerTemplate = str.toString();
                str = new StringBuilder().append(polymerTemplate.charAt(0));
            }

            String mostCommonElement = getMostCommonElement(polymerTemplate.split(""), polymerTemplate.length());
            String leastCommonElement = getLeastCommonElement(polymerTemplate.split(""), polymerTemplate.length());

            System.out.println(polymerTemplate);
            int mostFr = Collections.frequency(Arrays.asList(polymerTemplate.split("")), mostCommonElement);
            int leastFr = Collections.frequency(Arrays.asList(polymerTemplate.split("")), leastCommonElement);
            System.out.println(mostFr);
            System.out.println(leastFr);
            System.out.println(mostFr - leastFr);


        } catch (IOException e) {
            System.out.println("Error while reading file.");
        }
    }

    private static Map<String, String> initPairInsertionRules(BufferedReader br) throws IOException {
        Map<String, String> pairInsertionRules = new HashMap<>();
        for (String line; (line = br.readLine()) != null; ) {
            String[] splitted = line.split(" -> ");
            pairInsertionRules.put(splitted[0], splitted[1]);
        }
        return pairInsertionRules;
    }

    static String getMostCommonElement(String[] arr, int n) {
        int res = 0;
        int count = 1;
        for (int i = 1; i < n; i++) {
            if (arr[i].equals(arr[res])) {
                count++;
            } else {
                count--;
            }
            if (count == 0) {
                res = i;
                count = 1;
            }
        }
        return arr[res];
    }

    static String getLeastCommonElement(String arr[], int n) {

        Map<String, Integer> count = new HashMap<>();

        for (int i = 0; i < n; i++) {
            String key = arr[i];
            if (count.containsKey(key)) {
                int freq = count.get(key);
                freq++;
                count.put(key, freq);
            } else
                count.put(key, 1);
        }

        // find min frequency.
        int min_count = n + 1;
        String res = "";
        for (Map.Entry<String, Integer> val : count.entrySet()) {
            if (min_count >= val.getValue()) {
                res = val.getKey();
                min_count = val.getValue();
            }
        }

        return res;
    }


}
