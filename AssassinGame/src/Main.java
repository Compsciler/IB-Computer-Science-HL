import java.io.*;
import java.util.*;

public class Main {
    private static List<String> killRing;
    private static List<String> graveyard;

    public static void main(String[] args) throws FileNotFoundException {
        killRing = new LinkedList<>();
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
        List<String> killRingClone = new LinkedList<>(killRing);
        Iterator<String> killRingIter = killRingClone.iterator();
        while (killRingIter.hasNext()){
            String s = killRingIter.next();
            if (killRingIter.next().equals(killer)){

            }
        }
    }
}