package Tasks_from_work.Hash_Map_Task;

import java.util.Hashtable;

public class Main {
  public static void main(String[] args) {
    Hashtable<NumbersCode, String> hashMap = new Hashtable<>();

    hashMap.put(new NumbersCode("5852"), "luck");
    hashMap.put(new NumbersCode("5852"), "juck");
    hashMap.put(new NumbersCode("27753"), "apple");
    hashMap.put(new NumbersCode("672643"), "orange");
    hashMap.put(new NumbersCode("5852"), "kuck");

    System.out.println(hashMap.size());
  }
}
