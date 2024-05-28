package Cars;

public class Sedan extends Car {

    double discount = 0.10;

    public Sedan (String model) {
        super(model);
    }

    public void setPrice(double price) {
        super.setPrice(price, discount);
    }

    public void setPrice(double rent, int days) {
        super.setPrice(rent, days, discount);
    }

}
