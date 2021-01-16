import java.io.*;
import java.util.*;

public class Main {
    private static List<String> killRing;
    private static List<String> graveyard;

    public static void main(String[] args) throws FileNotFoundException {
        killRing = new LinkedList2<>();
        graveyard = new ArrayList<>();
        // loadKillRing("input.txt");

    }

    public static void loadKillRing(String filePath) throws FileNotFoundException {
        File file = new File(filePath);
        Scanner sc = new Scanner(file);
        while (sc.hasNextLine()){
            killRing.add(sc.nextLine());
        }
    }

    public static void kill(String killer){
        // List<String> killRingClone = new LinkedList<>(killRing);
        // Iterator<String> killRingIter = killRingClone.iterator();
        Iterator<String> killRingIter = killRing.iterator();
        while (killRingIter.hasNext()){
            String s = killRingIter.next();
            if (killRingIter.next().equals(killer)){

            }
        }
    }
}

class LinkedList2 <T> extends LinkedList<T> {
    @Override
    public int indexOf(Object o){
        Iterator<T> iter = iterator();
        int index = 0;
        while (iter.hasNext()){
            if (iter.next().equals(o)){
                return index;
            }
            index++;
        }
        return -1;
    }
}