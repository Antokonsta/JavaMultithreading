package factorialCalc;

import java.math.BigDecimal;
import java.util.Scanner;

/**
 * Created by Anton on 14.05.17.
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {

        boolean again = true;
        Scanner scanner = new Scanner(System.in);
        while (again) {
            System.out.println("Введите число для подсчета факторила : ");
            int input = scanner.nextInt();
            System.out.println("Введите число потоков : ");
            int numOfThreads = scanner.nextInt();
            System.out.println("Результат = " + factorialСalc(input, numOfThreads).toString());
            System.out.println();
            System.out.println("Еще разок [y/n] : ");
            String str = scanner.next();
            if (str.equals("n")) {
                again = false;
            } else if (str.equals("y"))  {
                again = true;
            } else {
                System.out.println("Просили же Y или N!!! Я Закрываюсь, пока!!");
                again = false;
            }

        }
    }


    private static BigDecimal factorialСalc(int input, int numThreads)
            throws InterruptedException {
        BigDecimal result = BigDecimal.valueOf(1);
        Thread[] threads = new Thread[numThreads];
        FactorialRunnable[] workers = new FactorialRunnable[numThreads];
        for (int i = 1; i <= numThreads; i++) {
            // Делим последовательность перемножения на части пропорциональные кол-ву потоков
            int start = (i == 1) ? 1 : (input / numThreads * (i - 1)) + 1;
            int end = (i == numThreads) ? input : input / numThreads * i;
            workers[i - 1] = new FactorialRunnable(start, end);
            threads[i - 1] = new Thread(workers[i - 1]);
        }

        // старт потоков
        for (int i = 0; i < numThreads; i++) {
            threads[i].start();
        }
        // ждем пока все потоки закончат перемножение своих частей
        for (int i = 0; i < numThreads; i++) {
            threads[i].join();
        }
        // перемножает результаты потоков
        for (int i = 0; i < numThreads; i++) {
            result = result.multiply(workers[i].getRes());
        }
        return result;
    }
}
