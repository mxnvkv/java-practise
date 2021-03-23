package Tasks_from_work.Hash_Map_Task;

import java.io.*;
import java.util.ArrayList;
import static Tasks_from_work.Hash_Map_Task.Translator.*;

public class Main {
  static HashMapWrapper hashMap = new HashMapWrapper();

  public static void main(String[] args) throws IOException {
    putWordToHashMap(readFromNumbersCodeFile());

    hashMap.createDictionary();
    // change numbersCode here
    System.out.println(hashMap.get("6262"));
  }

  private static void putWordToHashMap(ArrayList<String> arrayList) {
    for (int i = 0; i < arrayList.size(); i++) {
      hashMap.add(translateToNumber(arrayList.get(i)), arrayList.get(i));
    }
  }

  private static ArrayList<String> readFromNumbersCodeFile() {
    ArrayList<String> words = new ArrayList<>();

    try {
      File file = new File("src/Tasks_from_work/Hash_Map_Task/new_words.txt");
      BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
      String line;

      while ((line = bufferedReader.readLine()) != null) {
        if (line.matches("[a-zA-Z]+")) {
          words.add(line.toLowerCase());
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }

    return words;
  }

  // private static ArrayList<String> readFromNumbersCodeFile() {
  //   ArrayList<String> fileLines = new ArrayList<>();
  //
  //   try {
  //     File file = new File("src/Tasks_from_work/Hash_Map_Task/words.txt");
  //     BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
  //     String line;
  //
  //     while ((line = bufferedReader.readLine()) != null) {
  //       if (line.matches("[a-zA-Z]+")) {
  //         fileLines.add(line.toLowerCase());
  //       }
  //     }
  //   } catch (Exception e) {
  //     e.printStackTrace();
  //   }
  //
  //   return fileLines;
  // }
  //
  // public static void writeFile4(ArrayList<String> arrayList) throws IOException {
  //   File fout = new File("src/Tasks_from_work/Hash_Map_Task/new_words.txt");
  //   FileOutputStream fos = new FileOutputStream(fout);
  //
  //   OutputStreamWriter osw = new OutputStreamWriter(fos);
  //
  //   for (int i = 0; i < arrayList.size(); i++) {
  //     osw.write(arrayList.get(i) + "\n");
  //   }
  //
  //   osw.close();
  // }
}
