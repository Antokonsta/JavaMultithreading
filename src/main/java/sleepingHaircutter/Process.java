package sleepingHaircutter;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Created by Anton on 14.05.17.
 */
public class Process implements Runnable{

    private Barbershop barbershop;

    public Process(Barbershop barbershop) {
        this.barbershop = barbershop;
    }

    public void run() {
        while (true) {
            Customer customer = new Customer(barbershop);
            customer.setInTime(new Date());
            Thread thСustomer = new Thread(customer);
            customer.setName("Поток клиента " + thСustomer.getId());
            thСustomer.start();

            try {
                TimeUnit.SECONDS.sleep((long) (Math.random() * 10));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
