package books;

import category.Category;

import java.util.ArrayList;

public class Shelf {
    private ArrayList<BookCopy> book;
    private Category category;

    public Shelf(ArrayList<BookCopy> book, Category category) {
        this.book = book;
        this.category = category;
    }

    public Shelf(Category category) {
        this.book = new ArrayList<>();
        this.category = category;
    }

    public ArrayList<BookCopy> getBook() {
        return book;
    }

    public void setBook(ArrayList<BookCopy> book) {
        this.book = book;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Shelf shelf) {
            return this.category.equals(shelf.getCategory());
        }

        return false;
    }

    public void addBook(BookCopy b) {
        if (!book.contains(b)) {
            book.add(b);
        }
    }
}
