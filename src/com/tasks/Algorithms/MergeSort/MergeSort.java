package com.tasks.Algorithms.MergeSort;

import java.text.DecimalFormat;
import static com.tasks.Helper.AlgorithmsHelper.*;

public class MergeSort {
  public static void main(String[] args) {
    int[] testArray = new int[] {100, 55, 67, 73, 14, 97, 1, 12};
    int[] filledArray = fillArray(10_000_000);

    testMergeSort(filledArray);
  }

  public static int[] divide(int[] array) {
    if (array.length < 2) return array;

    int middleIndex = (array.length - 1) / 2;

    int[] leftArray = getArrayFrom(array, 0, middleIndex);
    int[] rightArray = getArrayFrom(array, middleIndex + 1, array.length - 1);

    leftArray = divide(leftArray);
    rightArray = divide(rightArray);

    return merge(leftArray, rightArray);
  }

  public static int[] getArrayFrom(int[] array, int startIndex, int endIndex) {
    int[] newArray = new int[endIndex - startIndex + 1];
    int index = 0;

    for (int i = startIndex; i <= endIndex; i++) {
      newArray[index] = array[i];
      index++;
    }

    return newArray;
  }

  public static int[] merge(int[] leftArray, int[] rightArray) {
    int[] sortedArray = new int[leftArray.length + rightArray.length];
    int leftArrayIndex = 0;
    int rightArrayIndex = 0;

    for (int i = 0; i < sortedArray.length; i++) {
      if (leftArrayIndex >= leftArray.length) {
        sortedArray[i] = rightArray[rightArrayIndex];
        rightArrayIndex++;
      } else if (rightArrayIndex >= rightArray.length) {
        sortedArray[i] = leftArray[leftArrayIndex];
        leftArrayIndex++;
      } else {
        int leftArrayElement = leftArray[leftArrayIndex];
        int rightArrayElement = rightArray[rightArrayIndex];

        if (leftArrayElement < rightArrayElement) {
          sortedArray[i] = leftArrayElement;
          leftArrayIndex++;
        } else {
          sortedArray[i] = rightArrayElement;
          rightArrayIndex++;
        }
      }
    }

    return sortedArray;
  }

  public static void testMergeSort(int[] array) {
    long start = System.currentTimeMillis();
    divide(array);
    long end = System.currentTimeMillis();

    System.out.println("Merge sort");
    System.out.println("Array length: " + new DecimalFormat("#,###").format(array.length));
    System.out.println(String.format("Sorted time: %dms", end - start));
  }
}
