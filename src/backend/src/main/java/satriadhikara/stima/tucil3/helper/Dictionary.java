package satriadhikara.stima.tucil3.helper;

import java.io.*;
import java.util.*;

public class Dictionary {
    public static Set<String> words;

    static {
        try {
            words = loadWordsFromFile();
            System.out.println("Dictionary loaded successfully.");
        } catch (Exception e) {
            System.out.println("Error loading dictionary: " + e.getMessage());
        }
    }

    private static Set<String> loadWordsFromFile() throws Exception {
        Set<String> words = new HashSet<>();
        try {
            File file = new File("src/main/resources/words_oracle.txt");
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String word = scanner.nextLine().trim();
                words.add(word);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            throw new Exception("File not found");
        }
        return words;
    }

    public static Set<String> getWordNeighbors(String currentWord, Set<String> visited) {
        Set<String> neighbors = new HashSet<>();
        StringBuilder wordBuilder = new StringBuilder(currentWord);

        for (int i = 0; i < wordBuilder.length(); i++) {
            char originalChar = wordBuilder.charAt(i);
            for (char c = 'a'; c <= 'z'; c++) {
                if (c != originalChar) {
                    wordBuilder.setCharAt(i, c);
                    String newWord = wordBuilder.toString();
                    if (words.contains(newWord) && !visited.contains(newWord)) {
                        neighbors.add(newWord);
                    }
                }
            }
            wordBuilder.setCharAt(i, originalChar);
        }

        return neighbors;
    }

    public static boolean isWordExist(String word) {
        return words.contains(word);
    }

    public static boolean isOneLetterDifferent(String word1, String word2) {
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
