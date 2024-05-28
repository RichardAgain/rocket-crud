package Cars;

public class Pickup extends Car {

    double discount = 0.20;

    public Pickup (String model) {
        super(model);
    }

    public void setPrice(double price) {
        super.setPrice(price, discount);
    }

    public void setPrice(double rent, int days) {
        super.setPrice(rent, days, discount);
    }
}
