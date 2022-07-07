package logging;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.config.Configuration;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.config.LoggerConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



import  org.apache.logging.log4j.Level;

public class LoggerMain {


    public static Logger logger = LoggerFactory.getLogger(LoggerMain.class);

    public static void main(String[] args) {

//        System.out.println("jhgasfsdfhjsadjk");
        logger.info("here 1 from info ...");
        logger.debug("here 1 from info ...");

//        LoggerContext loggerContext = (LoggerContext) LogManager.getContext(false);
//        Configuration configuration = loggerContext.getConfiguration();
//        LoggerConfig loggerConfig = configuration.getLoggerConfig("logging");
//        System.out.println("loggerConfig Name: "+ loggerConfig.getName());
//        System.out.println("loggerConfig level: "+ loggerConfig.getLevel());


        logger.info("here 2 from info ...");
        logger.debug("here 2 from info ...");
//        LoggerConfig rootLoggerConfig = configuration.getLoggerConfig("");
//        System.out.println("Root Name: "+ rootLoggerConfig.getName());
//        System.out.println("Root level: "+ rootLoggerConfig.getLevel());


//        loggerConfig.setLevel(Level.INFO);
//        loggerContext.updateLoggers();


//        System.out.println("loggerConfig Name: "+ loggerConfig.getName());
//        System.out.println("loggerConfig level: "+ loggerConfig.getLevel());



        LoggerContext loggerContext = (LoggerContext) LogManager.getContext(false);
        Configuration config = loggerContext.getConfiguration();
//        LoggerConfig loggerConfig = config.getLoggerConfig(LogManager.ROOT_LOGGER_NAME);
        LoggerConfig loggerConfig = config.getLoggerConfig("logging");
        loggerConfig.setLevel(Level.DEBUG);
        loggerContext.updateLoggers();  // This causes all Loggers to refetch information from their LoggerConfig.


        logger.info("here 3 from info ...***********************************************************************************************");
        logger.debug("here 3 from info ...***********************************************************************************************");



//        ThreadFactory namedThreadFactory = new ThreadFactoryBuilder()
//                .setNameFormat("my-sad-thread-%d")
//                .setUncaughtExceptionHandler((t, e) ->
//                        logger.error(" {} throws {}: {} here ", t.getName(), e.getClass().getName(), e.getMessage(), e)
//                ).build();
//
//
//        ExecutorService executorService = Executors.newFixedThreadPool(3, namedThreadFactory);


//        executorService.execute(() -> logData());
//        executorService.execute(() -> logData());
//        executorService.execute(() -> raiseException());
//        executorService.execute(() -> logData());
//        executorService.execute(() -> logData());
//        executorService.execute(() -> logData());
//        executorService.execute(() -> logData());
//        executorService.execute(() -> logData());



    }

    private static void raiseException() {

        nestedRaiseException();

    }

    private static void nestedRaiseException() {
        int x = 10;
        int testtttt = 0;

        int r = x / testtttt;

//        try {
//            int r = x / testtttt;
//
//        } catch (Exception ex) {
//            throw ex;
//        }


    }

    private static void logData() {
//        int i = 2;
        while (true) {
            logger.info("some data For this reason, future Maven versions might no longer support building such malformed projects.");
//            i--;
//            break;

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
                logger.error("InterruptedException: ", e);
            }
        }

    }
}
