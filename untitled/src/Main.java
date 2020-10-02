import java.io.*;
import java.util.*;
public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Map<String, Integer> words = new HashMap<>();
        File file = new File("file.txt");
        Scanner sc = new Scanner(file);
        while (sc.hasNext()){
            String word = sc.next();
            if (words.containsKey(word)){
                words.put(word, words.get(word) + 1);
            } else {
                words.put(word, 1);
            }
            /*
            if ((word.charAt(0) == 'a' || word.charAt(0) == 'A') && word.length() == 5){
                tree.add(sc.next());
            }
            */
        }
        Map<Integer, Set<String>> wordsByOccurences = new TreeMap<>();
        for (Map.Entry<String, Integer> entry : words.entrySet()){
            if (entry.getValue() >= 100){
                if (wordsByOccurences.containsKey(entry.getValue())){
                    wordsByOccurences.get(wordsByOccurences.get(entry.getValue())).add(entry.getKey());
                } else {
                    Set<String> set = new TreeSet<>();
                    set.add(entry.getKey());
                    wordsByOccurences.put(entry.getValue(), set);
                }
            }
        }
    }
}
