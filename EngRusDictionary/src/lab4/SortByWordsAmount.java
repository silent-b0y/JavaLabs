package lab4;

import java.util.Comparator;

public class SortByWordsAmount implements Comparator<String> {
    @Override
    public int compare(String o1, String  o2) {
        return Integer.compare(o2.split(" ").length, o1.split(" ").length);
    }
}
