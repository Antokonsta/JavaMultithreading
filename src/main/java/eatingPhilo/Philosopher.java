package eatingPhilo;

import java.util.Random;

/**
 * Created by Anton on 14.05.17.
 */
public class Philosopher implements Runnable {

    private int name;

    // Монитор взятия вилки
    private PhilosopherMonitor monitor;


    public Philosopher(int name, PhilosopherMonitor monitor) {
        this.name = name;
        this.monitor = monitor;
    }


    // Поочередно думает, берет вилку, ест и кладет вилку
    public void run() {
        try {
            while (true) {
                think();
                monitor.takeSpoons(name);
                eat();
                monitor.putDownChopsticks(name);
            }
        } catch (InterruptedException e) {
            System.out.println("Вы потревожили философа " + name);
        }
    }


    private void think() throws InterruptedException {
        System.out.println("Философ " + name + " сейчас думает!.\n");
        Thread.sleep (new Random().nextInt(100));
    }


    private void eat() throws InterruptedException {
        System.out.println("Философ " + name + " сейчас жрё....кушает!.\n");
        Thread.sleep (new Random().nextInt(100));
    }


}