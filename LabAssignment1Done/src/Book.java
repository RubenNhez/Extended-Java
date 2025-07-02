public class Book {
    private String title;
    private String author;
    private long isbn;
    private int pages;
    private int copiesInCollection;
    private int CopiesOnLoan;

    //1, complete this class with a constructor that has arguments for all the properties
    //and, getters and setters for each of them
    Book(String Title, String Author, long Isbn, int Pages, int CopiesInCollection, int copiesOnLoan) {
        title = Title;
        author = Author;
        isbn = Isbn;
        pages = Pages;
        copiesInCollection = CopiesInCollection;
        CopiesOnLoan = copiesOnLoan;
    }
    //Setters and Getters
    //Title
    public void setTitle(String title) {
        this.title = title;
    }
    public String getTitle() {
        return this.title;
    }
    //Author
    public void setAuthor (String author) {
        this.author = author;
    }
    public String getAuthor() {
        return this.author;
    }
    //Isbn
    public void setIsbn(long isbn) {
        this.isbn = isbn;
    }
    public long getIsbn() {
        return this.isbn;
    }
    //Pages
    public void setPages(int pages) {
        this.pages = pages;
    }
    public int getPages() {
        return this.pages;
    }
    //CopiesInCollection
    public void setCopiesInCollection (int copiesInCollection) {
        this.copiesInCollection = copiesInCollection;
    }
    public int getCopiesInCollection() {
        return this.copiesInCollection;
    }
    //CopiesOnLoan
    public void setCopiesOnLoan(int CopiesOnLoan) {
        this.CopiesOnLoan = CopiesOnLoan;
    }
    public int getCopiesOnLoan() {
        return this.CopiesOnLoan;
    }


    @Override
    public String toString() {
        return "Title: "+title+" Author: "+author+" ISBN: "+isbn+" Pages: "+pages+" Copies Vailable: "+copiesInCollection+" Copies on Loan: "+CopiesOnLoan;
    }
}
