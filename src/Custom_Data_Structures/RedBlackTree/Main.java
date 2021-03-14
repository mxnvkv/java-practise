package Custom_Data_Structures.RedBlackTree;

public class Main {
  public static void main(String[] args) {
    RedBlackTree tree = new RedBlackTree();

    tree.add(8);

    tree.add(5);
    tree.add(15);

    tree.add(12);
    tree.add(19);

    tree.add(9);
    tree.add(13);
    tree.add(23);

    tree.add(10);

    System.out.println(tree);
  }
}
