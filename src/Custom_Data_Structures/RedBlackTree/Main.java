package Custom_Data_Structures.RedBlackTree;

public class Main {
  public static void main(String[] args) {
    RedBlackTree tree = new RedBlackTree();

    tree.add(13);

    tree.add(8);
    tree.add(17);

    tree.add(1);
    tree.add(11);
    tree.add(15);
    tree.add(25);

    tree.add(6);
    tree.add(22);
    tree.add(27);

    tree.remove(8);

    System.out.println(tree);
  }
}
