package Tasks_from_work.Hash_Map_Task;

import java.util.Objects;

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
  public boolean equals(Object object) {
    if (this == object) return true;
    if (object == null || getClass() != object.getClass()) return false;

    NumbersCode numbersCode = (NumbersCode) object;
    return code.equals(numbersCode.code);
  }

  @Override
  public int hashCode() {
    int hash = 12;
    hash = 31 * hash + (code == null ? 0 : Objects.hash(code));

    return hash;
  }
}
