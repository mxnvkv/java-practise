package AsynchronousProgramming.Basics;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Supplier;

public class CompletableFutureWithSupplier {
  public static void main(String[] args) {
    ExecutorService executor = Executors.newSingleThreadExecutor();

    Supplier<String> supplier = () -> {
      try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }

      return Thread.currentThread().getName();
    };

    CompletableFuture<String> completableFuture =
      CompletableFuture.supplyAsync(supplier, executor);

    // will have custom message, since we force completion
    // before its completion

    completableFuture.complete("Took too much time, complete now!");

    String result1 = completableFuture.join();
    System.out.println("Result is: " + result1);


    // need to stop executor
    executor.shutdown();
  }
}
