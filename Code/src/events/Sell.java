package events;

import java.time.LocalDate;

public class Sell {
    private String isbnSoldBook;
    private String idUser;
    private double price;
    private LocalDate sellDate;

    public Sell(String isbnSoldBook, String idUser, double price, LocalDate sellDate) {
        this.isbnSoldBook = isbnSoldBook;
        this.idUser = idUser;
        this.price = price;
        this.sellDate = sellDate;
    }

    public String getIsbnSoldBook() {
        return isbnSoldBook;
    }

    public void setIsbnSoldBook(String isbnSoldBook) {
        this.isbnSoldBook = isbnSoldBook;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public LocalDate getSellDate() {
        return sellDate;
    }

    public void setSellDate(LocalDate sellDate) {
        this.sellDate = sellDate;
    }
}