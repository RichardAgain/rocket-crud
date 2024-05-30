package Cars;

public class Pickup extends Car {

    double discount = 0.20;

    public Pickup (Owner owner, String model, String brand, String plate, int year, String color) {
        super(owner, model, brand, plate, year, color);
    }

    public void setPrice(double price) {
        super.setPrice(price, discount);
    }

    public void setPrice(double rent, int days) {
        super.setPrice(rent, days, discount);
    }
}
