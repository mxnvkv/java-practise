package XMLapp;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

public class XMLapp {
  public static void main(String[] args) {
    readXML();
  }

  public static void setXML() {
    Properties properties = new Properties();

    properties.setProperty("LAL", "17");
    properties.setProperty("CAV", "1");
    properties.setProperty("MIA", "3");
    properties.setProperty("WAR", "5");
    properties.setProperty("TOR", "1");

    try(OutputStream outputStream = Files.newOutputStream(Paths.get("basketball_championships.xml"))) {
      properties.storeToXML(outputStream, "Basketball teams and their championships");
    } catch (Exception ex) {
      System.out.println(ex.getClass().getSimpleName() + " - " + ex.getMessage());
    }
  }

  public static void readXML() {
    Properties properties = new Properties();

    try(InputStream inputStream = Files.newInputStream(Paths.get("basketball_championships.xml"))) {
      properties.loadFromXML(inputStream);
    } catch (Exception ex) {
      System.out.println(ex.getClass().getSimpleName() + " - " + ex.getMessage());
    }

    String lakers = properties.getProperty("LAL");
    String cavaliers = properties.getProperty("CAV");

    String lineToPrint = String.format(
      "Lakers have %s rings and Cavaliers have %s ring",
      lakers,
      cavaliers
    );

    System.out.println(lineToPrint);
  }
}
