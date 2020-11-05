import java.io.*;
import java.util.*;

public class Main {
    public static Set<String> dictionaryWords;
    public static char[][] grid =   {
                                        {'P', 'E', 'R', 'S'},
                                        {'L', 'A', 'T', 'G'},
                                        {'S', 'I', 'N', 'E'},
                                        {'T', 'E', 'R', 'S'}
                                    };
    /*
    public static char[][] grid =   {
                                        {'S', 'E', 'T', 'S'},
                                        {'P', 'I', 'R', 'D'},
                                        {'L', 'A', 'N', 'E'},
                                        {'S', 'E', 'T', 'S'}
                                    };
    */
    /*
    public static char[][] grid =   {
                                        {'D', 'R', 'C', 'K'},
                                        {'A', 'E', 'W', 'N'},
                                        {'I', 'T', 'S', 'U'},
                                        {'O', 'N', 'G', 'E'}
                                    };
    */

    public static void main(String[] args) throws FileNotFoundException {
        long startTimeInMillis = System.currentTimeMillis();

        dictionaryWords = new HashSet<>();  // DEMO 1
        loadDictionary("dictionary words small.txt");  // DEMO 2
        Set<String> validWordSet = solve();  // DEMO 3
        List<ValidWord> validWordsArr = new ArrayList<>();  // DEMO 7
        for (String word: validWordSet){
            validWordsArr.add(new ValidWord(word));
            // System.out.println(word);
        }
        Collections.sort(validWordsArr);  // DEMO 9
        for (ValidWord validWordObj: validWordsArr){
            System.out.println(validWordObj);
        }
        System.out.println("Total: " + validWordsArr.size());

        long endTimeInMillis = System.currentTimeMillis();
        System.out.println("Execution time: " + (endTimeInMillis - startTimeInMillis) / 1000.0 + " s");
    }

    public static void loadDictionary(String filePath) throws FileNotFoundException {
        File file = new File(filePath);
        Scanner sc = new Scanner(file);
        while (sc.hasNextLine()) {
            dictionaryWords.add(sc.nextLine().toUpperCase());
        }
    }

    public static Set<String> solve(){
        Set<String> validWords = new TreeSet<>();
        int gridIndex = 0;
        for (int r = 0; r < grid.length; r++){
            for (int c = 0; c < grid.length; c++){
                validWords.addAll(solve(gridIndex, "", cloneGrid(grid)));  // DEMO 4
                gridIndex++;
            }
        }
        return validWords;
    }

    private static Set<String> solve(int newLetterIndex, String wordSoFar, char[][] remainingGrid){
        Set<String> validWords = new HashSet<>();
        int[] newLetterPos = gridGridPos(newLetterIndex);
        wordSoFar += remainingGrid[newLetterPos[0]][newLetterPos[1]];
        remainingGrid[newLetterPos[0]][newLetterPos[1]] = ' ';
        if (isValidWord(wordSoFar)){
            validWords.add(wordSoFar);
            // System.out.println(wordSoFar);
        }

        // The base case is when this loop only reaches continue statements
        for (int i = -1; i <= 1; i++){  // DEMO 5
            int row = newLetterPos[0] + i;
            if (row < 0 || row >= grid.length){
                continue;
            }
            for (int j = -1; j <= 1; j++){
                int col = newLetterPos[1] + j;
                if (col < 0 || col >= grid.length){
                    continue;
                }
                if (remainingGrid[row][col] == ' '){
                    continue;
                }
                validWords.addAll(solve(getGridIndex(row, col), wordSoFar, cloneGrid(remainingGrid)));  // DEMO 6
            }
        }
        return validWords;
    }

    public static boolean isValidWord(String word){
        return dictionaryWords.contains(word);
    }

    public static int getGridIndex(int row, int col){
        return row * grid.length + col;
    }

    public static int[] gridGridPos(int gridIndex){
        return new int[]{gridIndex / grid.length, gridIndex % grid.length};
    }

    public static char[][] cloneGrid(char[][] gridToClone){
        char[][] gridClone = new char[gridToClone.length][gridToClone[0].length];
        for (int r = 0; r < gridToClone.length; r++){
            for (int c = 0; c < gridToClone.length; c++){
                gridClone[r][c] = gridToClone[r][c];
            }
        }
        return gridClone;
    }
}

class ValidWord implements Comparable<ValidWord> {
    private String word;

    public ValidWord(String word) {
        this.word = word;
    }

    @Override
    public int compareTo(ValidWord other){  // DEMO 8
        if (word.length() != other.word.length()){
            return other.word.length() - word.length();
        }
        return word.compareTo(other.word);
    }

    @Override
    public String toString(){
        return word;
    }

    public String getWord() {
        return word;
    }
    public void setWord(String word) {
        this.word = word;
    }
}