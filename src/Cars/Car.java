package Cars;

class Car {

    public String model;
    public String brand;
    public String plate;
    public int year;
    public String color;
    public byte image; //idk

    public String price;
    public Boolean isInRent;

    public Owner owner = new Owner();

    protected Car (String model) {
        this.model = model;
    }

    protected void setPrice(double price, double discount) {
        this.price = String.valueOf((price + price * 0.16) * (1 - discount)) + "$";
        this.isInRent = false;
    }

    protected void setPrice(double rent, int days, double discount) {
        this.price = String.valueOf((rent * days) * discount) + "$/dia";
        this.isInRent = true;
    }

    public static class Owner {

        public String name = "John";


    }

}
