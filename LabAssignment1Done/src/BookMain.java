import java.util.Arrays;

public class BookMain {
    public static void main(String[] args) {
        BookCollection bCollection = new BookCollection("BookList.csv");
//        bCollection.getBooks();
//       for (String author:  bCollection.getAuthors()) {
//           System.out.println(author);
//        }
        System.out.println(bCollection.getAuthors());
        System.out.println(bCollection.getLongBooks());
        System.out.println(bCollection.getBookByTitle("The Adventures of Sherlock Holmes"));
        System.out.println(Arrays.toString(bCollection.mostPopular()));
//
//    }
    }
}
