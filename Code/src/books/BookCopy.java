package books;

import util.Library;

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
