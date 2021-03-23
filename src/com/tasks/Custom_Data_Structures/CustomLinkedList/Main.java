package com.tasks.Custom_Data_Structures.CustomLinkedList;

public class Main {
  public static void main(String[] args) {
    CustomLinkedList linkedList = new CustomLinkedList(1, 2, 3, 4, 5);
    linkedList.add(6);
    linkedList.add(0, 7);
    System.out.println(linkedList);
  }
}
