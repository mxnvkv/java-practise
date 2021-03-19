package Tasks_from_work.Hash_Map_Task;

import java.util.ArrayList;
import java.util.HashMap;

public class HashMapWrapper {
  private HashMap<NumbersCode, ArrayList<String>> hashMap = new HashMap<>();

  public void add(String numbersCode, String word) {
    NumbersCode code = new NumbersCode(numbersCode);

    ArrayList<String> list = hashMap.get(code);

    if (list == null) {
      ArrayList<String> newList = new ArrayList<>();
      newList.add(word);
      hashMap.put(code, newList);
    } else {
      list.add(word);
      hashMap.put(code, list);
    }
  }

  public ArrayList<String> get(String numbersCode) {
    NumbersCode code = new NumbersCode(numbersCode);
    return hashMap.get(code);
  }

  public void print() {
    hashMap.entrySet().forEach(entry -> {
      System.out.println(entry.getKey() + " " + entry.getValue());
    });
  }
}
