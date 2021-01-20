package Helper;

import java.util.Arrays;

public class AlgorithmsHelper {

  public static void printArray(int[] array) {
    System.out.println(Arrays.toString(array));
  }

  public static int[] fillArray(int quantity) {
    int[] returnedArray = new int[quantity];
    for (int i = 0; i < returnedArray.length; i++) {
      returnedArray[i] = getRandomNumber(quantity);
    }
    return returnedArray;
  }

  public static int getRandomNumber(int quantity) {
    return (int) (Math.random() * (quantity + 1));
  }

}
