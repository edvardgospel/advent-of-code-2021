package day10;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import static java.lang.String.valueOf;
import static java.util.Arrays.asList;

public class SyntaxScoring2 {
    private static final List<String> CLOSING_TAGS = asList(")", "]", "}", ">");
    private static final Map<String, Integer> SCORES = new HashMap<String, Integer>() {{
        put(")", 1);
        put("]", 2);
        put("}", 3);
        put(">", 4);
    }};
    private static final Map<String, String> PAIRS = new HashMap<String, String>() {{
        put("(", ")");
        put("[", "]");
        put("{", "}");
        put("<", ">");
    }};

    public static void main(String[] args) {

        try (BufferedReader br = new BufferedReader(new FileReader("test10.txt"))) {
            List<Long> results = new ArrayList<>();
            for (String line; (line = br.readLine()) != null; ) {
                Stack<String> syntaxScoring = getSyntaxScoring(line);
                calculateResults(results, syntaxScoring);
            }
            results.removeAll(Collections.singleton(0L));
            System.out.println(results.get(results.size() / 2 ));
        } catch (IOException e) {
            System.out.println("Error while reading file.");
        }
    }

    private static void calculateResults(List<Long> results, Stack<String> syntaxScoring) {
        reverseStack(syntaxScoring);
        long result = 0;
        for (String ss : syntaxScoring) {
            result = result * 5 + SCORES.get(ss);
        }
        results.add(result);
        Collections.sort(results);
    }

    private static Stack<String> getSyntaxScoring(String line) {
        Stack<String> expectedClosingTags = new Stack<>();
        for (int i = 0; i < line.length(); i++) {
            String character = valueOf(line.charAt(i));
            if (isClosingTag(character)) {
                if (!character.equals(expectedClosingTags.pop())) {
                    return new Stack<>();
                }
            } else {
                expectedClosingTags.push(PAIRS.get(character));
            }
        }
        return expectedClosingTags;
    }

    private static boolean isClosingTag(String str) {
        return CLOSING_TAGS.contains(str);
    }

    private static <T> void reverseStack(Stack<T> stack) {
        if (stack.isEmpty()) {
            return;
        }
        T bottom = popBottom(stack);
        reverseStack(stack);
        stack.push(bottom);
    }

    private static <T> T popBottom(Stack<T> stack) {
        T top = stack.pop();
        if (stack.isEmpty()) {
            return top;
        } else {
            T bottom = popBottom(stack);
            stack.push(top);
            return bottom;
        }
    }
}
