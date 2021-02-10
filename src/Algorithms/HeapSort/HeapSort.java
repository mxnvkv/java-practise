package Algorithms.HeapSort;

import java.text.DecimalFormat;
import static Helper.AlgorithmsHelper.*;

public class HeapSort {
  public static void main(String[] args) {
    int[] testArray = {4, 1, 3, 2, 16, 9, 10, 14, 8, 7};
    int[] filledArray = fillArray(10_000_000);

    testHeapSort(filledArray);
  }

  public static int[] heapSort(int[] array) {
    int heapSize = array.length - 1;

    buildMaxHeap(array);

    for (int i = heapSize; i > 0; i--) {
      int firstElement = array[0];
      array[0] = array[i];
      array[i] = firstElement;

      maxHeapify(array, 0, i);
    }

    return array;
  }

  public static int[] buildMaxHeap(int[] array) {
    for (int i = array.length / 2; i >= 0; i--) {
      maxHeapify(array, i, array.length - 1);
    }

    return array;
  }

  public static int[] maxHeapify(int[] array, int index, int heapSize) {
    int leftChildIndex = left(index);
    int rightChildIndex = right(index);
    int largestIndex;

    if (leftChildIndex < heapSize && array[leftChildIndex] > array[index]) {
      largestIndex = leftChildIndex;
    } else {
      largestIndex = index;
    }

    if (rightChildIndex < heapSize && array[rightChildIndex] > array[largestIndex]) {
      largestIndex = rightChildIndex;
    }

    if (largestIndex != index) {
      int largestNumber = array[largestIndex];
      array[largestIndex] = array[index];
      array[index] = largestNumber;

      maxHeapify(array, largestIndex, heapSize);
    }

    return array;
  }

  public static int left(int index) {
    return index * 2 + 1;
  }

  public static int right(int index) {
    return (index + 1) * 2;
  }

  public static void testHeapSort(int[] array) {
    long start = System.currentTimeMillis();
    heapSort(array);
    long end = System.currentTimeMillis();

    System.out.println("Heap sort");
    System.out.println("Array length: " + new DecimalFormat("#,###").format(array.length));
    System.out.println(String.format("Sorted time: %dms", end - start));
  }
}
