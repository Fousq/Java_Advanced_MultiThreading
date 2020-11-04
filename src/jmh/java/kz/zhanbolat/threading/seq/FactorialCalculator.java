package kz.zhanbolat.threading.seq;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

import java.math.BigInteger;

public class FactorialCalculator {

    @Benchmark
    public BigInteger calculateFactorial(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("factorial below zero cannot be calculated");
        }
        if (n == 0) {
            return BigInteger.ONE;
        }
        BigInteger factorialVal = BigInteger.ONE;
        while(n >= 1) {
            factorialVal = factorialVal.multiply(BigInteger.valueOf(n));
            n--;
        }
        return factorialVal;
    }
}
