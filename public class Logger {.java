public class Logger {
    private Logger() {}
    private static class LoggerHolder {
        private static final Logger INSTANCE = new Logger();
    }
    public static Logger getInstance() {
        return LoggerHolder.INSTANCE;
    }
    public void log(String message) {
        System.out.println("Log message: " + message);
    }
}
  class Main {
    public static void main(String[] args) {
        Logger logger = Logger.getInstance();

        logger.log("Starting the application.");
        logger.log("Application is running.");
        logger.log("Application is closing.");
    }
}

