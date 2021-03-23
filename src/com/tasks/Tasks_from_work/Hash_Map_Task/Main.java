package com.tasks.Tasks_from_work.Hash_Map_Task;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Main {
  private HashMap<NumbersCode, ArrayList<String>> hashMap = new HashMap();

  Main() {}

  Main(String pathToDictionary) {
    initialize(pathToDictionary);
  }

  public static void main(String[] args) {
    Main main = new Main("src/com/tasks/Tasks_from_work/Hash_Map_Task/dictionary.txt");

    ArrayList<String> list = main.find("6262");

    list.forEach(System.out::println);
  }

  private ArrayList<String> find(String code) {
    return hashMap.get(new NumbersCode(code));
  }

  private void initialize(String path) {
    try {
      File file = new File(path);
      BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
      String line;

      while ((line = bufferedReader.readLine()) != null) {
        String[] parts = line.split(" - ");
        String code = parts[0];
        String[] array = parts[1].replaceAll("\\p{P}","").split(" ");
        ArrayList<String> list = new ArrayList<>(Arrays.asList(array));

        this.hashMap.put(new NumbersCode(code), list);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
