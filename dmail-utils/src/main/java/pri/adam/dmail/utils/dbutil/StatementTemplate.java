package pri.adam.dmail.utils.dbutil;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by adam on 2014/12/5.
 */
public class StatementTemplate<R> {
    private Connection connection;
    private String exeSQL;
    private Statement statement;
    private PreparedStatement preparedStatement;
    private static Logger logger = Logger.getLogger(StatementTemplate.class);

    public StatementTemplate(Connection connection){
        this.connection = connection;
    }

    public StatementTemplate(Connection connection, String exeSQL) {
        this.connection = connection;
        this.exeSQL = exeSQL;
    }

    public R executeStmSQL(StatmentExecutor statmentExecutor) {
        R result = null;

        try {
            if (connection.getAutoCommit()) {
                connection.setAutoCommit(false);
            }
            statement = connection.createStatement();
            result = statmentExecutor.execute(statement);
            connection.commit();
            statement.close();
        } catch (SQLException e) {
            logger.error("无法提交数据库操作", e);
            try {
                connection.rollback();
                DBToolkit.closeStatment(statement);
                return null;
            } catch (SQLException e1) {
                logger.error("无法回滚", e);
                return null;
            }
        }
        return result;
    }

    public R executePreparedSQL(PreparedExecutor preparedExecutor) {
        R result = null;
        try {
            if (connection.getAutoCommit()) {
                connection.setAutoCommit(false);
            }
            preparedStatement = connection.prepareStatement(exeSQL);
            result = preparedExecutor.execute(preparedStatement, exeSQL);
            connection.commit();
            preparedStatement.close();
        } catch (SQLException e) {
            logger.error("无法提交数据库操作", e);
            try {
                connection.rollback();
                DBToolkit.closePreparedStatment(preparedStatement);
                return null;
            } catch (SQLException e1) {
                logger.error("无法回滚", e);
                return null;
            }
        }

        return result;

    }

    public abstract class StatmentExecutor {
        public abstract R execute(Statement stm) throws SQLException;
    }

    public abstract class PreparedExecutor {
        public abstract R execute(PreparedStatement preStatment, String exeSql) throws SQLException;
    }
}
