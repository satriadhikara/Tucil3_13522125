package satriadhikara.stima.tucil3.helper;

import java.io.*;
import java.util.*;

public class Dictionary {
    public static Set<String> words;

    static {
        try {
            words = loadWordsFromFile();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static Set<String> loadWordsFromFile() throws Exception {
        Set<String> words = new HashSet<>();
        try {
            File file = new File("src/main/resources/words_alpha.txt");
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String word = scanner.nextLine();
                words.add(word);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            throw new Exception("File not found");
        }
        return words;
    }

    public static List<String> getWordNeighbors(String word) {
        List<String> neighbors = new ArrayList<>();
        for (String w : words) {
            if (isOneLetterDifferent(word, w)) {
                neighbors.add(w);
            }
        }
        return neighbors;
    }

    public static Boolean isWordExist(String word) {
        return words.contains(word);
    }

    public static Boolean isOneLetterDifferent(String word1, String word2) {
        if (word1.length() != word2.length()) {
            return false;
        }
        int count = 0;
        for (int i = 0; i < word1.length(); i++) {
            if (word1.charAt(i) != word2.charAt(i)) {
                if (++count > 1) {
                    return false;
                }
            }
        }
        return count == 1;
    }
}
