package kz.zhanbolat.threading.fjp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openjdk.jmh.annotations.Benchmark;

import java.math.BigInteger;
import java.util.concurrent.ForkJoinPool;

// TODO: Add microbenchmark
public class App {
    private static final Logger logger = LogManager.getLogger(App.class);

    public static void main(String[] args) {
        ForkJoinPool pool = new ForkJoinPool(Runtime.getRuntime().availableProcessors());
        BigInteger factorial = pool.invoke(new FactorialCalculationTask(100));
        logger.info("Factorial of 100 is " + factorial);
    }
}
