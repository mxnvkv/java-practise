package Tasks_from_work.Hash_Map_Task;

import java.util.HashMap;

public class Main {
  public static void main(String[] args) {
    HashMap<NumbersCode, String> hashMap = new HashMap<>();

    hashMap.put(new NumbersCode("2 7 7 555 33"), "apple");
    hashMap.put(new NumbersCode("7 33 2 222 44"), "peach");
    hashMap.put(new NumbersCode("7 444 66 33 2 7 7 555 33"), "pineapple");
    hashMap.put(new NumbersCode("4 777 2 7 33"), "grape");
    hashMap.put(new NumbersCode("666 777 2 66 4 33"), "orange");

    System.out.println(hashMap.get(new NumbersCode("2 7 7 555 33")));
  }
}
