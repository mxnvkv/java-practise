package Custom_Data_Structures.RedBlackTree;

public class RedBlackTree {
  private Node root;

  RedBlackTree() {}

  RedBlackTree(int number) {
    root = createNode(number, Color.BLACK);
  }

  public Node getRoot() {
    return root;
  }

  public void add(int number) {
    Node node = createNode(number, Color.RED);
    Node iterationNode = this.root;
    Node parent = null;

    // find parent for our node to insert
    while (iterationNode != null) {
      parent = iterationNode;

      if (number < iterationNode.getValue()) {
        iterationNode = iterationNode.getLeftChild();
      } else {
        iterationNode = iterationNode.getRightChild();
      }
    }

    if (parent == null) {
      root = node;
      root.setColor(Color.BLACK); // root is always black
    } else {
      node.setParent(parent);

      if (node.getValue() < parent.getValue()) {
        parent.setLeftChild(node);
      } else {
        parent.setRightChild(node);
      }

      fixTree(node);
    }
  }

  public Node get(int number) {
    Node node = null;
    Node currentNode = root;

    while (currentNode != null) {
      if (number == currentNode.getValue()) {
        node = currentNode;
        break;
      }

      if (number < currentNode.getValue()) {
        currentNode = currentNode.getLeftChild();
      } else {
        currentNode = currentNode.getRightChild();
      }
    }

    return node;
  }

  private void fixTree(Node node) {
    while (node.getParent() != null && node.getParent().getColor() == Color.RED) {
      Node parent = node.getParent();
      Node grandparent = node.getParent().getParent();

      // if node's parent is left child of node's grandparent
      if (node.getParent() == node.getParent().getParent().getLeftChild()) {
        Node uncle = grandparent.getRightChild();

        if (uncle != null && uncle.getColor() == Color.RED) {
          changeColorForAncestors(parent, uncle, grandparent);
          node = grandparent;
        } else { // uncle is black
          // triangle case
          if (node == parent.getRightChild()) {
            node = parent; // changing node reference, have to change ancestors!
            rotateLeft(node);
          }

          // line case
          node.getParent().setColor(Color.BLACK);
          node.getParent().getParent().setColor(Color.RED);
          rotateRight(node.getParent().getParent());
        }
      } else { // then the node's parent is right child of node's grandparent
        Node uncle = grandparent.getLeftChild();

        if (uncle.getColor() == Color.RED) {
          changeColorForAncestors(parent, uncle, grandparent);
          node = grandparent;
        } else { // uncle is black
          // triangle case
          if (node == parent.getLeftChild()) {
            node = parent; // changing node reference, have to change ancestors!
            rotateRight(parent);
          }

          // line case
          node.getParent().setColor(Color.BLACK);
          node.getParent().getParent().setColor(Color.RED);
          rotateLeft(node.getParent().getParent());
        }
      }

    }

    root.setColor(Color.BLACK);
  }

  private void changeColorForAncestors(Node parent, Node uncle, Node grandparent) {
    parent.setColor(Color.BLACK);
    uncle.setColor(Color.BLACK);
    grandparent.setColor(Color.RED);
  }

  private Node createNode(int number, Color color) {
    return new Node(number, color);
  }

  private void rotateLeft(Node node) {
    Node rightChild = node.getRightChild();
    node.setRightChild(rightChild.getLeftChild());

    if (rightChild.getLeftChild() != null) {
      rightChild.getLeftChild().setParent(node);
    }

    rightChild.setParent(node.getParent());

    if (node.getParent() == null) {
      root = rightChild;
    } else if (node == node.getParent().getLeftChild()) {
      node.getParent().setLeftChild(rightChild);
    } else {
      node.getParent().setRightChild(rightChild);
    }

    rightChild.setLeftChild(node);
    node.setParent(rightChild);
  }

  private void rotateRight(Node node) {
    Node leftChild = node.getLeftChild();
    node.setLeftChild(leftChild.getRightChild());

    if (leftChild.getRightChild() != null) {
      leftChild.getRightChild().setParent(node);
    }

    leftChild.setParent(node.getParent());

    if (node.getParent() == null) {
      root = leftChild;
    } else if (node == node.getParent().getLeftChild()) {
      node.getParent().setLeftChild(leftChild);
    } else {
      node.getParent().setRightChild(leftChild);
    }

    leftChild.setRightChild(node);
    node.setParent(leftChild);
  }

  // TODO: remove method below and implement correct toString() method for Red-Black Tree

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
    return getNodeData(root);
  }
}
