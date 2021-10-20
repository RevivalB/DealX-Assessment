package dealx;

public class PriceCalculator {
    private double unitPrice;
    private int quantity;
    private double totalPrice;

    public PriceCalculator(double unitPrice, int quantity) {
        this.unitPrice = unitPrice;
        this.quantity = quantity;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public double calculateTotalPrice(){
        totalPrice = unitPrice * quantity;
        return  totalPrice;
    }
}
