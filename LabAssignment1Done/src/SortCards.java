import java.util.Comparator;

public class SortCards implements Comparator<Card> {
    @Override
    public int compare(Card o1, Card o2) {
        if(o1.getNumericValue() > o2.getNumericValue()) {
            return 1;
        }
        if(o1.getNumericValue() < o2.getNumericValue()) {
            return -1;
        }
        return 0;
    }
}
