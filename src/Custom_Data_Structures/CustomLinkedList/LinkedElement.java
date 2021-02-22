package Custom_Data_Structures.CustomLinkedList;

public class LinkedElement {
  private int value;
  private LinkedElement nextElement;

  LinkedElement() {}

  LinkedElement(int value, LinkedElement nextElement) {
    this.value = value;
    this.nextElement = nextElement;
  }

  public int getValue() {
    return value;
  }

  public LinkedElement getNextElement() {
    return nextElement;
  }

  public void setValue(int value) {
    this.value = value;
  }

  public void setNextElement(LinkedElement nextElement) {
    this.nextElement = nextElement;
  }

  @Override
  public String toString() {
    return String.format(
      "{ value: %d, nextElement: %d }",
      value,
      nextElement == null ? null : nextElement.getValue()
    );
  }
}
