/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package kz.zhanbolat.threading;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class App {
    private static final Logger logger = LogManager.getLogger(App.class);
    private static Map<Integer, Double> employeeIdToSalaryMap = new HashMap<>();
    private static Random random = new Random(13);

    // TODO: Ask about special operation on CF
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        init();
        CompletableFuture<List<Employee>> listCompletableFuture = CompletableFuture.supplyAsync(App::hiredEmployees)
                .thenApplyAsync(employees -> employees.stream().peek(employee -> employee.setSalary(getSalary(employee.getId()))).collect(Collectors.toList()));
        logger.info(listCompletableFuture.get());
    }

    private static void init() {
        IntStream.range(1, 10).forEach(id -> employeeIdToSalaryMap.put(id, random.nextDouble()));
    }

    private static List<Employee> hiredEmployees() {
        List<Employee> employees = new ArrayList<>();
        IntStream.range(1, 10).forEach(id -> employees.add(new Employee(id)));
        return employees;
    }

    private static double getSalary(int hiredEmployeeId) {
        return Optional.ofNullable(employeeIdToSalaryMap.get(hiredEmployeeId)).orElse(0d);
    }
}
