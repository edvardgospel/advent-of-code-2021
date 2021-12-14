package day14;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static java.util.Collections.max;
import static java.util.Collections.min;

public class ExtendedPolymerization2 {

    public static void main(String[] args) {//TODO refactor
        try (BufferedReader br = new BufferedReader(new FileReader("test14_2.txt"))) {
            String polymerTemplate = br.readLine();
            br.readLine();
            Map<String, String> pairInsertionRules = initPairInsertionRules(br);
            Map<String, Long> pairCounter = initPairCounter(polymerTemplate);
            Map<String, Long> singletonCounter = initSingletonCounter(polymerTemplate);

            for (int step = 1; step <= 40; step++) {
                Map<String, Long> toAdd = new HashMap<>();
                Map<String, Long> toRemove = new HashMap<>();
                for (String pair : pairCounter.keySet()) {
                    String character = pairInsertionRules.get(pair);
                    String firstChar = pair.split("")[0];
                    String secondChar = pair.split("")[1];

                    if (toAdd.containsKey(firstChar + character)) {
                        toAdd.put(firstChar + character, toAdd.get(firstChar + character) + pairCounter.get(pair));
                    } else {
                        toAdd.put(firstChar + character, pairCounter.get(pair));
                    }

                    if (toAdd.containsKey(character + secondChar)) {
                        toAdd.put(character + secondChar, toAdd.get(character + secondChar) + pairCounter.get(pair));
                    } else {
                        toAdd.put(character + secondChar, pairCounter.get(pair));
                    }

                    if (toAdd.containsKey(character)) {
                        toAdd.put(character, toAdd.get(character) + pairCounter.get(pair));
                    } else {
                        toAdd.put(character, pairCounter.get(pair));
                    }

                    if (toRemove.containsKey(pair)) {
                        toRemove.put(pair, toRemove.get(pair) + pairCounter.get(pair));
                    } else {
                        toRemove.put(pair, pairCounter.get(pair));
                    }

                }
                for (Map.Entry<String, Long> add : toAdd.entrySet()) {
                    if (add.getKey().length() == 1) {
                        singletonCounter.merge(add.getKey(), add.getValue(), Long::sum);
                    } else {
                        pairCounter.merge(add.getKey(), add.getValue(), Long::sum);
                    }
                }

                for (Map.Entry<String, Long> remove : toRemove.entrySet()) {
                    Long pair = pairCounter.get(remove.getKey());
                    pairCounter.put(remove.getKey(), pair - remove.getValue());
                }
            }

            //System.out.println(polymerTemplate);
            //System.out.println(pairInsertionRules);
            //System.out.println(pairCounter);
            //System.out.println(singletonCounter);
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
