package com.tasks.Tasks_from_work.Tree_Depth;

public class Tree {
  private Node root;

  Tree() {}

  Tree(int number) {
    this.root = createNode(number);
  }

  public void addElement(int number) {
    Node element = createNode(number);
    Node parent = root;

    if (root == null) {
      this.root = createNode(number);
      return;
    }

    while (parent != null) {
      if (element.getValue() < parent.getValue()) {
        if (parent.getLeftChild() == null) {
          break;
        }

        parent = parent.getLeftChild();
      } else {
        if (parent.getRightChild() == null) {
          break;
        }

        parent = parent.getRightChild();
      }
    }

    element.setParent(parent);

    if (element.getValue() < parent.getValue()) {
      parent.setLeftChild(element);
    } else {
      parent.setRightChild(element);
    }
  }

  public int getDepth() {
    return getDepth(root);
  }

  public int getDepth(Node node) {
    if (node == null) {
      return 0;
    } else if (root.getLeftChild() == null && root.getRightChild() == null) {
      return 1;
    }

    int leftChildDepth = getDepth(node.getLeftChild());
    int rightChildDepth = getDepth(node.getRightChild());

    return Integer.max(leftChildDepth, rightChildDepth) + 1;
  }

  private Node createNode(int number) {
    return new Node(null, number);
  }

  private String getNodeData(Node node) {
    if (node == null) {
      return "";
    }

    if (node.getLeftChild() == null && node.getRightChild() == null ) {
      return "";
    }

    String leftChildData = getNodeData(node.getLeftChild());
    String rightChildData = getNodeData(node.getRightChild());

    return node.toString() + "\n" + leftChildData + "\n" + rightChildData;
  }

  @Override
  public String toString() {
    // TODO: implement correct toString() method for Tree
    return getNodeData(root);
  }
}
