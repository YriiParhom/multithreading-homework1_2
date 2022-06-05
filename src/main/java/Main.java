import java.util.Arrays;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Main {
    public static void main(String[] args) throws Exception {
        Callable<Integer> callable1 = new MyCallable("Поток1");
        Callable<Integer> callable2 = new MyCallable("Поток2");
        Callable<Integer> callable3 = new MyCallable("Поток3");
        Callable<Integer> callable4 = new MyCallable("Поток4");
        ExecutorService threadPool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        Future<Integer> task1 = threadPool.submit(callable1);
        Future<Integer> task2 = threadPool.submit(callable2);
        Future<Integer> task3 = threadPool.submit(callable3);
        Future<Integer> task4 = threadPool.submit(callable4);

        System.out.println(callable1 + " выполнил задачу " + task1.get() + " раз.");
        System.out.println(callable2 + " выполнил задачу " + task2.get() + " раз.");
        System.out.println(callable3 + " выполнил задачу " + task3.get() + " раз.");
        System.out.println(callable4 + " выполнил задачу " + task4.get() + " раз.");

        Double result = (double) threadPool.invokeAny(Arrays.asList(callable1, callable2, callable3, callable4));
        System.out.println("Результат самой быстрой задачи " + result);

        System.out.println("Завершаю все потоки...");
        threadPool.shutdown();
    }
}
