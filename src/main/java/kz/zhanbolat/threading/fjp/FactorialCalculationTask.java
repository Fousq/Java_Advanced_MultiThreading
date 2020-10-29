package kz.zhanbolat.threading.fjp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class FactorialCalculationTask extends RecursiveTask<BigInteger> {
    private static final int THRESHOLD = 10;
    private List<Integer> numbers;

    public FactorialCalculationTask(int n) {
        numbers = IntStream.range(1, n + 1).boxed().collect(Collectors.toList());
    }

    private FactorialCalculationTask(List<Integer> numbers) {
        this.numbers = numbers;
    }

    @Override
    protected BigInteger compute() {
        BigInteger result;
        if (numbers.size() > THRESHOLD) {
            Collection<FactorialCalculationTask> subtasks = ForkJoinTask.invokeAll(createSubtasks());
            result = BigInteger.ONE;
            for (FactorialCalculationTask subtask : subtasks) {
                result = result.multiply(subtask.join());
            }
        } else {
            result = calculateFactorial();
        }
        return result;
    }

    private BigInteger calculateFactorial() {
        BigInteger factorialVal = BigInteger.ONE;
        for (Integer number : numbers) {
            factorialVal = factorialVal.multiply(BigInteger.valueOf(number));
        }
        return factorialVal;
    }

    private Collection<FactorialCalculationTask> createSubtasks() {
        List<FactorialCalculationTask> tasks = new ArrayList<>();
        tasks.add(new FactorialCalculationTask(numbers.subList(0, numbers.size() / 2)));
        tasks.add(new FactorialCalculationTask(numbers.subList(numbers.size() / 2, numbers.size())));
        return tasks;
    }
}
