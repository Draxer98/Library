package books;

import category.Category;

import java.util.ArrayList;

/**
 * This class rappresent a book that can be loaned to a user.
 * It has some attributes such as isbn, title, authors, price, category, 
 * isAvaiable to check if is avaiable, the isbns of all the books that are copy of this one and can be sell.
 */
public class Book {
    private String isbn;
    protected String title;
    protected ArrayList<Author> authors;
    protected double price;
    protected Category category;
    protected ArrayList<String> isbnCopyBook;
    protected boolean isAvaiable;

    public Book(String isbn, String title, ArrayList<Author> authors, double price, Category category) {
        this.isbn = isbn;
        this.title = title;
        this.authors = authors;
        this.price = price;
        this.category = category;
        this.isAvaiable = true;
        this.isbnCopyBook = new ArrayList<>();
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ArrayList<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(ArrayList<Author> authors) {
        this.authors = authors;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public ArrayList<String> getIsbnCopyBook() {
        return isbnCopyBook;
    }

    public void setIsbnCopyBook(ArrayList<String> isbnCopyBook) {
        this.isbnCopyBook = isbnCopyBook;
    }

    public boolean isAvaiable() {
        return isAvaiable;
    }

    public void setAvaiable(boolean avaiable) {
        isAvaiable = avaiable;
    }

    @Override
    public String toString() {
        return "Libro {" +
                "isbn= '" + isbn + "'" +
                ", Titolo = '" + title + "'" +
                ", Autore = '" + authors + "'" +
                ", Prezzo = '" + price + "'" +
                ", Categoria = '" + category + "'" +
                '}';
    }
}
