package CmdLineApp;

import java.io.BufferedReader;
import java.nio.file.Files;
import java.nio.file.Paths;

// in order to add file as a command line argument, go to:
// Run -> Edit Configurations -> Program Arguments

public class CmdLineApp {
  public static void main(String[] args) {
    if (args.length == 0) {
      System.out.println("Please, provide file name");
      return;
    }

    String filename = args[0];
    if (!Files.exists(Paths.get(filename))) {
      System.out.println("No files find with following name: " + filename);
    }

    readFile(filename);
  }

  public static void readFile(String filename) {
    System.out.println();

    try (BufferedReader reader = Files.newBufferedReader(Paths.get(filename))) {
      String line = null;

      while((line = reader.readLine()) != null) {
        System.out.println(line);
      }
    } catch (Exception ex) {
      System.out.println(ex.getClass().getSimpleName() + " - " + ex.getMessage());
    }
  }
}
