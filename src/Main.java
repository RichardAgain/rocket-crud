import Cars.*;

public class Main {
    public static void main(String[] args) {

        Sedan corsa = new Sedan("Corsa");

        corsa.setPrice(10000);
        corsa.setPrice(100.0, 3);

        System.out.println(corsa.owner.name);
    }
}