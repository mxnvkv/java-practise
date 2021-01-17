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

public class AsyncExampleMulti2 {
  public static void main(String[] args) {
    ExecutorService executor = Executors.newSingleThreadExecutor();

    Supplier<List<Long>> supplyIds = () -> {
      sleep(300);
      return Arrays.asList(1L, 2L, 3L);
    };

    Function<List<Long>, CompletableFuture<List<User>>> fetchUsers1 = ids -> {
      sleep(250);
      Supplier<List<User>> userSupplier =
        () -> ids.stream().map(User::new).collect(Collectors.toList());

      return CompletableFuture.supplyAsync(userSupplier);
    };

    Function<List<Long>, CompletableFuture<List<User>>> fetchUsers2 = ids -> {
      sleep(5000);
      Supplier<List<User>> userSupplier =
        () -> ids.stream().map(User::new).collect(Collectors.toList());

      return CompletableFuture.supplyAsync(userSupplier);
    };

    Consumer<List<User>> displayer = users -> users.forEach(System.out::println);

    CompletableFuture<List<Long>> completableFuture = CompletableFuture.supplyAsync(supplyIds);

    CompletableFuture<List<User>> userFuture1 = completableFuture.thenComposeAsync(fetchUsers1);
    CompletableFuture<List<User>> userFuture2 = completableFuture.thenComposeAsync((fetchUsers2));

    userFuture1.thenRun(() -> System.out.println("Users 1"));
    userFuture2.thenRun(() -> System.out.println("Users 2"));

    userFuture1.acceptEither(userFuture2, displayer);

    sleep(6000);
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
