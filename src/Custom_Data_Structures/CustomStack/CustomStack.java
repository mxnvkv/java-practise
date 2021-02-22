package Custom_Data_Structures.CustomStack;

import java.util.Arrays;

public class CustomStack {
  private int[] stack;

  CustomStack() {}

  CustomStack(int ...elements) {
    fillStack(elements);
  }

  public void add(int number) {
    int[] newStack = new int[stack.length + 1];

    for (int i = 0; i < stack.length; i++) {
      newStack[i] = stack[i];
    }

    newStack[newStack.length - 1] = number;

    stack = newStack;
  }

  public int get(int index) {
    return stack[index];
  }

  public void pop() {
    int[] newStack = new int[stack.length - 1];

    for (int i = 0; i < newStack.length; i++) {
      newStack[i] = stack[i];
    }

    stack = newStack;
  }

  private void fillStack(int ...elements) {
    stack = new int[elements.length];

    for (int i = 0; i < stack.length; i++) {
      stack[i] = elements[i];
    }
  }

  @Override
  public String toString() {
    return Arrays.toString(stack);
  }
}
