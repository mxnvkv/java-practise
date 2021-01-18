package AsynchronousProgramming.ExceptionsExample;

import AsynchronousProgramming.AsyncExample.User;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class ExceptionExample {
  public static void main(String[] args) {
    Supplier<List<Long>> supplyIds = () -> {
      sleep(200);
      throw new IllegalStateException("No data");
      // return Arrays.asList(1L, 2L, 3L);
    };

    Function<List<Long>, List<User>> fetchUsers = ids -> {
      sleep(300);
      return ids.stream().map(User::new).collect(Collectors.toList());
    };

    Consumer<List<User>> displayer = users -> users.forEach(System.out::println);

    CompletableFuture<List<Long>> supplier = CompletableFuture.supplyAsync(supplyIds);

    // CompletableFuture<List<Long>> exception = supplier.exceptionally(e -> List.of());

    // CompletableFuture<List<Long>> exception = supplier.whenComplete(
    //   (ids, e) -> {
    //     if (e != null) {
    //       System.out.println(e.getMessage());
    //       e.printStackTrace();
    //     }
    //   }
    // );

    CompletableFuture<List<Long>> exception = supplier.handle((ids, e) -> {
      if (e != null) {
        System.out.println(e.getMessage());
        e.printStackTrace();
        return List.of();
      } else {
        return ids;
      }
    });

    CompletableFuture<List<User>> fetcher = exception.thenApply(fetchUsers);
    CompletableFuture<Void> display = fetcher.thenAccept(displayer);

    sleep(1000);

    System.out.println("Supply  : done=" + supplier.isDone() +
      " exception=" + supplier.isCompletedExceptionally());
    System.out.println("Fetch   : done=" + fetcher.isDone() +
      " exception=" + fetcher.isCompletedExceptionally());
    System.out.println("Display : done=" + display.isDone() +
      " exception=" + display.isCompletedExceptionally());

  }

  private static void sleep(int timeout) {
    try {
      Thread.sleep(timeout);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
