package pri.adam.dmail.utils.dbutil;

import org.apache.log4j.Logger;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Created by adam on 2014/12/5.
 */
public class DBManager {
    private static Logger logger = Logger.getLogger(DBManager.class);
    private Connection connection = null;

    public boolean conn(Properties properties){
        String driverName = properties.getProperty("db.driverName");
        String url = properties.getProperty("db.Url");
        String username = properties.getProperty("db.username");
        String password = properties.getProperty("db.password");

       try{
           Class.forName(driverName);
           connection = DriverManager.getConnection(url,username,password);
       }catch (ClassNotFoundException e) {
           logger.error("未找到相应的驱动包",e.getException());
           return false;
       } catch (SQLException e) {
           logger.error("无法获得数据库连接",e);
           return false;
       }

        logger.info("成功获得数据库连接");

        return true;
    }

    public boolean conn(InputStream confFileIn){
       Properties properties = null;
        try {
            properties = createProper(confFileIn);
        } catch (IOException e) {
            logger.error("不能打开文件流",e);
            return false;
        }

        return conn(properties);
    }

    public boolean conn(String confFilePath){
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(confFilePath);
        } catch (FileNotFoundException e) {
            logger.error("文件不存在",e);
            return false;
        }

        return conn(inputStream);
    }

    protected Properties createProper(InputStream inputStream) throws IOException {
        Properties properties = new Properties();
        properties.load(inputStream);

        return properties;
    }

    public boolean disConn(){
        try {
            DBToolkit.closeDBConnection(connection);
        } catch (SQLException e) {
            logger.error("无法正常关闭数据库连接",e);
            return false;
        }

        logger.info("成功关闭数据库连接");
        return true;
    }

    public boolean isConnected(){
        return !DBToolkit.isConnectionClosed(connection);
    }

    public Connection getConnection(){
        return connection;
    }

    public void closeAutoCommit(){
        assert connection!=null;
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            logger.error("无法关闭自动提交",e);
        }
    }
}
