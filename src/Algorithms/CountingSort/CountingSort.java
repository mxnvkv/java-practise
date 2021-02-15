package Algorithms.CountingSort;

import java.text.DecimalFormat;

import static Helper.AlgorithmsHelper.*;

public class CountingSort {
  public static void main(String[] args) {
    int[] testArray = {7, 2, 5, 8, 1, 2, 5, 4, 0, 3, 8, 2};
    int[] filledArray = fillArray(10_000_000);

    testCountingSort(filledArray);
  }

  public static int[] sort(int[] array) {
    int maxNumber = array[0];
    // array where index is number and the value is amount of times
    // we have this number in array
    int[] numbersArray;
    int[] sortedArray;

    for (int i = 0; i < array.length; i++) {
      if (array[i] > maxNumber) {
        maxNumber = array[i];
      }
    }

    numbersArray = new int[maxNumber + 1];

    // now we are going to fill numbersArray with corresponding value
    for (int i = 0; i < array.length; i++) {
      int number = array[i];

      numbersArray[number]++;
    }

    // modify numbersArray by adding previous value to the current
    for (int i = 1; i < numbersArray.length; i++) {
      numbersArray[i] += numbersArray[i - 1];
    }

    // Subtracting 1 in order to have index as number and value
    // as its position in array. For example, if we have zero 1 time
    // in our array, it should be first
    for (int i = 0; i < numbersArray.length; i++) {
      numbersArray[i]--;
    }

    sortedArray = new int[array.length];

    for (int i = 0; i < array.length; i++) {
      int number = array[i]; // current number
      int index = numbersArray[number]; // position to put in sorted array

      sortedArray[index] = number;
      numbersArray[number]--;
    }

    return sortedArray;
  }

  public static void testCountingSort(int[] array) {
    long start = System.currentTimeMillis();
    sort(array);
    long end = System.currentTimeMillis();

    System.out.println("Counting sort");
    System.out.println("Array length: " + new DecimalFormat("#,###").format(array.length));
    System.out.println(String.format("Sorted time: %dms", end - start));
  }
}
