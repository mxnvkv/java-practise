package AsynchronousProgramming.AsyncExample;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class AsyncExample {
  public static void main(String[] args) {
    ExecutorService executor = Executors.newSingleThreadExecutor();
    ExecutorService executor2 = Executors.newSingleThreadExecutor();

    Supplier<List<Long>> supplyIds = () -> {
      sleep(300);
      return Arrays.asList(1L, 2L, 3L);
    };

    Function<List<Long>, CompletableFuture<List<User>>> fetchUsers = ids -> {
      sleep(200);
      Supplier<List<User>> userSupplier = () -> {
        System.out.println("Currently running in " + Thread.currentThread().getName());
        return ids.stream().map(User::new).collect(Collectors.toList());
      };

      return CompletableFuture.supplyAsync(userSupplier, executor2);
    };

    Consumer<List<User>> displayer = users -> {
      System.out.println("Running thread: " + Thread.currentThread().getName());
      users.forEach(System.out::println);
    };

    // it will work, but it won't display anything
    // since we have to wait 500ms before we can chain tasks
    // so the main thread will be dead before anything
    // can be executed

    CompletableFuture.supplyAsync(supplyIds)
      .thenCompose(fetchUsers)
      .thenAcceptAsync(displayer, executor);

    // to make sure that everything has time to be executed
    sleep(1000);
    executor.shutdown();
    executor2.shutdown();
  }

  private static void sleep(int timeout) {
    try {
      Thread.sleep(timeout);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
