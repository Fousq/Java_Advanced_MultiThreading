package kz.zhanbolat.threading.fjp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openjdk.jmh.annotations.Benchmark;

import java.math.BigInteger;
import java.util.concurrent.ForkJoinPool;

// Works longer than sequential implementation
public class App {
    private static final Logger logger = LogManager.getLogger(App.class);

    @Benchmark
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        ForkJoinPool pool = new ForkJoinPool(Runtime.getRuntime().availableProcessors());
        BigInteger factorial = pool.invoke(new FactorialCalculationTask(100));
        logger.info("Factorial of 100 is " + factorial);
        long endTime = System.currentTimeMillis();
        logger.info("Take " + (endTime - startTime) + " ms to complete the calculation");
    }
}
