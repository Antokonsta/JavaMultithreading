package sleepingHaircutter;

/**
 * Created by Anton on 14.05.17.
 */
public class Main {
    public static void main(String[] args) {

        Barbershop shop = new Barbershop();

        Haircutter haircutter = new Haircutter(shop);
        Process process = new Process(shop);

        Thread thHaircutter = new Thread(haircutter);
        Thread thProcess = new Thread(process);
        thProcess .start();
        thHaircutter.start();
    }
}
