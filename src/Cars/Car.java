package Cars;

import java.util.ArrayList;

public class Car {

    public String model;
    public String brand;
    public String plate;
    public int year = 2024;
    public String color;
    public byte image; //idk

    public double price;

    public Owner owner;

    public static ArrayList<Car> carList  = new ArrayList<>();

    protected Car (Owner owner, String model, String brand, String plate, int year, String color, double price) {
        this.model = model;
        this.brand = brand;
        this.plate = plate;
        this.year = year;
        this.color = color;

        this.owner = owner;
        this.price = price;
    }

    public Object[] getColumn() {
        return new Object[] {
                this.brand,
                this.model,
                this.plate,
                this.year,
                this.owner.last_name + " " + this.owner.first_name,
        };
    }

    protected String getPrice(double discount) {
        double yearDiscount = ((double) (2024 - year) / 200);

        return String.valueOf(((price + price * 0.16) * (1 - yearDiscount)) * (1 - discount)) + "$";
    }

    protected String getPrice(int hours, double discount) {
        return String.valueOf(price * 0.02 * hours * (1 - discount)) + "$/hora";
    }

    public static class Owner {

        public int ci;
        public String first_name;
        public String last_name;

        public String phone;
        public String address;

        public Owner (int ci, String first_name, String last_name, String phone, String address ) {
            this.ci = ci;
            this.first_name = first_name;
            this.last_name = last_name;
            this.phone = phone;
            this.address = address;
        }

    }

}
