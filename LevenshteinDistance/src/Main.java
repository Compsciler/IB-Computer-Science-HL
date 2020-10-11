import java.io.*;
import java.util.*;

public class Main {
    public static Set<String> dictionaryWords;
    public static Map<String, Set<String>> dictionaryMap;

    public static void main(String[] args) throws FileNotFoundException {
        dictionaryWords = new HashSet<>();
        dictionaryMap = new HashMap<>();

        loadDictionary("C:\\Users\\roger\\OneDrive\\Desktop\\IB-Computer-Science-HL\\LevenshteinDistance\\src\\dictionary words small.txt");
        System.out.println(dictionaryMap);
    }

    public static void loadDictionary(String filePath) throws FileNotFoundException {
        File file = new File(filePath);
        Scanner sc = new Scanner(file);

        // Reduces n^2 searches to n(n-1)/2
        while (sc.hasNextLine()){
            String newWord = sc.nextLine();
            dictionaryWords.add(newWord);
            for (String dictionaryWord: dictionaryWords){
                if (areWordsLevAdjacent(newWord, dictionaryWord)){
                    updateMap(newWord, dictionaryWord);
                }
            }
        }
    }

    public static boolean areWordsLevAdjacent(String word1, String word2){
        if (word1.length() != word2.length()){
            return false;
        }
        for (int i = 0; i < word1.length(); i++){
            if (word1.charAt(i) != word2.charAt(i)){
                return false;
            }
        }
        return true;
    }

    public static void updateMap(String word1, String word2){
        String[][] wordUpdates = {{word1, word2}, {word2, word1}};
        for (String[] wordUpdate: wordUpdates){
            String wordKey = wordUpdate[0];
            String wordValue = wordUpdate[1];

            if (dictionaryMap.containsKey(wordKey)){
                dictionaryMap.get(wordKey).add(wordValue);
            } else {
                Set<String> wordSet = new HashSet<>();
                wordSet.add(wordValue);
                dictionaryMap.put(wordKey, wordSet);
            }
        }
    }
}