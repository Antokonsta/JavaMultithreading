package factorialCalc;

import java.math.BigDecimal;

/**
 * Created by Anton on 14.05.17.
 */
public class FactorialRunnable implements Runnable {
    private BigDecimal res;
    // Какую последовательность будет перемножать поток, напримре !6 ,а поток перемножает 1*2*3 ( с 1 до 3)
    private final int from;
    private final int to;

    public FactorialRunnable(int from, int to) {
        this.from = from;
        this.to = to;
    }

    public void run() {
        res = BigDecimal.valueOf(from);
        for (int i = from + 1; i <= to; i++) {
            res = res.multiply(BigDecimal.valueOf(i));
        }

    }

    public BigDecimal getRes() {
        return res;
    }
}

