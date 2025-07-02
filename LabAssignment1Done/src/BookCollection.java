import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.ArrayList;

public class BookCollection {
    private  ArrayList<Book> books= new ArrayList<Book>();

    //2, complete constructor that takes a string path (the BookList file name) load the books from BookList into the books arrayList
    //When complete books should have 100 items. Make sure you don't include the header row!
    BookCollection(String path)   {

        try{
            Scanner file = new Scanner(new File(path));
            file.nextLine();
            while(file.hasNext()){

                String[] parameter = file.nextLine().split(",");
                books.add(new Book( parameter[0], parameter[1], Long.parseLong(parameter[2]), Integer.parseInt(parameter[3]), Integer.parseInt(parameter[4]), Integer.parseInt(parameter[5])));


            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        System.out.println(books.size());
    }

    //3, Return a HashSet of all the authors in the book list
    public HashSet<String> getAuthors(){

        HashSet<String> authors = new HashSet<>();

        for (Book book: books) {
            authors.add(book.getAuthor());
        }

        return authors;
    }

    //4, return an arrayList of books with more than 750 pages
    public ArrayList<Book> getLongBooks(){
        ArrayList<Book> LongBooks = new ArrayList<>();
        for (int i = 0; i < books.size(); i++) {
            if(books.get(i).getPages() > 750) {
               LongBooks.add(books.get(i));
            }
        }
        return LongBooks;
    }

    //5, return the book if the given title is in the list.
    public Book getBookByTitle(String title){

        for (int i = 0; i < books.size(); i++) {
            if(books.get(i).getTitle().equals(title)) {
                return books.get(i);
            }


        }
        return null;
    }

    //6, return an array of the 10 most popular books (That is those that currently have most copies on loan)
    public Book[] mostPopular(){
        Book [] popular = new Book[10];
        //make a comparator - new class implement a comparator which will sort the books by order of popularity -if it's more popular go up the list else go down the list
        //for loop that takes the most popular books and puts it in the popular book array than return it
        books.sort(new BookComparator());
        for (int i = 0; i < 10; i++) {
            popular[i] = books.get(i);
        }
        return popular;
    }



    //EXTRA - getBooks
    public void getBooks(){
        System.out.println("Total Books: "+books.size());
        for(Book book: books){
            System.out.println(book.toString());
        }
    }

}
