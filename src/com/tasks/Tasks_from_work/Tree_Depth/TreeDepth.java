package com.tasks.Tasks_from_work.Tree_Depth;

public class TreeDepth {
  public static void main(String[] args) {
    Tree tree = new Tree(50);

    // Level 2
    tree.addElement(20);
    tree.addElement(90);

    // Level 3
    tree.addElement(15);
    tree.addElement(30);
    tree.addElement(60);
    tree.addElement(120);

    // Level 4
    tree.addElement(7);
    tree.addElement(18);

    // Level 5
    tree.addElement(1);
    tree.addElement(10);
    tree.addElement(19);

    // Level 6
    tree.addElement(9);

    System.out.println(tree.getDepth());
  }
}
