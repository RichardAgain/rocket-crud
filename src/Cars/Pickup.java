package Cars;

public class Pickup extends Car {

    double discount = 0.20;

    public Pickup (Owner owner, String model, String brand, String plate, int year, String color, double price) {
        super(owner, model, brand, plate, year, color, price);
    }

    public String getPrice() {
        return super.getPrice(discount);
    }

    public String getPrice(int days) {
        return super.getPrice(days, discount);
    }
}
