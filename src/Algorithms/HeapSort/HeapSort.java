package Algorithms.HeapSort;

import java.util.Arrays;
import java.util.stream.Stream;

import static Helper.AlgorithmsHelper.*;

public class HeapSort {
  public static void main(String[] args) {
    int[] testArray = {4, 1, 3, 2, 16, 9, 10, 14, 8, 7};
    System.out.println(Arrays.toString(heapSort(testArray)));
  }

  public static int[] heapSort(int[] array) {
    int counter = 1;

    buildMaxHeap(array);

    for (int i = array.length; i >= 1; i--) {

      // swap first and last element
      int firstElement = array[0];
      array[0] = array[array.length - counter];
      array[array.length - counter] = firstElement;

      // call maxHeapify() on unsorted part
      int[] sortedArray = Arrays.copyOfRange(array, array.length - counter, array.length);
      array = maxHeapify(Arrays.copyOf(array, array.length - counter), 0);
      array = joinArrays(array, sortedArray);

      counter++;
    }

    return array;
  }

  public static int[] buildMaxHeap(int[] array) {
    for (int i = array.length / 2; i >= 0; i--) {
      maxHeapify(array, i);
    }

    return array;
  }

  public static int[] maxHeapify(int[] array, int index) {
    int leftChildIndex = left(index);
    int rightChildIndex = right(index);
    int largestIndex;

    if (leftChildIndex < array.length && array[leftChildIndex] > array[index]) {
      largestIndex = leftChildIndex;
    } else {
      largestIndex = index;
    }

    if (rightChildIndex < array.length && array[rightChildIndex] > array[largestIndex]) {
      largestIndex = rightChildIndex;
    }

    if (largestIndex != index) {
      int largestNumber = array[largestIndex];
      array[largestIndex] = array[index];
      array[index] = largestNumber;

      maxHeapify(array, largestIndex);
    }

    return array;
  }

  public static int left(int index) {
    return index * 2 + 1;
  }

  public static int right(int index) {
    return (index + 1) * 2;
  }
}
