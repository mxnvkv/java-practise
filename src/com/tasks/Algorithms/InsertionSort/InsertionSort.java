package com.tasks.Algorithms.InsertionSort;

import java.text.DecimalFormat;

import static com.tasks.Helper.AlgorithmsHelper.fillArray;

public class InsertionSort {
  public static void main(String[] args) {
    int[] testArray = new int[] { 94, 11, 72, 54, 2, 92, 1, 23, 12, 60 };
    int[] filledArray = fillArray(500_000);

    testInsertionSort(filledArray);
  }

  public static int[] insertionSort(int[] array) {
    for (int i = 1; i < array.length; i++) {
      int number = array[i];
      int count = i - 1;

      while (count >= 0 && number < array[count] ) {
        array[count + 1] = array[count];

        count--;
      }

      array[count + 1] = number;
    }

    return array;
  }

  public static void testInsertionSort(int[] array) {
    long start = System.currentTimeMillis();
    insertionSort(array);
    long end = System.currentTimeMillis();

    System.out.println("Insertion sort");
    System.out.println("Array length: " + new DecimalFormat("#,###").format(array.length));
    System.out.println(String.format("Sorted time: %dms", end - start));
  }
}
