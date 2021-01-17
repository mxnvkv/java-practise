package AsynchronousProgramming.Basics;

import java.util.concurrent.CompletableFuture;

public class SimpleCompletableFuture {
  public static void main(String[] args) {
    CompletableFuture<Void> cf = new CompletableFuture<>();

    Runnable task = () -> {
      try {
        Thread.sleep(1000);
      } catch (InterruptedException e) { }

      cf.complete(null);
    };

    CompletableFuture.runAsync(task);
    Void unused = cf.join();

    System.out.println("End of the program");
  }
}
