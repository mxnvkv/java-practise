package Custom_Data_Structures.RedBlackTree;

public class Node {
  private int value;
  private Color color;
  private Node parent;
  private Node leftChild;
  private Node rightChild;

  Node() {}

  Node(int value, Color color) {
    this.value = value;
    this.color = color;
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

  // TODO: toString() should return only value

  @Override
  public String toString() {
    String leftChildValue = leftChild == null
      ? "null"
      : String.valueOf(leftChild.getValue());
    String leftChildColor = leftChild == null
      ? "null"
      : String.valueOf(leftChild.getColor());
    String rightChildValue = rightChild == null
      ? "null"
      : String.valueOf(rightChild.getValue());
    String rightChildColor = rightChild == null
      ? "null"
      : String.valueOf(rightChild.getColor());

    return String.format(
      "Value: %d (%s), leftChild: %s (%s), rightChild: %s (%s)",
      value,
      color,
      leftChildValue,
      leftChildColor,
      rightChildValue,
      rightChildColor
    );
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }

    if (!(obj instanceof Node)) {
      return false;
    }

    Node rbt = (Node) obj;
    return rbt.getValue() == value;
  }
}
