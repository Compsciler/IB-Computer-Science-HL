import java.io.*;
import java.util.*;
public class Main {
    public static void main(String[] args) {
        List<Integer> arr1 = new ArrayList<>();
        List<Integer> arr2 = new ArrayList<>();
        arr1.add(1);
        arr1.add(5);
        arr1.add(4);
        arr1.add(23);
        arr1.add(3);
        arr1.add(8);
        arr2.add(8);
        arr2.add(8);
        arr2.add(23);
        arr2.add(-2);
        arr2.add(3);
        arr2.add(0);
        System.out.println(countCommon(arr1, arr2));

        Set<String> set = new HashSet<>();
        set.add("asdf");
        set.add("qwe");
        set.add("12345");
        set.add("zxcvbn");
        removeEvenLength(set);

        Map<String, Integer> map1 = new HashMap<>();
        Map<String, Integer> map2 = new HashMap<>();
        map1.put("a", 1);
        map1.put("c", 4);
        map1.put("b", 3);
        map1.put("d", 1);
        map1.put("e", 4);
        map1.put("g", 1);
        map1.put("h", 5);
        System.out.println(reverse(map1));
    }

    public static <T> int countCommon(List<T> list1, List<T> list2){  // #7
        Set<T> set1 = new HashSet<>(list1);
        Set<T> set2 = new HashSet<>(list2);
        set1.retainAll(set2);
        return set1.size();
    }

    public static void removeEvenLength(Set<String> strings){  // #10
        Iterator<String> stringsIter = strings.iterator();
        while (stringsIter.hasNext()){
            String s = stringsIter.next();
            if (s.length() % 2 == 0){
                stringsIter.remove();
            }
        }
        System.out.println(strings);
    }

    public static boolean contains3(List<String> strings){  // #12
        Map<String, Integer> stringTotals = new HashMap<>();
        for (String s: strings){
            if (stringTotals.containsKey(s)){
                stringTotals.put(s, stringTotals.get(s) + 1);
            } else {
                stringTotals.put(s, 1);
            }
        }
        for (Integer i: stringTotals.values()){
            if (i >= 3){
                return true;
            }
        }
        return false;
    }

    public static Map<String, Integer> intersect(Map<String, Integer> map1, Map<String, Integer> map2){  // #14
        Map<String, Integer> res = new HashMap<>();
        for (String s: map1.keySet()){
            if (map1.get(s).equals(map2.get(s))){
                res.put(s, map1.get(s));
            }
        }
        return res;
    }

    public static <K,V> Map<V, Set<K>> reverse(Map<K,V> map){  // #18
        Map<V, Set<K>> res = new HashMap<>();
        for (K k: map.keySet()){
            V v = map.get(k);
            if (res.containsKey(v)){
                res.get(v).add(k);
            } else {
                Set<K> kSet = new HashSet<>();
                kSet.add(k);
                res.put(v, kSet);
            }
        }
        return res;
    }
}