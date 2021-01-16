import java.io.*;
import java.util.*;

public class Main {
    private static List<String> killRing;
    private static List<String> graveyard;

    public static void main(String[] args) throws FileNotFoundException {
        killRing = new LinkedList2<>();
        graveyard = new ArrayList<>();
        loadKillRing("input.txt");

        String[] killerOrder = {"Sally", "Chris", "Chris", "Carol"};
        printKillRing();
        System.out.println();

        for (String killer: killerOrder){
            doKill(killer);
            printKillRing();
            printGraveyard();
            System.out.println();
        }
    }

    public static void loadKillRing(String filePath) throws FileNotFoundException {
        File file = new File(filePath);
        Scanner sc = new Scanner(file);
        while (sc.hasNextLine()){
            killRing.add(sc.nextLine());
        }
    }

    public static void doKill(int killerIndex){
        String killed = killRing.remove((killerIndex + 1) % killRing.size());
        graveyard.add(killed);
    }
    public static void doKill(String killer){
        doKill(killRing.indexOf(killer));
    }

    public static void printKillRing(){
        System.out.println("Kill ring: " + killRing);
    }

    public static void printGraveyard(){
        System.out.println("Graveyard: " + graveyard);
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