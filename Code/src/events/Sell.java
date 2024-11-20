package events;

import org.json.simple.JSONObject;

import java.time.LocalDate;

/**
 * Represents a sell of the library,
 * and it tracks isbn of the sold book, the date when the book was sold
 * and the price at the moment of the sell.
 */
public class Sell {
    private String isbnSoldBook;
    private double price;
    private LocalDate sellDate;

    public Sell(String isbnSoldBook, double price, LocalDate sellDate) {
        this.isbnSoldBook = isbnSoldBook;
        this.price = price;
        this.sellDate = sellDate;
    }

    public String getIsbnSoldBook() {
        return isbnSoldBook;
    }

    public void setIsbnSoldBook(String isbnSoldBook) {
        this.isbnSoldBook = isbnSoldBook;
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

    @Override
    public String toString() {
        return "Acquisto {" +
                "isbn = '" + isbnSoldBook + "'" +
                ", Prezzo = '" + price + "'" +
                ", Data di acquisto = '" + sellDate.toString() + "'" +
                "}";
    }

    public JSONObject toJson() {
        JSONObject obj = new JSONObject();

        obj.put("isbnSoldBook", isbnSoldBook);
        obj.put("price", price);
        obj.put("sellDate", sellDate);

        return obj;
    }
}