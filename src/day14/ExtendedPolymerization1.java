package day14;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Collections.max;
import static java.util.Collections.min;

public class ExtendedPolymerization1 {

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new FileReader("test14_1.txt"))) {
            String polymerTemplate = br.readLine();
            br.readLine();
            Map<String, String> pairInsertionRules = initPairInsertionRules(br);
            Map<String, Long> pairCounter = initPairCounter(polymerTemplate);
            Map<String, Long> singletonCounter = initSingletonCounter(polymerTemplate);

            for (int step = 1; step <= 10; step++) {
                System.out.println(step);
                List<String> toAdd = new ArrayList<>();
                List<String> toRemove = new ArrayList<>();
                for (String pair : pairCounter.keySet()) {
                    for (int counter = 0; counter < pairCounter.get(pair); counter++) {
                        String character = pairInsertionRules.get(pair);
                        String firstChar = pair.split("")[0];
                        String secondChar = pair.split("")[1];
                        toAdd.add(firstChar + character);
                        toAdd.add(character + secondChar);
                        toAdd.add(character);
                        toRemove.add(pair);
                    }
                }

                for (String add : toAdd) {
                    if (add.length() == 1) {
                        Long singleton = singletonCounter.get(add);
                        if (singleton == null) {
                            singletonCounter.put(add, 1L);
                        } else {
                            singletonCounter.put(add, singleton + 1);
                        }
                    } else {
                        Long pair = pairCounter.get(add);
                        if (pair == null) {
                            pairCounter.put(add, 1L);
                        } else {
                            pairCounter.put(add, pair + 1);
                        }
                    }
                }

                for (String remove : toRemove) {
                    Long pair = pairCounter.get(remove);
                    pairCounter.put(remove, pair - 1);
                }
            }

            System.out.println(polymerTemplate);
            System.out.println(pairInsertionRules);
            System.out.println(pairCounter);
            System.out.println(singletonCounter);
            System.out.println(max(singletonCounter.values()) - min(singletonCounter.values()));

        } catch (IOException e) {
            System.out.println("Error while reading file.");
        }
    }

    private static Map<String, Long> initPairCounter(String polymerTemplate) {
        Map<String, Long> pairCounter = new HashMap<>();
        for (int i = 0; i < polymerTemplate.length() - 1; i++) {
            pairCounter.merge(polymerTemplate.substring(i, i + 2), 1L, Long::sum);
        }
        return pairCounter;
    }

    private static Map<String, Long> initSingletonCounter(String polymerTemplate) {
        Map<String, Long> singletonCounter = new HashMap<>();
        for (int i = 0; i < polymerTemplate.length(); i++) {
            singletonCounter.merge(polymerTemplate.substring(i, i + 1), 1L, Long::sum);
        }
        return singletonCounter;
    }

    private static Map<String, String> initPairInsertionRules(BufferedReader br) throws IOException {
        Map<String, String> pairInsertionRules = new HashMap<>();
        for (String line; (line = br.readLine()) != null; ) {
            String[] splitted = line.split(" -> ");
            pairInsertionRules.put(splitted[0], splitted[1]);
        }
        return pairInsertionRules;
    }

}
