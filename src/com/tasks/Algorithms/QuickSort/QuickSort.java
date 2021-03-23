package com.tasks.Algorithms.QuickSort;

import java.text.DecimalFormat;

import static com.tasks.Helper.AlgorithmsHelper.*;

public class QuickSort {
  public static void main(String[] args) {
    int[] testArray = {92, 4, 48, 56, 12, 5, 27, 108, 34, 9};
    int[] filledArray = fillArray(10_000_000);

    testQuickSort(filledArray);
  }

  public static void sort(int[] array, int start, int end) {
    if (start < end) {
      int divider = partition(array, start, end);

      sort(array, start, divider - 1);
      sort(array, divider + 1, end);
    }
  }

  // main function in current algorithm: it takes pivot, places
  // elements smaller than it at the start and then puts pivot right
  // after them
  public static int partition(int[] array, int start, int end) {
    int pivot = array[end];
    int smallerElementIndex = start - 1;

    for (int i = start; i <= end - 1; i++) {
      if (array[i] <= pivot) {
        smallerElementIndex++;

        swap(array, smallerElementIndex, i);
      }
    }

    smallerElementIndex++;
    swap(array, smallerElementIndex , end);

    return smallerElementIndex;
  }

  public static void swap(int[] array, int firstIndex, int secondIndex) {
    int temp = array[firstIndex];
    array[firstIndex] = array[secondIndex];
    array[secondIndex] = temp;
  }

  public static void testQuickSort(int[] array) {
    long start = System.currentTimeMillis();
    sort(array, 0, array.length - 1);
    long end = System.currentTimeMillis();

    System.out.println("Quick sort");
    System.out.println("Array length: " + new DecimalFormat("#,###").format(array.length));
    System.out.println(String.format("Sorted time: %dms", end - start));
  }
}
