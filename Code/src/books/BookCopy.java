package books;

import util.Library;

/**
 * This class represents the book that can be sold.
 * It has the isbn and the isbn of the parent book.
 */
public class BookCopy {
    private String isbn;
    private String parentIsbn;

    public BookCopy(String isbn, String parentIsbn) {
        this.isbn = isbn;
        this.parentIsbn = parentIsbn;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getParentIsbn() {
        return parentIsbn;
    }

    public void setParentIsbn(String parentIsbn) {
        this.parentIsbn = parentIsbn;
    }


    public boolean equals(Object obj) {
        if (obj instanceof BookCopy book) {
            return parentIsbn.equals(book.getParentIsbn());
        }

        return false;
    }

    /**
     * This toString method takes the library to get the information about the parent {@link Book}
     *
     * @param library the library where to find the information about the parent book
     */
    public String toString(Library library) {
        Book parentBook = library.findParentBookByIsbn(parentIsbn);

        return "Libro copia {" +
                "isbn='" + isbn + '\'' +
                ", Titolo = '" + parentBook.getTitle() + "'" +
                ", Prezzo = " + parentBook.getPrice() + "'" +
                ", Autori = " + parentBook.getAuthors() + "'" +
                ", Categoria = " + parentBook.getCategory() + "'" +
                '}';
    }
}
