import java.io.*;
import java.util.*;
public class Main {
    public static void main(String[] args) {
        // printBinary(-59595);
        // System.out.println(isPalindrome("raccecar"));
        // crawl("C:\\Users\\roger\\OneDrive\\Desktop\\IB-Computer-Science-HL\\untitled");
        // doubleReverse("hello");
        int[] arr = {2, 3, 5, 7, 11, 13, 17, 19};
        System.out.println(binarySearch(arr, 10));
    }
    public static void printBinary(int n){
        System.out.println(binaryString(Math.abs(n), (n < 0)));
    }
    public static String binaryString(int n, boolean isNegative){
        if (n == 0){
            if (isNegative){
                return "-";
            }
            return "";
        }
        String lastDigit = Integer.toString(n % 2);
        return binaryString(n / 2, isNegative) + lastDigit;
    }
    public static boolean isPalindrome(String s){
        if (s.length() < 2){
            return true;
        }
        if (s.charAt(0) != s.charAt(s.length() - 1)){
            return false;
        }
        return isPalindrome(s.substring(1, s.length() - 1));
    }

    private static void crawl(File f, int level){
        for (int i = 0; i < level; i++){
            System.out.print("\t");
        }
        System.out.println(f.getName());
        if (f.isDirectory()){
            for (File f2: f.listFiles()){
                crawl(f2, level + 1);
            }
        }
    }
    public static void crawl(String s){
        File f = new File(s);
        crawl(f, 0);
    }

    public static void doubleReverse(String s){
        if (s.length() <= 0){
            return;
        }
        doubleReverse(s.substring(1));
        System.out.print(s.charAt(0));
        System.out.print(s.charAt(0));
    }

    public static int binarySearch(int[] arr, int n){
        return binarySearch(arr, n, 0, arr.length - 1);
    }

    private static int binarySearch(int[] arr, int n, int minIndex, int maxIndex){
        if (minIndex > maxIndex){
            return -(minIndex + 1);
        }
        int midIndex = (minIndex + maxIndex) / 2;
        if (arr[midIndex] == n){
            return midIndex;
        }
        if (arr[midIndex] < n){
            return binarySearch(arr, n, midIndex + 1, maxIndex);
        }
        return binarySearch(arr, n, minIndex, midIndex - 1);
    }
}