package AsynchronousProgramming.Basics;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FirstCompletableFuture {
  public static void main(String[] args) throws InterruptedException {
    ExecutorService executor = Executors.newSingleThreadExecutor();
    Runnable task = () -> {
      System.out.println("I am running asynchronously in a thread"
        + Thread.currentThread().getName());
    };

    CompletableFuture.runAsync(task, executor);

    // threadForkJoinPool example below

    // CompletableFuture.runAsync(task);
    // Thread.sleep(100);

    executor.shutdown();
  }
}
