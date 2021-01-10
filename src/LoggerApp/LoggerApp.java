package LoggerApp;

import java.util.logging.*;

//                Logger levels
// ---------------------------------------------
// SEVERE   - 1000 ( Serious failure )
// WARNING  - 900  ( Potential problem )
// INFO     - 800  ( General info )
// CONFIG   - 700  ( Configuration info )
// FINE     - 500  ( General developer info )
// FINER    - 400  ( Detailed developer info )
// FINEST   - 300  ( Specialized developer info )
// ALL      - Integer.MIN_VALUE ( capture all )

public class LoggerApp {
  static Logger logger =
    LogManager.getLogManager().getLogger(Logger.GLOBAL_LOGGER_NAME);

  public static void main(String[] args) {
    logger.setLevel(Level.INFO); // Will capture INFO level and above

    logger.log(Level.SEVERE, "System's malfunction");
    logger.log(Level.WARNING, "Potential vulnerability");
    logger.log(Level.FINE, "General staff");  // won't be displayed
    logger.log(Level.FINER, "Detailed info"); // because they're lower than INFO
  }
}
