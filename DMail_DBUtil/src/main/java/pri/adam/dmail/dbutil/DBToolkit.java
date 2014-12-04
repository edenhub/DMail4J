package pri.adam.dmail.dbutil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by adam on 2014/12/5.
 */
public class DBToolkit {

    public static void closeDBConnection(Connection connection) throws SQLException {
        if (connection != null)
            connection.close();
    }

    public static void closeStatment(Statement statement) throws SQLException {
        if (statement!=null)
            statement.close();
    }

    public static void closePreparedStatment(PreparedStatement preparedStatement) throws SQLException {
        if (preparedStatement!=null)
            preparedStatement.close();
    }

    public static boolean isConnectionClosed(Connection connection){
        return connection==null ? true : false;
    }

    public static boolean isStatmentClosed(Statement statement){
        return statement==null ? true : false;
    }

    public static boolean isPreparedStatementClosed(PreparedStatement preparedStatement){
        return preparedStatement==null ? true : false;
    }
}
