package com.tasks.Algorithms.BucketSort;

import java.util.ArrayList;
import java.util.Collections;

// made this sort for double numbers from 0 to 1
public class BucketSort {
  public static void main(String[] args) {
    double[] testArray = { 0.97, 0.24, 0.01, 0.11, 0.73, 0.49, 0.52, 0.28, 0.25 };

    System.out.println(sort(testArray));
  }

  public static ArrayList<Double> sort(double[] array) {
    ArrayList<ArrayList<Double>> list = new ArrayList<>();
    ArrayList<Double> sortedList = new ArrayList<>();

    // filling with empty lists
    for (int i = 0; i < 10; i++) {
      list.add(new ArrayList<>());
    }

    // adding number to each list
    for (int i = 0; i < array.length; i++) {
      list.get((int) (array[i] * 10)).add(array[i]);
    }

    // sorting each list
    for (int i = 0; i < list.size(); i++) {
      Collections.sort(list.get(i));
    }

    // joining lists
    for (int i = 0; i < list.size(); i++) {
      sortedList.addAll(list.get(i));
    }

    return sortedList;
  }
}
