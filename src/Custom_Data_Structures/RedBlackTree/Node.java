package Custom_Data_Structures.RedBlackTree;

public class Node {
  private int value;
  private Color color;
  private Node parent;
  private Node leftChild;
  private Node rightChild;

  Node() {}

  Node(int value) {
    this.value = value;
  }

  public int getValue() { return value; }

  public Color getColor() { return color; }

  public Node getParent() { return parent; }

  public Node getLeftChild() { return leftChild; }

  public Node getRightChild() { return rightChild; }

  public void setValue(int value) { this.value = value; }

  public void setColor(Color color) { this.color = color; }

  public void setParent(Node parent) { this.parent = parent; }

  public void setLeftChild(Node leftChild) { this.leftChild = leftChild; }

  public void setRightChild(Node rightChild) { this.rightChild = rightChild; }
}
