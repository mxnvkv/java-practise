package Fibonacci;

public class Fibonacci {
  public static void main(String[] args) {
    int result = fibonacci(-1);
    System.out.println(result);
  }

  public static int fibonacci(int number) {
    if (number <= 1) return number;

    return fibonacci(number - 1) + fibonacci(number - 2);
  }
}
