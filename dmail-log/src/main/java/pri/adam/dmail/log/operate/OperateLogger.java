package pri.adam.dmail.log.operate;

import org.apache.log4j.Logger;
import pri.adam.dmail.utils.dbutil.PreparedStatementTemplate;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by adam on 2014/12/5.
 */
public class OperateLogger implements OperateLogSql{
    private static OperateLogger operator = null;
    private static Connection connection = null;
    private static Logger logger = Logger.getLogger(OperateLogger.class);
    private OperateLogger(){};

    public static OperateLogger getInstance(Connection conn){
        assert conn!= null;
        if (connection!=null && connection != conn){
            logger.warn("使用了不同的数据库连接");
        }
        if (operator == null){
            connection  = conn;
            operator = new OperateLogger();
        }

        return operator;
    }

    public void addOperateLog(final OperateBean operateBean){
        PreparedStatementTemplate template = new PreparedStatementTemplate(connection,addOperateLog);
        template.executeSQL(template.new Executor() {
            @Override
            public Object execute(PreparedStatement preStatement, String exeSql) throws SQLException {
                preStatement.setDate(1,operateBean.getDatetime());
                preStatement.setString(2,operateBean.getInitiator());
                preStatement.setString(3,operateBean.getF_describe());
                preStatement.setInt(4,operateBean.getF_level());
                preStatement.setInt(5,operateBean.getResult());

                preStatement.execute();
                return null;
            }
        });
    }

    public Set<OperateBean> selectAllOperateLog(){
        PreparedStatementTemplate<Set<OperateBean>> template =
                new PreparedStatementTemplate<Set<OperateBean>>(connection,seeAllOperateLog);

        return template.executeSQL(template.new Executor() {
            @Override
            public Set<OperateBean> execute(PreparedStatement preStatment, String exeSql) throws SQLException {
                ResultSet resultSet = preStatment.executeQuery();
                Set<OperateBean> beans = new HashSet<OperateBean>();

                if (resultSet.first()){
                    do {
                        OperateBean bean = reverseBean(resultSet);
                        beans.add(bean);
                    }while (resultSet.next());
                }

                return beans;
            }
        });
    }

    public Set<OperateBean> selectRangeOperateLog(final int start,final int end){
        PreparedStatementTemplate<Set<OperateBean>> template =
                new PreparedStatementTemplate<Set<OperateBean>>(connection,seeAllOperateLog);

        return template.executeSQL(template.new Executor() {
            @Override
            public Set<OperateBean> execute(PreparedStatement preStatment, String exeSql) throws SQLException {
                ResultSet resultSet = preStatment.executeQuery();
                Set<OperateBean> beans = new HashSet<OperateBean>();

                int index = 0;
                if (resultSet.first()){
                    do {
                        index++;
                        if (start<=index && index<=end){
                            OperateBean bean = reverseBean(resultSet);
                            beans.add(bean);
                        }
                    }while (resultSet.next());
                }

                return beans;
            }
        });
    }

    public Set<OperateBean> selectStartOperateLog(final int start){
        return selectRangeOperateLog(start, Integer.MAX_VALUE);
    }

    public Set<OperateBean> selectEndOperateLog(final int end){
        return selectRangeOperateLog(Integer.MIN_VALUE,end);
    }

    protected OperateBean reverseBean(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt(1);
        Date date = resultSet.getDate(2);
        String initiator = resultSet.getString(3);
        String describe = resultSet.getString(4);
        int level = resultSet.getInt(5);
        int result = resultSet.getInt(6);
        OperateBean bean = new OperateBean(id,date,initiator,describe,level,result);
        return bean;
    }

}
