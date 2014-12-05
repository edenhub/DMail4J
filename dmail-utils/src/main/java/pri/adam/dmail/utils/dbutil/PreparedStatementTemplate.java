package pri.adam.dmail.utils.dbutil;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by adam on 2014/12/5.
 */
public class PreparedStatementTemplate<R> {
    private Connection connection;
    private String exeSQL;
    private PreparedStatement preparedStatement;
    private static Logger logger = Logger.getLogger(PreparedStatementTemplate.class);

    public PreparedStatementTemplate(Connection connection,String exeSQL){
        this.connection = connection;
        this.exeSQL = exeSQL;
    }

    public R executeSQL(Executor executor){
        R result = null;
        try{
            if (connection.getAutoCommit()){
                connection.setAutoCommit(false);
            }
            preparedStatement = connection.prepareStatement(exeSQL);
            result = executor.execute(preparedStatement,exeSQL);
            connection.commit();
            preparedStatement.close();
        } catch (SQLException e) {
            logger.error("无法提交数据库操作",e);
            try {
                connection.rollback();
                DBToolkit.closePreparedStatment(preparedStatement);
                return null;
            } catch (SQLException e1) {
                logger.error("无法回滚",e);
                return null;
            }
        }

        return result;

    }

    public abstract class Executor{
        public abstract R execute(PreparedStatement preStatment, String exeSql) throws SQLException;
    }
}
