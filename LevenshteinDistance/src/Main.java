import java.io.*;
import java.util.*;

public class Main {
    public static Set<String> dictionaryWords;
    public static Map<String, Set<String>> dictionaryMap;

    public static String startWord = "";  // Must be in dictionary
    public static String endWord = "";  // Must be in dictionary
    public static Queue<String> wordsToVisit;
    public static Set<String> locatedWords;
    public static Map<String, String> parentWordMap;

    public static final int NUM_WORD_PAIRS = 32;  // DEMO 12

    public static void main(String[] args) throws FileNotFoundException {
        long startTimeInMillis = System.currentTimeMillis();

        dictionaryWords = new HashSet<>();  // DEMO 1
        dictionaryMap = new HashMap<>();

        wordsToVisit = new PriorityQueue<>();
        locatedWords = new HashSet<>();
        parentWordMap = new HashMap<>();  // Key word's parent is value word

        loadDictionary("dictionary words small.txt");  // DEMO 2
        // System.out.println(dictionaryMap);

        pickRandomStartAndEndWordIfEmpty();  // DEMO 3
        printLevDistance(startWord, endWord, false);  // DEMO 5, DEMO 13
        for (int i = 1; i < NUM_WORD_PAIRS; i++) {
            resetLevCalculator();
            pickRandomStartAndEndWordIfEmpty();
            printLevDistance(startWord, endWord, false);
        }

        long endTimeInMillis = System.currentTimeMillis();
        System.out.println("Execution time: " + (endTimeInMillis - startTimeInMillis) / 1000.0 + " s");
    }

    public static List<String> calculateLevDistance(String word1, String word2) {
        if (word1.length() != word2.length()) {
            return null;
        }

        boolean isWord2Found = false;

        updateMapWithLevAdjacent(word1);  // DEMO 7
        addAdjacentWords(word1);  // DEMO 10
        while (!wordsToVisit.isEmpty()) {
            String currentWord = wordsToVisit.poll();
            if (currentWord.equals(word2)) {
                isWord2Found = true;
                break;
            }
            updateMapWithLevAdjacent(currentWord);
            addAdjacentWords(currentWord);
        }

        LinkedList<String> res = new LinkedList<>();
        if (isWord2Found) {
            res.addFirst(word2);
            while (!res.getFirst().equals(word1)) {
                String parentWord = parentWordMap.get(res.getFirst());
                res.addFirst(parentWord);
            }
        }
        return res;
    }

    public static void printLevDistance(String word1, String word2, boolean isPrintingIfUnequalLengths) {
        List<String> levSequence = calculateLevDistance(word1, word2);  // DEMO 6
        int levDistance;
        if (levSequence == null) {
            if (isPrintingIfUnequalLengths) {
                levDistance = -1;
            } else {
                return;
            }
        } else {
            levDistance = (levSequence.size() == 0) ? -1 : (levSequence.size() - 1);
        }
        System.out.println(startWord + " -> " + endWord + ": " + levDistance);
        System.out.println(levSequence);
        System.out.println();
    }

    public static void addAdjacentWords(String word) {
        Set<String> adjacentWords = dictionaryMap.get(word);
        if (adjacentWords != null) {
            adjacentWords.removeAll(locatedWords);
            for (String adjacentWord : adjacentWords) {
                wordsToVisit.add(adjacentWord);  // DEMO 11
                parentWordMap.put(adjacentWord, word);
                locatedWords.add(adjacentWord);  // Moved inside loop to not create long distance of [fig, big, dig, dog]
            }
        }
    }

    public static void loadDictionary(String filePath) throws FileNotFoundException {
        File file = new File(filePath);
        Scanner sc = new Scanner(file);

        // Reduces n^2 searches to n(n-1)/2
        while (sc.hasNextLine()) {
            String newWord = sc.nextLine();
            // updateMapWithLevAdjacent(newWord);
            dictionaryWords.add(newWord);
        }
    }

    public static void updateMapWithLevAdjacent(String word) {
        for (String dictionaryWord : dictionaryWords) {
            if (areWordsLevAdjacent(word, dictionaryWord)) {  // DEMO 8
                updateMap(word, dictionaryWord);  // DEMO 9
            }
        }
    }

    public static boolean areWordsLevAdjacent(String word1, String word2) {
        if (word1.length() != word2.length()) {
            return false;
        }

        int differentLetterTotal = 0;
        for (int i = 0; i < word1.length(); i++) {
            if (word1.charAt(i) != word2.charAt(i)) {
                differentLetterTotal++;
                if (differentLetterTotal >= 2) {
                    return false;
                }
            }
        }
        return (differentLetterTotal != 0);
    }

    public static void updateMap(String word1, String word2) {
        String[][] wordUpdates = {{word1, word2}, {word2, word1}};
        for (String[] wordUpdate : wordUpdates) {
            String wordKey = wordUpdate[0];
            String wordValue = wordUpdate[1];

            if (dictionaryMap.containsKey(wordKey)) {
                dictionaryMap.get(wordKey).add(wordValue);
            } else {
                Set<String> wordSet = new HashSet<>();
                wordSet.add(wordValue);
                dictionaryMap.put(wordKey, wordSet);
            }
        }
    }

    public static List<String> pickRandomWordsFromDictionary(int numWords) {
        Random rand = new Random();
        TreeSet<Integer> randomIndices = new TreeSet<>();
        int dictionarySize = dictionaryWords.size();
        for (int i = 0; i < numWords; i++) {
            randomIndices.add(rand.nextInt(dictionarySize));
        }
        List<String> res = new ArrayList<>();
        int i = 0;
        for (String s : dictionaryWords) {
            if (i == randomIndices.first()) {
                res.add(s);
                randomIndices.pollFirst();
                if (randomIndices.isEmpty()) {
                    break;
                }
            }
            i++;
        }
        return res;
    }

    public static void pickRandomStartAndEndWordIfEmpty() {
        int randomWordTotal = 0;
        randomWordTotal += (startWord.equals("")) ? 1 : 0;
        randomWordTotal += (endWord.equals("")) ? 1 : 0;

        if (randomWordTotal >= 1) {
            List<String> randomWords = pickRandomWordsFromDictionary(randomWordTotal);  // DEMO 4
            if (startWord.equals("")) {
                startWord = randomWords.get(0);
                randomWords.remove(0);
            }
            if (endWord.equals("")) {
                endWord = randomWords.get(0);
                randomWords.remove(0);
            }
        }
    }

    public static void resetLevCalculator() {
        startWord = "";
        endWord = "";
        wordsToVisit.clear();
        locatedWords.clear();
        parentWordMap.clear();
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
}