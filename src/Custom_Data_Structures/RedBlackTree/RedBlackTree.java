package Custom_Data_Structures.RedBlackTree;

public class RedBlackTree {
  private Node root;

  RedBlackTree() {}

  RedBlackTree(int number) {
    root = createNode(number);
  }

  private Node createNode(int number) {
    return new Node(number);
  }
}
