package AsynchronousProgramming.DelayingExample;

import AsynchronousProgramming.AsyncExample.User;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class TriggerExample {
  public static void main(String[] args) {
    ExecutorService executor = Executors.newSingleThreadExecutor();

    Supplier<List<Long>> supplyIds = () -> {
      sleep(200);
      return Arrays.asList(1L, 2L, 3L);
    };

    Function<List<Long>, List<User>> fetchUsers = ids -> {
      sleep(300);
      return ids.stream().map(User::new).collect(Collectors.toList());
    };

    Consumer<List<User>> displayer = users -> {
      System.out.println("In thread " + Thread.currentThread().getName());
      users.forEach(System.out::println);
    };

    CompletableFuture<Void> start = new CompletableFuture<>();

    CompletableFuture<List<Long>> supplier = start.thenApply(nil -> supplyIds.get());
    CompletableFuture<List<User>> fetcher = supplier.thenApply(fetchUsers);
    CompletableFuture<Void> display = fetcher.thenAccept(displayer);

    start.completeAsync(() -> null, executor);

    sleep(1000);
    executor.shutdown();
  }

  private static void sleep(int timeout) {
    try {
      Thread.sleep(timeout);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}

