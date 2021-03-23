package com.tasks.Tasks_from_work.Tree_Depth;

public class Node {
  private Node parent;
  private Node leftChild;
  private Node rightChild;
  private int value;

  Node() {}

  Node(Node parent, int value) {
    this.parent = parent;
    this.value = value;
  }

  Node(Node parent, Node leftChild, Node rightChild, int value) {
    this.parent = parent;
    this.leftChild = leftChild;
    this.rightChild = rightChild;
    this.value = value;
  }

  public Node getParent() {
    return parent;
  }

  public Node getLeftChild() {
    return leftChild;
  }

  public Node getRightChild() {
    return rightChild;
  }

  public int getValue() {
    return value;
  }

  public void setParent(Node parent) {
    this.parent = parent;
  }

  public void setLeftChild(Node leftChild) {
    this.leftChild = leftChild;
  }

  public void setRightChild(Node rightChild) {
    this.rightChild = rightChild;
  }

  public void setValue(int value) {
    this.value = value;
  }

  @Override
  public String toString() {
    // TODO: should return only value
    String leftChildValue = leftChild == null
      ? "null"
      : String.valueOf(leftChild.getValue());
    String rightChildValue = rightChild == null
      ? "null"
      : String.valueOf(rightChild.getValue());

    return String.format(
      "Value: %d, leftChild: %s, rightChild: %s",
      value,
      leftChildValue,
      rightChildValue
    );
  }
}
