package activiti;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.impl.cfg.StandaloneProcessEngineConfiguration;


public class Main {

    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/activiti";
    private static final String JDBC_USERNAME = "mina";
    private static final String JDBC_PWD = "#Mina_1234$";
    private static final String DB_TYPE = "";


    public static void main(String[] args) {

        ProcessEngineConfiguration config = (StandaloneProcessEngineConfiguration)ProcessEngineConfiguration
                .createStandaloneProcessEngineConfiguration();
        config.setJdbcDriver(JDBC_DRIVER);
        config.setJdbcPassword(JDBC_PWD);
        config.setJdbcUrl(JDBC_URL);
        config.setJdbcUsername(JDBC_USERNAME);
//        config.setDatabaseType(DB_TYPE);
        config.setDatabaseSchemaUpdate("true");
        ProcessEngine processEngine = config.buildProcessEngine();

    }
}
