package Tasks_from_work.Hash_Map_Task;

public class NumbersCode {
  private String code;

  NumbersCode() {}

  NumbersCode(String code) {
    this.code = code;
  }

  public String getCode() {
    return code;
  }

  @Override
  public String toString() {
    return code;
  }

  @Override
  public boolean equals(Object object) {
    if (this == object) return true;
    if (object == null || getClass() != object.getClass()) return false;

    NumbersCode numbersCode = (NumbersCode) object;
    return code.equals(numbersCode.code);
  }

  @Override
  public int hashCode() {
    return code.hashCode();
  }
}
