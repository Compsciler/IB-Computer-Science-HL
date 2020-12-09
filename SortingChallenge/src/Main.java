import java.io.*;
import java.util.*;

public class Main {
    private enum DataFile {
        RANDOM1, RANDOM2, RANDOM3
    }
    private enum DataReadingMethod {
        SCANNER, BUFFERED_READER
    }
    private enum SortingMethod {
        MERGE_SORT, RADIX_SORT, COUNTING_SORT
    }

    private static DataFile dataFile = DataFile.RANDOM1;
    private static DataReadingMethod dataReadingMethod = DataReadingMethod.BUFFERED_READER;
    private static SortingMethod sortingMethod = SortingMethod.COUNTING_SORT;

    private static ArrayList arr;
    private static PrintWriter out;

    public static void main(String[] args) throws FileNotFoundException, IOException {
        long startTime = System.nanoTime();
        readData();

        long sortStartTime = System.nanoTime();
        switch (sortingMethod){
            case MERGE_SORT:
                mergeSort();
                break;
            case RADIX_SORT:
                radixSort();
                break;
            case COUNTING_SORT:
                countingSort(100000);  // Only for random1.txt
                break;
        }
        long sortTime = (System.nanoTime() - sortStartTime) / 1000000;
        System.out.println("Sort time: " + sortTime + " ms");

        out.println(arr);
        long totalTime = (System.nanoTime() - startTime) / 1000000;
        System.out.println("Total time: " + totalTime + " ms");
    }

    public static void mergeSort(){
        Collections.sort(arr);
    }

    // Source: https://www.java67.com/2018/03/how-to-implement-radix-sort-in-java.html
    public static void radixSort(){
        final int RADIX = 10;
        List < Integer > [] bucket = new ArrayList[RADIX];
        for (int i = 0; i < bucket.length; i++) {
            bucket[i] = new ArrayList < Integer > ();
        }
        boolean maxLength = false;
        int tmp = -1, placement = 1;
        while (!maxLength) {
            maxLength = true;
            for (Object obj : arr) {
                int i = (int) obj;
                tmp = i / placement;
                bucket[tmp % RADIX].add(i);
                if (maxLength && tmp > 0) {
                    maxLength = false;
                }
            }
            int a = 0;
            for (int b = 0; b < RADIX; b++) {
                for (Integer i : bucket[b]) {
                    arr.set(a++, i);
                }
                bucket[b].clear();
            }
            placement *= RADIX;
        }
    }

    // Source: https://www.java67.com/2017/06/counting-sort-in-java-example.html
    public static void countingSort(int k) {  // k is the maximum of the values to be sorted
        int counter[] = new int[k + 1];
        for (Object obj: arr) {
            int i = (int) obj;
            counter[i]++;
        }
        int ndx = 0;
        for (int i = 0; i < counter.length; i++) {
            while (0 < counter[i]) {
                arr.set(ndx++, i);
                counter[i]--;
            }
        }
    }

    public static void readData() throws FileNotFoundException, IOException {
        String fileName = "";
        switch (dataFile){
            case RANDOM1:
                arr = new ArrayList<Integer>();
                fileName = "random1.txt";
                break;
            case RANDOM2:
                arr = new ArrayList<Integer>();
                fileName = "random2.txt";
                break;
            case RANDOM3:
                arr = new ArrayList<Double>();
                fileName = "random3.txt";
        }

        switch (dataReadingMethod){
            case SCANNER:
                Scanner sc = new Scanner(new File(fileName));
                switch (dataFile){
                    case RANDOM1:
                    case RANDOM2:
                        while (sc.hasNextInt()){
                            arr.add(sc.nextInt());
                            // sc.nextLine();
                        }
                        break;
                    case RANDOM3:
                        while (sc.hasNextDouble()){
                            arr.add(sc.nextDouble());
                            // sc.nextLine();
                        }
                }
                break;

            case BUFFERED_READER:
                BufferedReader f = new BufferedReader(new FileReader(fileName));
                switch (dataFile){
                    case RANDOM1:
                    case RANDOM2:
                        for (String line = f.readLine(); line != null; line = f.readLine()){
                            arr.add(Integer.parseInt(line));
                        }
                        break;
                    case RANDOM3:
                        for (String line = f.readLine(); line != null; line = f.readLine()){
                            arr.add(Double.parseDouble(line));
                        }
                }
        }
        out = new PrintWriter(new BufferedWriter(new FileWriter("out.txt")));
    }
}
