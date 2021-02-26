package Tasks_from_work.Hash_Map_Task;

import java.util.HashMap;
import static Tasks_from_work.Hash_Map_Task.Translator.*;

public class Main {
  public static void main(String[] args) {
    HashMap<NumbersCode, Integer> hashMap = new HashMap<>();

    hashMap.put(new NumbersCode("Lakers"), 3);
    hashMap.put(new NumbersCode("Jazz"), 1);
    hashMap.put(new NumbersCode("Clippers"), 2);

    // hashMap.forEach((code, value) -> {
    //   System.out.println(code.getCode() + " : " + value);
    // });

    System.out.println(translateToNumber("orange"));
    System.out.println(translateToWord("666 777 2 66 4 33"));
  }
}
