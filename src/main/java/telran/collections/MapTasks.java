package telran.collections;
import java.util.*;

public class MapTasks {
    public static void displayOccurances(String[] strings ) {
        //input { "lpm, ab, a, c , cb , cb , c "}


        HashMap <String, Long> occurancesMap = getMapOccurances(strings);
        TreeMap<Long, TreeSet<String>> sortedOccurancesMap = getSortedOccurancesMap(occurancesMap);
        displaySortedOccurancesMap(sortedOccurancesMap);
    }

    private static void displaySortedOccurancesMap(TreeMap<Long, TreeSet<String>> sortedOccurancesMap) {
        sortedOccurancesMap.forEach((occurancy, treeSet) -> treeSet.forEach(s -> System.out.printf("%s -> %d\n", s, occurancy)));
    }

    private static HashMap<String, Long> getMapOccurances(String[] strings) {
        HashMap<String, Long> result = new HashMap<>();
        Arrays.stream(strings).forEach(s -> result.merge(s, 1l, Long::sum));
    
        return result;
    }
    
    private static TreeMap<Long, TreeSet<String>> getSortedOccurancesMap(HashMap<String, Long> occurancesMap) {
        TreeMap<Long, TreeSet<String>> result = new TreeMap<>(Comparator.reverseOrder());
        occurancesMap.entrySet().forEach(e -> result.computeIfAbsent(e.getValue(), k -> new TreeSet<>()).add(e.getKey()));
        return result;
    }
}
