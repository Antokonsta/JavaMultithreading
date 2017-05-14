package sleepingHaircutter;

/**
 * Created by Anton on 14.05.17.
 */
class Haircutter implements Runnable {
    Barbershop barbershop;

    public Haircutter(Barbershop barbershop) {
        this.barbershop = barbershop;
    }

    public void run() {
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Парикмахер готов стричь!");
        while (true) {
            barbershop.cutHair();
        }
    }
}