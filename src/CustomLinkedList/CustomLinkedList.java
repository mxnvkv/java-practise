package CustomLinkedList;

public class CustomLinkedList {
  LinkedElement head;

  CustomLinkedList() {}

  CustomLinkedList(int ...elements) {
    fillList(elements);
  }

  public void add(int value) {
    LinkedElement tail = getTail();

    if (tail == null) {
      head = createElement(value);
    } else {
      tail.setNextElement(createElement(value));
    }
  }

  public void add(int index, int value) {
    if (index == 0) {
      LinkedElement newElement = createElement(value);
      LinkedElement currentHeadElement = head;

      head = newElement;
      newElement.setNextElement(currentHeadElement);
    } else if (index < size()) {
      LinkedElement previousElement = get(index - 1);
      LinkedElement currentElement = previousElement.getNextElement();
      LinkedElement newElement = createElement(value);

      previousElement.setNextElement(newElement);
      newElement.setNextElement(currentElement);
    } else if (index == size()) {
      add(value);
    }
  }

  public LinkedElement get(int index) {
    int counter = 0;
    LinkedElement currentElement = head;

    while (counter < index && currentElement != null) {
      currentElement = currentElement.getNextElement();
      counter++;
    }

    return currentElement;
  }

  public int size() {
    int counter = 0;
    LinkedElement currentElement = head;

    while (currentElement != null) {
      counter++;
      currentElement = currentElement.getNextElement();
    }

    return counter;
  }

  private LinkedElement createElement(int value) {
    return new LinkedElement(value, null);
  }

  private LinkedElement getTail() {
    if (head == null) return null;

    return get(size() - 1);
  }

  private void fillList(int ...elements) {
    for (int i = 0; i < elements.length; i++) {
      add(elements[i]);
    }
  }

  @Override
  public String toString() {
    String list = "[\n";
    LinkedElement currentElement = head;

    while (currentElement != null) {
      list += "  " + currentElement + "\n";
      currentElement = currentElement.getNextElement();
    }

    list += "]";

    return list;
  }
}
