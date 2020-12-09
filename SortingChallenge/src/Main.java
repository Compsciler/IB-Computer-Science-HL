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
        DEFAULT_SORT
    }

    private static DataFile dataFile = DataFile.RANDOM2;
    private static DataReadingMethod dataReadingMethod = DataReadingMethod.BUFFERED_READER;
    private static SortingMethod sortingMethod = SortingMethod.DEFAULT_SORT;

    private static ArrayList arr;
    private static PrintWriter out;

    public static void main(String[] args) throws FileNotFoundException, IOException {
        long startTime = System.nanoTime();
        readData();

        long sortStartTime = System.nanoTime();
        switch (sortingMethod){
            case DEFAULT_SORT:
                defaultSort();
                break;
        }
        long sortTime = (System.nanoTime() - sortStartTime) / 1000000;
        System.out.println("Sort time: " + sortTime + " ms");

        out.println(arr);
        long totalTime = (System.nanoTime() - startTime) / 1000000;
        System.out.println("Total time: " + totalTime + " ms");
    }

    public static void defaultSort(){
        Collections.sort(arr);
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
                            arr.add(Double.parseDouble(line));
                        }
                        break;
                    case RANDOM3:
                        for (String line = f.readLine(); line != null; line = f.readLine()){
                            arr.add(Integer.parseInt(line));
                        }
                }
        }
        out = new PrintWriter(new BufferedWriter(new FileWriter("out.txt")));
    }
}
