package Tasks_from_work.Hash_Map_Task;

import java.util.ArrayList;

public class Main {
  public static void main(String[] args) {
    Dictionary dictionary = new Dictionary("src/Tasks_from_work/Hash_Map_Task/dictionary.txt");

    ArrayList<String> list = dictionary.find("6262");

    list.forEach(System.out::println);
  }
}
