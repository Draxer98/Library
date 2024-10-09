import category.Category;

import java.util.List;

public class Book {
    private String isbn;
    private int counterCopyBook;
    private int counterSell;
    protected String title;
    protected List<Author> authors;
    protected double price;
    protected Category mainCategory;
    protected List<Category> categoryList;



    public String getIsbn() {
        return isbn;
    }
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
    public int getCounterCopyBook() {
        return counterCopyBook;
    }
    public void setCounterCopyBook(int counterCopyBook) {
        this.counterCopyBook = counterCopyBook;
    }
    public int getCounterSell() {
        return counterSell;
    }
    public void setCounterSell(int counterSell) {
        this.counterSell = counterSell;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public List<Author> getAuthors() {
        return authors;
    }
    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public Category getMainCategory() {
        return mainCategory;
    }
    public void setMainCategory(Category mainCategory) {
        this.mainCategory = mainCategory;
    }
    public List<Category> getCategoryList() {
        return categoryList;
    }
    public void setCategoryList(List<Category> categoryList) {
        this.categoryList = categoryList;
    }
}
