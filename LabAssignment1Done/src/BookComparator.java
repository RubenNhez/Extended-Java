import java.util.Comparator;

public class BookComparator implements Comparator <Book> {

    @Override
    public int compare(Book o1, Book o2) {

        if(o1.getCopiesOnLoan() == o2.getCopiesOnLoan()) {
            return 0;
        }

        else if(o1.getCopiesOnLoan() > o2.getCopiesOnLoan()) {
            return -1;
        }

        else {
            return 1;
        }
    }
}
