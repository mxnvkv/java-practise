package LoggerApp;

import java.util.logging.*;

public class LoggerHandler {
  static Logger logger = Logger.getLogger("LoggerApp");

  public static void main(String[] args) {
    Handler handler = new ConsoleHandler();
    SimpleFormatter formatter = new SimpleFormatter();

    handler.setFormatter(formatter);
    handler.setLevel(Level.ALL);
    logger.setLevel(Level.ALL);
    logger.addHandler(handler);

    logger.log(Level.FINE, "This is working!");
  }
}
