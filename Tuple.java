public class Tuple {
    private long timeStamp;
    private String symbol;
    private int quantity;
    private int price;

    public Tuple(long timeStamp, String symbol, int quantity, int price) {
        this.timeStamp = timeStamp;
        this.symbol = symbol;
        this.quantity = quantity;
        this.price = price;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
