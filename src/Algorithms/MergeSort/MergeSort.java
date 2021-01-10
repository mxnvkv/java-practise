package Algorithms.MergeSort;

import java.util.*;
import java.util.stream.Collectors;

public class MergeSort {
  public static void main(String[] args) {
    int[] testArray = new int[] {86, 52, 11, 34, 7, 12, 95, 72};
    int[] sortedArray = divide(testArray);

    printArray(sortedArray);
  }

  public static int[] divide(int[] array) {
    if (array.length < 2) {
      return array;
    }

    int leftIndex = 0;
    int rightIndex = array.length - 1;
    int middleIndex = (leftIndex + rightIndex) / 2;

    int[] leftArray = getElementsFromArray(array, leftIndex, middleIndex);
    int[] rightArray = getElementsFromArray(array, middleIndex + 1, rightIndex);

    int[] dividedLeftArray = divide(leftArray);
    int[] dividedRightArray = divide(rightArray);

    return mergeUsingList(dividedLeftArray, dividedRightArray);
  }

  public static int[] getElementsFromArray(int[] array, int indexFrom, int indexTo) {
    List<Integer> list = new ArrayList<>();
    for (int i = indexFrom; i <= indexTo; i++) {
      list.add(array[i]);
    }

    return list.stream().mapToInt(i -> i).toArray();
  }

  public static int[] merge(int[] firstArray, int[] secondArray) {
    int firstArrayLength = firstArray.length;
    int secondArrayLength = secondArray.length;
    int firstArrayIterator = 0;
    int secondArrayIterator = 0;
    List<Integer> list = new ArrayList<>();

    while (firstArrayIterator < firstArrayLength || secondArrayIterator < secondArrayLength) {
      if (firstArrayIterator == firstArrayLength) {
        list.add(secondArray[secondArrayIterator]);
        secondArrayIterator++;
        continue;
      }

      if (secondArrayIterator == secondArrayLength) {
        list.add(firstArray[firstArrayIterator]);
        firstArrayIterator++;
        continue;
      }

      int firstArrayElement = firstArray[firstArrayIterator];
      int secondArrayElement = secondArray[secondArrayIterator];

      if (firstArrayElement < secondArrayElement) {
        list.add(firstArrayElement);
        firstArrayIterator++;
      } else {
        list.add(secondArrayElement);
        secondArrayIterator++;
      }
    }

    return list.stream().mapToInt(i -> i).toArray();
  }

  public static int[] mergeUsingList(int[] firstArray, int[] secondArray) {
    List<Integer> firstList = Arrays.stream(firstArray)
      .boxed()
      .collect(Collectors.toList());
    List<Integer> secondList = Arrays.stream(secondArray)
      .boxed()
      .collect(Collectors.toList());

    List<Integer> returnList = new LinkedList<>();

    while (firstList.size() > 0 || secondList.size() > 0) {
      if (firstList.isEmpty()) {
        returnList.add(secondList.get(0));
        secondList.remove(0);
        continue;
      }

      if (secondList.isEmpty()) {
        returnList.add(firstList.get(0));
        firstList.remove(0);
        continue;
      }

      int firstListItem = firstList.get(0);
      int secondListItem = secondList.get(0);

      if (firstListItem < secondListItem) {
        returnList.add(firstListItem);
        firstList.remove(0);
      } else {
        returnList.add(secondListItem);
        secondList.remove(0);
      }
    }

    return returnList.stream().mapToInt(i -> i).toArray();
  }

  public static void printArray(int[] array) {
    System.out.println(Arrays.toString(array));
  }
}
