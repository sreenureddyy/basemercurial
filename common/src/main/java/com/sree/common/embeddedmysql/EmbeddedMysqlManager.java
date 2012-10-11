/**
 * 
 *//*
package com.sree.common.embeddedmysql;

import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.mysql.management.MysqldResource;
import com.mysql.management.MysqldResourceI;
 
*//**
 * Manages the life cycle of the embedded MySQL database.
 * This manager uses the MySQL Connector/MXJ and Mysql Connector/J JDBC driver to manage the
 * embedded MySQL DB instance.
 *
 * @see http://dev.mysql.com/doc/refman/5.1/en/connector-mxj.html for more details
 *//*
public class EmbeddedMysqlManager {
    private Logger logger = Logger.getLogger(EmbeddedMysqlManager.class);
    private MysqldResource mysqldResource;
    private String baseDatabaseDir =  System.getProperty("user.dir");
    private String databaseName = "pharma_db";
    private int port = 3336;
    private String username = "root";
    private String password = "";
    private List<String> sqlScripts = new ArrayList<String>();
    private DriverManagerDataSource datasource;
 
    //-------------------------------------------------------------------------
    *//**
     * Starts the mysql database
     * @return
     *//*
    public void startDatabase() {
        if (logger.isDebugEnabled()) {
            logger.debug("=============== Starting Embedded MySQL using these parameters ===============");
            logger.debug("baseDatabaseDir : " + baseDatabaseDir);
            logger.debug("databaseName : " + databaseName);
            logger.debug("host : localhost (hardcoded)");
            logger.debug("port : " + port);
            logger.debug("username : " + username);
            logger.debug("password : " + password);
            logger.debug("=============================================================================");
        }
 
        File databaseDir = new File(new File(baseDatabaseDir), databaseName);
        mysqldResource = new MysqldResource(databaseDir);
 
        Map<String, String> database_options = new HashMap<String, String>();
        database_options.put(MysqldResourceI.PORT, Integer.toString(port));
        database_options.put(MysqldResourceI.INITIALIZE_USER, "true");
        database_options.put(MysqldResourceI.INITIALIZE_USER_NAME, username);
        database_options.put(MysqldResourceI.INITIALIZE_PASSWORD, password);

        mysqldResource.start("embedded-mysqld-thread-" + System.currentTimeMillis(), database_options);
 
        if (!mysqldResource.isRunning()) {
            throw new RuntimeException("MySQL did not start.");
        }else{
        	DataSource datasource = getDatasource();
        	try {
				printQueryResults(datasource, "SELECT VERSION()");
			} catch (Exception e) {
				e.printStackTrace();
			}
        }
 
        logger.info("MySQL started successfully @ " + System.currentTimeMillis());
 
        try {
            if (!sqlScripts.isEmpty()) {
                SimpleJdbcTemplate simpleJdbcTemp = new SimpleJdbcTemplate(getDatasource());
                logger.info("Executing scripts...");
 
                for (String script : sqlScripts) {
                    logger.info("Executing script [" + script + "]");
                   SimpleJdbcTestUtils.executeSqlScript(simpleJdbcTemp, new ClassPathResource(script), false);
                }
            } else {
                logger.info("No scripts to load...");
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            if ((mysqldResource != null) && (mysqldResource.isRunning())) {
                shutdownDatabase();
            }
        }
    }
 
    public void shutdownDatabase() {
        mysqldResource.shutdown();
        logger.debug("=============== MySQL shutdown successfully ===============");
        if (mysqldResource.isRunning() == false) {
            logger.info(">>>>>>>>>> DELETING MYSQL BASE DIR [" + mysqldResource.getBaseDir() + "] <<<<<<<<<<");
            try {
                FileUtils.forceDelete(mysqldResource.getBaseDir());
            } catch (IOException e) {
                logger.error(e.getMessage(), e);
            }
        }
    }
 
    *//**
     * Gets a {@link DataSource} for the embedded DB managed by this manager
     * @return
     *//*
    public DataSource getDatasource() {
        if (!mysqldResource.isRunning()) {
            logger.error("MySQL instance not found... Terminating");
            throw new RuntimeException("Cannot get Datasource, MySQL instance not started.");
        }
 
        if (datasource == null) {
            datasource = new DriverManagerDataSource();
            datasource.setDriverClassName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:" + port + "/" + databaseName +
                         "?" + "createDatabaseIfNotExist=true&sessionVariables=FOREIGN_KEY_CHECKS=0";
 
            datasource.setUrl(url);
            datasource.setUsername(username);
            datasource.setPassword(password);
        }
        return datasource;
    }
 
    public static void printQueryResults(DataSource datasource, String SQLquery)
            throws Exception {
    	Connection conn = datasource.getConnection();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(SQLquery);
        int columns = rs.getMetaData().getColumnCount();
        System.out.println("------------------------");
        System.out.println();
        while (rs.next()) {
            for (int i = 1; i <= columns; i++) {
                System.out.println(rs.getString(i));
            }
            System.out.println();
        }
        rs.close();
        stmt.close();
        System.out.println("------------------------");
        System.out.flush();
        Thread.sleep(100); // wait for System.out to finish flush
    }
    
    //-------------------------------------------------------------------------
    *//**
     * @return the baseDatabaseDir
     *//*
    public final String getBaseDatabaseDir() {
        return baseDatabaseDir;
    }
 
    *//**
     * @param baseDatabaseDir the baseDatabaseDir to set
     *//*
    public final void setBaseDatabaseDir(String baseDatabaseDir) {
        this.baseDatabaseDir = baseDatabaseDir;
    }
 
    *//**
     * @return the databaseName
     *//*
    public final String getDatabaseName() {
        return databaseName;
    }
 
    *//**
     * @param databaseName the databaseName to set
     *//*
    public final void setDatabaseName(String databaseName) {
        this.databaseName = databaseName;
    }
 
    *//**
     * @return the port
     *//*
    public final int getPort() {
        return port;
    }
 
    *//**
     * @param port the port to set
     *//*
    public final void setPort(int port) {
        this.port = port;
    }
 
    *//**
     * @return the username
     *//*
    public final String getUsername() {
        return username;
    }
 
    *//**
     * @param username the username to set
     *//*
    public final void setUsername(String username) {
        this.username = username;
    }
 
    *//**
     * @return the password
     *//*
    public final String getPassword() {
        return password;
    }
 
    *//**
     * @param password the password to set
     *//*
    public final void setPassword(String password) {
        this.password = password;
    }
 
    *//**
     * @return the sqlScripts
     *//*
    public final List<String> getSqlScripts() {
        return sqlScripts;
    }
 
    *//**
     * @param sqlScripts the sqlScripts to set
     *//*
    public final void setSqlScripts(List<String> sqlScripts) {
        this.sqlScripts = sqlScripts;
    }
}*/