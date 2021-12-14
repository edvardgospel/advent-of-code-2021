package day10;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import static java.lang.String.valueOf;
import static java.util.Arrays.asList;

public class SyntaxScoring1 {

    private static final List<String> CLOSING_TAGS = asList(")", "]", "}", ">");
    private static final Map<String, Integer> SCORES = new HashMap<String, Integer>() {{
        put(")", 3);
        put("]", 57);
        put("}", 1197);
        put(">", 25137);
    }};
    private static final Map<String, String> PAIRS = new HashMap<String, String>() {{
        put("(", ")");
        put("[", "]");
        put("{", "}");
        put("<", ">");
    }};

    public static void main(String[] args) {

        try (BufferedReader br = new BufferedReader(new FileReader("test10.txt"))) {
            int result = 0;
            for (String line; (line = br.readLine()) != null; ) {
                result += syntaxScoring(line);
            }
            System.out.println("Result: " + result);
        } catch (IOException e) {
            System.out.println("Error while reading file.");
        }
    }

    private static int syntaxScoring(String line) {
        Stack<String> expectedClosingTags = new Stack<>();
        for (int i = 0; i < line.length(); i++) {
            String character = valueOf(line.charAt(i));
            if (isClosingTag(character)) {
                if (!character.equals(expectedClosingTags.pop())) {
                    return SCORES.get(character);
                }
            } else {
                expectedClosingTags.push(PAIRS.get(character));
            }
        }
        return 0;
    }

    private static boolean isClosingTag(String str) {
        return CLOSING_TAGS.contains(str);
    }
}
