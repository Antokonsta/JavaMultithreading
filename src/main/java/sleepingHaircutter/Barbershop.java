package sleepingHaircutter;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by Anton on 14.05.17.
 */
public class Barbershop {

    int nchair;
    List<Customer> listCustomer;

    public Barbershop() {
        nchair = 3;
        listCustomer = new LinkedList<Customer>();
    }

    public void cutHair() {
        Customer customer;
        System.out.println("Парикмахер готовится к работе");
        synchronized (listCustomer) {

            while (listCustomer.size() == 0) {
                System.out.println("Парикмахер ожидает клиента");
                try {
                    listCustomer.wait();
                } catch (InterruptedException iex) {
                    iex.printStackTrace();
                }
            }
            System.out.println("Клиент найден");
            customer = (Customer) ((LinkedList<?>) listCustomer).poll();
        }
        long duration = 0;
        try {
            System.out.println("Стрижем клиента : " + customer.getName());
            duration = (long) (Math.random() * 10);
            TimeUnit.SECONDS.sleep(duration);
        } catch (InterruptedException iex) {
            iex.printStackTrace();
        }
        System.out.println("Завершение обслуживания клиента : " + customer.getName());
    }

    public void add(Customer customer) {
        System.out.println("Клиента : " + customer.getName() + " зашел в парикмахерскую в  " + customer.getInTime());

        synchronized (listCustomer) {
            if (listCustomer.size() == nchair) {
                System.out.println("Нет свободных мест для " + customer.getName());
                System.out.println("Клиент " + customer.getName() + "уходит...");
                return;
            }

            ((LinkedList<Customer>) listCustomer).offer(customer);
            System.out.println("Клиент : " + customer.getName() + " встал в очередь");

            if (listCustomer.size() == 1)
                listCustomer.notify();
        }
    }
}
