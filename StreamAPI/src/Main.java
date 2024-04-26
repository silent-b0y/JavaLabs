import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Integer> integerList = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 0);
        System.out.println(getAverage(integerList));
        List<String> stringList = List.of("one", "tWo", "Three", "fouR", "FIVE!!!!", " ");
        System.out.println(toUpper(stringList));
        List<Integer> square = List.of(1, 2, 2, 3, 4, 4, 5, 6, 6, 7);
        System.out.println(getUniqueSquares(square));
        List<String> firstLetter = List.of("home", "apple", "house", "hit", "hi", "meal", "happy");
        System.out.println(sortedFirstLetter(firstLetter, 'h'));
        List<Integer> last1 = List.of();
        List<Integer> last2 = List.of(1, 2, 3, 0, 9);
        try {
            System.out.println(getLast(last1));
        } catch (NoSuchElementException e) {
            System.out.println(e.getMessage());
        }
        System.out.println(getLast(last2));
        int[] oddSum1 = new int[] {1, 3, 5, 7, 9};
        int[] oddSum2 = new int[] {2, 1, 3, 5, 6, 7, 9, 10};
        System.out.println(getOddSum(oddSum1));
        System.out.println(getOddSum(oddSum2));
        List<String> toMap = List.of("tree", "three", "toy", "hello", "j", "hate", "", "0");
        System.out.println(toMap(toMap));
    }
    public static double getAverage(List<Integer> list) {
        return list.stream()
                .mapToInt(i -> i)
                .average()
                .orElse(0);
    }

    public static List<String> toUpper(List<String> list) {
        return list.stream()
                .map(s -> "_new_" + s.toUpperCase())
                .collect(Collectors.toList());
    }

    public static List<Integer> getUniqueSquares(List<Integer> list) {
        return list.stream()
                .filter(e -> Collections.frequency(list, e) == 1)
                .map(e -> e * e)
                .collect(Collectors.toList());
    }

    public static Collection<String> sortedFirstLetter(Collection<String> list, char letter) {
        return list.stream()
                .filter(s -> s.startsWith(Character.toString(letter)))
                .sorted()
                .collect(Collectors.toList());
    }

    public static <T> T getLast(Collection<T> collection) throws NoSuchElementException {
        return collection.stream()
                .reduce((e1, e2) -> e2)
                .orElseThrow(() -> new NoSuchElementException("List is empty"));
    }

    public static int getOddSum(int[] array) {
        return Arrays.stream(array)
                .filter(e -> e % 2 == 0)
                .sum();
    }

    public static Map<Character, String> toMap(List<String> list) {
        return list.stream()
                .filter(s -> !s.equals(""))
                .collect(Collectors.toMap(s -> s.charAt(0), s -> s.substring(1), (s1, s2) -> s1 + s2));
    }
}
