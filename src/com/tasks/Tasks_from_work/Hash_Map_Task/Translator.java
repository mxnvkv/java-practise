package com.tasks.Tasks_from_work.Hash_Map_Task;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Translator {
  private static final HashMap<Character, String> letterToNumberMap = setLetterToNumberMap();
  private static final HashMap<String, Character> numberToLetterMap = setNumberToLetterMap();

  public static String translateToNumber(String word) {
    char[] letters = word.toCharArray();
    List<String> numbers = new ArrayList<>();

    for (char letter : letters) {
      numbers.add(letterToNumberMap.get(letter));
    }

    return String.join("", numbers);
  }

  // TODO: rewrite for all possible combinations
  public static String translateToWord(String number) {
    String[] numbers = number.split("");
    List<String> letters = new ArrayList<>();

    for (String num : numbers) {
      letters.add( Character.toString(numberToLetterMap.get(num)) );
    }

    return String.join(" ", letters);
  }

  private static ArrayList<String> readFromNumbersCodeFile() {
    ArrayList<String> fileLines = new ArrayList<>();

    try {
      File file = new File("src/com.tasks.work.Tasks_from_work/Hash_Map_Task/numbers_code.txt");
      BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
      String line;

      while ((line = bufferedReader.readLine()) != null) {
        fileLines.add(line);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }

    return fileLines;
  }

  private static HashMap<Character, String> setLetterToNumberMap() {
    ArrayList<String> fileLines = readFromNumbersCodeFile();
    HashMap<Character, String> hashMap = new HashMap<>();

    for (String line : fileLines) {
      String[] keyValue = line.split(" - ");
      hashMap.put(keyValue[0].charAt(0), keyValue[1]);
    }

    return hashMap;
  }

  private static HashMap<String, Character> setNumberToLetterMap() {
    ArrayList<String> fileLines = readFromNumbersCodeFile();
    HashMap<String, Character> hashMap = new HashMap<>();

    for (String line : fileLines) {
      String[] keyValue = line.split(" - ");
      hashMap.put(keyValue[1], keyValue[0].charAt(0));
    }

    return hashMap;
  }
}
