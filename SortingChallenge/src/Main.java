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
        MERGE_SORT, RADIX_SORT, COUNTING_SORT, BUCKET_SORT, QUICK_SORT
    }

    private static DataFile dataFile = DataFile.RANDOM3;
    private static DataReadingMethod dataReadingMethod = DataReadingMethod.BUFFERED_READER;
    private static SortingMethod sortingMethod = SortingMethod.COUNTING_SORT;

    private static List arr;
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
            case BUCKET_SORT:
                bucketSort(1000000000);  // Currently does not work
                break;
            case QUICK_SORT:
                quickSort(arr);
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

    // Source: https://www.programiz.com/dsa/bucket-sort
    public static <T> void bucketSort(int n) {
        if (n <= 0)
            return;
        @SuppressWarnings("unchecked")
        List<T>[] bucket = new ArrayList[n];

        // Create empty buckets
        for (int i = 0; i < n; i++)
            bucket[i] = new ArrayList<T>();

        // Add elements into the buckets
        for (int i = 0; i < n; i++) {
            int bucketIndex = (int) arr.get(i) * n;
            bucket[bucketIndex].add((T) arr.get(i));
        }

        // Sort the elements of each bucket
        for (int i = 0; i < n; i++) {
            Collections.sort((List)(bucket[i]));
        }

        // Get the sorted array
        int index = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0, size = bucket[i].size(); j < size; j++) {
                arr.set(index++, bucket[i].get(j));
            }
        }
    }

    // Source: https://gist.github.com/djitz/2152957
    private static < T > List  quickSort(List < T > input) {

        if (input.size() <= 1) {
            return input;
        }

        int middle = (int) Math.ceil((double) input.size() / 2);
        T pivot = input.get(middle);

        List < T > less = new ArrayList < T > ();
        List < T > greater = new ArrayList < T > ();

        for (int i = 0; i < input.size(); i++) {
            boolean b;
            try {
                b = (double)input.get(i) <= (double)pivot;
            } catch (ClassCastException e){
                b = (int)input.get(i) <= (int)pivot;
            }
            if (b) {
                if (i == middle) {
                    continue;
                }
                less.add(input.get(i));
            } else {
                greater.add(input.get(i));
            }
        }

        return concatenate(quickSort(less), pivot, quickSort(greater));
    }
    private static < T > List  concatenate(List < T > less, T pivot, List < T > greater) {

        List < T > list = new ArrayList < T > ();

        for (int i = 0; i < less.size(); i++) {
            list.add(less.get(i));
        }

        list.add(pivot);

        for (int i = 0; i < greater.size(); i++) {
            list.add(greater.get(i));
        }

        return list;
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
