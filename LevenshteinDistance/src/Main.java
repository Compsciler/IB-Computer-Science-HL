import java.io.*;
import java.util.*;

public class Main {
    public static Set<String> dictionaryWords;
    public static Map<String, Set<String>> dictionaryMap;

    public static String startWord = "cat";  // Must come from dictionary
    public static String endWord = "dog";
    public static Queue<String> wordsToCheck;
    public static Set<String> checkedWords;
    public static Map<String, String> parentWordMap;

    public static void main(String[] args) throws FileNotFoundException {
        Calendar calendar = Calendar.getInstance();
        float startTimeInMillis = calendar.getTimeInMillis();

        dictionaryWords = new HashSet<>();
        dictionaryMap = new HashMap<>();

        loadDictionary("dictionary words smaller.txt");
        // System.out.println(dictionaryMap);

        List<String> levSequence = calculateLevDistance(startWord, endWord);
        System.out.println(levSequence);

        float endTimeInMillis = calendar.getTimeInMillis();
        System.out.println("Execution time: " + (endTimeInMillis - startTimeInMillis) * 1000 + " s");
    }

    public static List<String> calculateLevDistance(String word1, String word2){
        wordsToCheck = new PriorityQueue<>();
        checkedWords = new HashSet<>();
        parentWordMap = new HashMap<>();  // Key word's parent is value word
        boolean isWord2Found = false;

        addAdjacentWords(word1);
        while (!wordsToCheck.isEmpty()){
            String currentWord = wordsToCheck.poll();
            if (currentWord.equals(word2)){
                isWord2Found = true;
                break;
            }
            addAdjacentWords(currentWord);
        }

        LinkedList<String> res = new LinkedList<>();
        if (isWord2Found){
            res.addFirst(word2);
            while (!res.getFirst().equals(word1)){
                String parentWord = parentWordMap.get(res.getFirst());
                res.addFirst(parentWord);
            }
        }
        return res;
    }

    public static void addAdjacentWords(String word){
        Set<String> adjacentWords = dictionaryMap.get(word);
        adjacentWords.removeAll(checkedWords);
        for (String adjacentWord: adjacentWords){
            wordsToCheck.add(adjacentWord);
            parentWordMap.put(adjacentWord, word);
        }
        checkedWords.add(word);
    }

    /*
    public static void addList(String word){
        LinkedList<String> linkedList = new LinkedList<>();
        for (String s: dictionaryMap.keySet()){
            if (!checkedWords.contains(s)){
                linkedList.add(s);
            }
        }
        wordsToCheck.add(linkedList);
        checkedWords.add(word);
    }

    public static void addList(){
        LinkedList<String> linkedList = new LinkedList<>();
        linkedList.add(startWord);
        wordsToCheck.add(linkedList);
        checkedWords.add(startWord);
    }
    */

    public static void loadDictionary(String filePath) throws FileNotFoundException {
        File file = new File(filePath);
        Scanner sc = new Scanner(file);

        // Reduces n^2 searches to n(n-1)/2
        while (sc.hasNextLine()){
            String newWord = sc.nextLine();
            for (String dictionaryWord: dictionaryWords){
                if (areWordsLevAdjacent(newWord, dictionaryWord)){
                    updateMap(newWord, dictionaryWord);
                }
            }
            dictionaryWords.add(newWord);
        }
    }

    public static boolean areWordsLevAdjacent(String word1, String word2){
        if (word1.length() != word2.length()){
            return false;
        }

        int differentLetterTotal = 0;
        for (int i = 0; i < word1.length(); i++){
            if (word1.charAt(i) != word2.charAt(i)){
                differentLetterTotal++;
                if (differentLetterTotal >= 2){
                    return false;
                }
            }
        }
        if (differentLetterTotal == 0){
            return false;
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