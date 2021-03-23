package com.tasks.PropertiesApp;

import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

public class PropertiesApp {
  public static void main(String[] args) {
    readProperties();
  }

  public static void setProperties() {
    Properties properties = new Properties();

    properties.setProperty("LAL", "Los Angeles Lakers");
    properties.setProperty("CAV", "Cleveland Cavaliers");
    properties.setProperty("MIA", "Miami Heat");
    properties.setProperty("WAR", "Golden State Warriors");
    properties.setProperty("TOR", "Toronto Raptors");

    try(Writer writer = Files.newBufferedWriter(Paths.get("basketball_teams.properties"))) {
      properties.store(writer, "Basketball teams and their abbreviation names");
    } catch (Exception ex) {
      System.out.println(ex.getClass().getSimpleName() + " - " + ex.getMessage());
    }
  }

  public static void readProperties() {
    Properties properties = new Properties();

    try(Reader reader = Files.newBufferedReader(Paths.get("basketball_teams.properties"))) {
      properties.load(reader);
    } catch (Exception ex) {
      System.out.println(ex.getClass().getSimpleName() + " - " + ex.getMessage());
    }

    String lakers = properties.getProperty("LAL");
    String raptors = properties.getProperty("TOR");

    System.out.println("LAL: " + lakers + "\nTOR: " + raptors);
  }
}
