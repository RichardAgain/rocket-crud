package Cars;

public class Sedan extends Car {

    double discount = 0.10;

    public Sedan (Owner owner, String model, String brand, String plate, int year, String color) {
        super(owner, model, brand, plate, year, color);
    }

    public void setPrice(double price) {
        super.setPrice(price, discount);
    }

    public void setPrice(double rent, int days) {
        super.setPrice(rent, days, discount);
    }

}
