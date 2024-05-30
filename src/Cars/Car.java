package Cars;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Car {

    public String model;
    public String brand;
    public String plate;
    public int year = 2024;
    public String color;
    public byte image; //idk

    public String price;
    public Boolean isInRent;

    public Owner owner;

    public static ArrayList<Car> carList  = new ArrayList<>();

    protected Car (Owner owner, String model, String brand, String plate, int year, String color) {
        this.model = model;
        this.brand = brand;
        this.plate = plate;
        this.year = year;
        this.color = color;

        this.owner = owner;
        this.price = "Sin Precio";
    }

    protected void setPrice(double price, double discount) {
        double yearDiscount = ((double) (2024 - year) / 200);

        this.price = String.valueOf(((price + price * 0.16) * (1 - yearDiscount)) * (1 - discount)) + "$";
        this.isInRent = false;
    }

    protected void setPrice(double rent, int days, double discount) {
        this.price = String.valueOf(rent * days * (1 - discount)) + "$";
        this.isInRent = true;
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
