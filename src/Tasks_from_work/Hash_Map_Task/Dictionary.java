package Tasks_from_work.Hash_Map_Task;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;

public class Dictionary {
  private HashMapWrapper hashMap = new HashMapWrapper();

  Dictionary() {}

  Dictionary(String path) {
    createHashMapFromDictionary(path);
  }

  public ArrayList<String> find(String code) {
    return hashMap.get(code);
  }

  private void createHashMapFromDictionary(String path) {
    try {
      File file = new File(path);
      BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
      String line;

      while ((line = bufferedReader.readLine()) != null) {
        String[] parts = line.split(" - ");
        String code = parts[0];
        String[] array = parts[1].replaceAll("\\p{P}","").split(" ");
        ArrayList<String> list = new ArrayList<>(Arrays.asList(array));

        this.hashMap.add(code, list);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
