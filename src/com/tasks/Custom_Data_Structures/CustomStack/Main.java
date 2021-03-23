package com.tasks.Custom_Data_Structures.CustomStack;

public class Main {
  public static void main(String[] args) {
    CustomStack stack = new CustomStack(1, 2, 3, 4, 5, 6);
    System.out.println(stack);
    stack.add(12);
    System.out.println(stack);
    stack.pop();
    stack.pop();
    stack.pop();
    System.out.println(stack);
  }
}
