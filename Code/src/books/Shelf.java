package books;

import category.Category;

import java.util.ArrayList;
/**
 * Represents a bookshelf in a library, containing copies of books belonging to a specific category.
 * The shelf can hold multiple copies of the same book, but does not allow duplicates.
 * 
 * The class provides methods to add new books to the shelf, retrieve and modify the list of books,
 * and get information about the shelf's category.
 * 
 * The class also implements a method to represent the structure of the shelf as a string, 
 * displaying the category and the books present.
 */
public class Shelf {
    private ArrayList<BookCopy> books;
    private Category category;

    public Shelf(ArrayList<BookCopy> book, Category category) {
        this.books = book;
        this.category = category;
    }

    public Shelf(Category category) {
        this.books = new ArrayList<>();
        this.category = category;
    }

    public ArrayList<BookCopy> getBooks() {
        return books;
    }

    public void setBooks(ArrayList<BookCopy> books) {
        this.books = books;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void addBook(BookCopy b) {
        if (!books.contains(b)) {
            books.add(b);
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Shelf shelf) {
            return this.category.equals(shelf.getCategory());
        }

        return false;
    }

    private String bookToString(ArrayList<Book> library) {
        StringBuilder a = new StringBuilder();

        a.append("\n");

        for (BookCopy bookCopy : books) {
            a.append("\t").append(bookCopy.toString(library)).append(",\n");
        }

        return a.toString();
    }

    public String toString(ArrayList<Book> library) {
        return "Scaffale {" + "\n" +
                "Categoria = '" + category + "'\n" +
                "Libri = {" + bookToString(library) +
                "}";
    }
}
