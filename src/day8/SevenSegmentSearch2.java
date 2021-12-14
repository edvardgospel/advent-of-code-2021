package day8;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import static java.lang.String.valueOf;

public class SevenSegmentSearch2 {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new FileReader("test8.txt"))) {
            long result = 0;
            for (String strLine; (strLine = br.readLine()) != null; ) {
                String[] signals = strLine.split(" \\| ");
                String[] signals1 = signals[0].split(" ");
                String[] signals2 = signals[1].split(" ");
                Arrays.sort(signals1, Comparator.comparingInt(String::length));
                Map<Integer, String> map = new HashMap<>();
                for (String signal : signals1) {
                    signal = sort(signal);
                    if (signal.length() == 2) {
                        map.put(1, signal);
                        continue;
                    }
                    if (signal.length() == 3) {
                        map.put(7, signal);
                        continue;
                    }
                    if (signal.length() == 4) {
                        map.put(4, signal);
                        continue;
                    }
                    if (signal.length() == 5) {
                        if (containsAll(signal, map.get(1))) {
                            map.put(3, signal);
                            continue;
                        }
                        int count = 0;
                        for (int i = 0; i < signal.length(); i++) {
                            if (map.get(4).contains(valueOf(signal.charAt(i)))) {
                                count++;
                            }
                        }
                        if (count == 3) {
                            map.put(5, signal);
                        } else {
                            map.put(2, signal);
                        }
                    }
                    if (signal.length() == 6) {
                        if (containsAll(signal, map.get(4))) {
                            map.put(9, signal);
                            continue;
                        }
                        if (containsAll(signal, map.get(1))) {
                            map.put(0, signal);
                            continue;
                        }
                        map.put(6, signal);
                        continue;
                    }
                    if (signal.length() == 7) {
                        map.put(8, signal);
                    }
                }

                StringBuilder strResult = new StringBuilder();
                for (String signal : signals2) {
                    signal = sort(signal);
                    for (Entry<Integer, String> entry : map.entrySet()) {
                        if (signal.equals(entry.getValue())) {
                            strResult.append(entry.getKey());
                        }
                    }
                }
                result += Long.parseLong(strResult.toString());
            }
            System.out.println(result);
        } catch (IOException e) {
            System.out.println("Error while reading file.");
        }
    }

    private static boolean containsAll(String signal, String s) {
        for (int i = 0; i < s.length(); i++) {
            if (!signal.contains(valueOf(s.charAt(i)))) {
                return false;
            }
        }
        return true;
    }

    private static String sort(String signal) {
        char[] chars = signal.toCharArray();
        Arrays.sort(chars);
        return new String(chars);
    }
}
