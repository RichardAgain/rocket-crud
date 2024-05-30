import Cars.*;

public class Main {
    public static void main(String[] args) {
        Car.Owner owner = new Car.Owner(12123123, "Pepe", "Hern", "0414-4249066", "Albaquerque");
        Sedan corsa = new Sedan(owner, "Corsa", "Toyota","ASD123",2018, "#0f0f0f");
        Pickup luv = new Pickup(owner, "Luv", "Toyota","ASD123",2018, "#0f0f0f");
        Sedan spark = new Sedan(owner, "Spark", "Toyota","ASD123",2018, "#0f0f0f");

        corsa.setPrice(10000);
        System.out.println(corsa.price);
        corsa.setPrice(100, 20);
        System.out.println(corsa.price);

//        Car.carList.add(corsa);
//        Car.carList.add(luv);
//        Car.carList.add(spark);
//
//        Car.carList.forEach((car) -> System.out.println(car.model));
    }
}