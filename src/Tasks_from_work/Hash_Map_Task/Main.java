package Tasks_from_work.Hash_Map_Task;

import java.util.HashMap;

public class Main {
  public static void main(String[] args) {
    HashMap<NumbersCode, Integer> hashMap = new HashMap<>();

    hashMap.put(new NumbersCode("Lakers"), 3);
    hashMap.put(new NumbersCode("Jazz"), 1);
    hashMap.put(new NumbersCode("Clippers"), 2);
    hashMap.put(new NumbersCode("Lakers"), 2);
  }
}
