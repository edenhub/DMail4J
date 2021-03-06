package pri.adam.dmail.users.action;

import org.apache.log4j.Logger;
import pri.adam.dmail.log.operate.OperateBean;
import pri.adam.dmail.log.operate.OperateLogger;
import pri.adam.dmail.users.model.Contract;
import pri.adam.dmail.users.model.User4Proxy;
import pri.adam.dmail.users.model.User4Server;
import pri.adam.dmail.utils.dbutil.DBToolkit;
import pri.adam.dmail.utils.dbutil.StatementTemplate;

import javax.xml.transform.Result;
import java.sql.*;
import java.text.MessageFormat;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by adam on 2014/12/6.
 */
public class UserAction implements UserActionSql {
    private static UserAction action = null;
    private static Connection connection = null;
    private static Logger logger = Logger.getLogger(UserAction.class);
    private static OperateLogger operateLogger;
    private OperateBean  operateBean = new OperateBean(SYSTEM_INITIATOR, OperateBean.OperateLevel.SYSTEM);
    private static final String SYSTEM_INITIATOR = "SYSTEM";

//    private UserFromServer serverAction;

    private UserAction(){}

    public static UserAction getInstance(Connection conn){
        assert conn!=null;
        if (connection!=null && connection != conn){
            logger.warn("使用了不同的数据库连接");
        }

        if (action == null){
            connection = conn;
            action = new UserAction();
            operateLogger = OperateLogger.getInstance(connection);
        }

        return action;
    }

//    public UserFromServer getServerAction() {
//        return serverAction;
//    }
//
//    public void setServerAction(UserFromServer serverAction) {
//        this.serverAction = serverAction;
//    }

    public boolean addUser(final User4Proxy proxy){
//        assert serverAction!=null;
        StatementTemplate<Boolean> template =
                new StatementTemplate<Boolean>(connection);
        Object resO = template.executeStmSQL(template.new StatmentExecutor() {
            @Override
            public Boolean execute(Statement stm) throws SQLException {
                String proxySql = MessageFormat.format(addProxySql,proxy.toObjectArray());
                User4Server server = proxy.toUser4Server();
                String serverSql = MessageFormat.format(addServerSql,server.toObjectArray());

                stm.addBatch(proxySql);
//                try {
//                    serverAction.addServerUser(proxy, stm);
//                } catch (Exception e) {
//                    throw new SQLException("Server操作失误");
//                }
                stm.addBatch(serverSql);

                int res[] = stm.executeBatch();

                return res.length==2?true:false;
            }
        });

        boolean result = (resO == null ? false : true);
        operateBean.setDatetime(new Date(System.currentTimeMillis()));
        if (result){
            operateBean.setF_describe("新增用户成功");
            operateBean.setResult(OperateBean.OperateResult.SUCCESS);
        }
        else{
            operateBean.setF_describe("新增用户失败");
            operateBean.setResult(OperateBean.OperateResult.FAIL);
        }
        operateLogger.addOperateLog(operateBean);

        return result;
    }

    public boolean isUserExit(final String username){
        StatementTemplate<Boolean> template =
                new StatementTemplate<Boolean>(connection,isUserExitSql);

        return template.executePreparedSQL(template.new PreparedExecutor() {
            @Override
            public Boolean execute(PreparedStatement preStatment, String exeSql) throws SQLException {
                preStatment.setString(1,username);

                ResultSet resultSet = preStatment.executeQuery();

                boolean result =  resultSet.next();
                DBToolkit.closeResultSet(resultSet);

                return result;
            }
        });
    }

    public boolean isUserExit(final User4Proxy proxy){
        assert proxy!=null;
        return isUserExit(proxy.getUsername());
    }

    public boolean isUserValidated(final String username,final String pwdHash){
        StatementTemplate<Boolean> template =
                new StatementTemplate<Boolean>(connection,isUserValidateSql);

        return template.executePreparedSQL(template.new PreparedExecutor() {
            @Override
            public Boolean execute(PreparedStatement preStatment, String exeSql) throws SQLException {
                preStatment.setString(1,username);
                preStatment.setString(2,pwdHash);

                ResultSet resultSet = preStatment.executeQuery();

                boolean result =  resultSet.next();

                DBToolkit.closeResultSet(resultSet);

                return result;
            }
        });
    }

    public boolean isUserValidated(final User4Proxy proxy){
        return isUserValidated(proxy.getUsername(),proxy.getPwdHash());
    }

    public User4Proxy getProxyById(final int proxyId){
        StatementTemplate<User4Proxy> template =
                new StatementTemplate<User4Proxy>(connection,getProxyByIdSql);

        return template.executePreparedSQL(template.new PreparedExecutor() {
            @Override
            public User4Proxy execute(PreparedStatement preStatment, String exeSql) throws SQLException {
                preStatment.setInt(1,proxyId);
                ResultSet resultSet = preStatment.executeQuery();
                if (resultSet.next()){
                    String username = resultSet.getString(2);
                    String pwdHash = resultSet.getString(3);
                    User4Proxy proxy = new User4Proxy(proxyId,username,pwdHash);

                    DBToolkit.closeResultSet(resultSet);

                    return proxy;
                }

                DBToolkit.closeResultSet(resultSet);

                return null;
            }
        });
    }

    public User4Proxy getProxyByUsername(final String username){
        StatementTemplate<User4Proxy> template =
                new StatementTemplate<User4Proxy>(connection,getProxyByUsernameSql);

        return template.executePreparedSQL(template.new PreparedExecutor() {
            @Override
            public User4Proxy execute(PreparedStatement preStatment, String exeSql) throws SQLException {
                preStatment.setString(1, username);
                ResultSet resultSet = preStatment.executeQuery();
                if (resultSet.next()){
                    int id = resultSet.getInt(1);
                    String pwdHash = resultSet.getString(3);
                    User4Proxy proxy = new User4Proxy(id,username,pwdHash);

                    DBToolkit.closeResultSet(resultSet);

                    return proxy;
                }

                DBToolkit.closeResultSet(resultSet);

                return null;
            }
        });
    }

    public boolean updatePwdHash(final String username,final String newPwdHash){
//        assert serverAction!=null;
        StatementTemplate<Boolean> template =
                new StatementTemplate<Boolean>(connection);

        Object resO = template.executeStmSQL(template.new StatmentExecutor() {
            @Override
            public Boolean execute(Statement stm) throws SQLException {
                String updateProxy = MessageFormat.format(updateProxySql,new Object[]{newPwdHash,username});
                String updateServer = MessageFormat.format(updateServerSql,new Object[]{newPwdHash,username});

                stm.addBatch(updateProxy);
                stm.addBatch(updateServer);
//                try {
//                    serverAction.updateServerUser(username,newPwdHash);
//                } catch (Exception e) {
//                    throw new SQLException("Server错误");
//                }

                int res[] = stm.executeBatch();

                boolean result = false;

                return res.length == 2 ? true : false;
            }
        });

        boolean result = (resO == null ? false : true);

        return result;

    }

    public boolean updatePwdHash(final User4Proxy newProxy){
        return updatePwdHash(newProxy.getUsername(),newProxy.getPwdHash());
    }

    public Set<Contract> getAllContracts(final int proxyId){
        StatementTemplate<Set<Contract>> template =
                new StatementTemplate<Set<Contract>>(connection,getAllContractsSql);

        return template.executePreparedSQL(template.new PreparedExecutor() {
            @Override
            public Set<Contract> execute(PreparedStatement preStatment, String exeSql) throws SQLException {
                preStatment.setInt(1,proxyId);

                ResultSet resultSet = preStatment.executeQuery();

                if (resultSet.first()){
                    Set<Contract> contracts = new HashSet<Contract>();

                    do{
                        contracts.add(reverseToContract4Get(resultSet,proxyId));
                    }while (resultSet.next());

                    DBToolkit.closeResultSet(resultSet);
                    return contracts;
                }

                DBToolkit.closeResultSet(resultSet);
                return null;
            }
        });
    }

    public Set<Contract> getRangeContracts(final int proxyId,final int start,final int end){
        StatementTemplate<Set<Contract>> template =
                new StatementTemplate<Set<Contract>>(connection,getAllContractsSql);

        return template.executePreparedSQL(template.new PreparedExecutor() {
            @Override
            public Set<Contract> execute(PreparedStatement preStatment, String exeSql) throws SQLException {
                preStatment.setInt(1,proxyId);

                ResultSet resultSet = preStatment.executeQuery();

                int index = 0;
                if (resultSet.first()){
                    Set<Contract> contracts = new HashSet<Contract>();

                    do{
                        index++;
                        if (start<=index && index<= end){
                            contracts.add(reverseToContract4Get(resultSet,proxyId));
                        }
                    }while (resultSet.next());

                    DBToolkit.closeResultSet(resultSet);
                    return contracts;
                }

                DBToolkit.closeResultSet(resultSet);
                return null;
            }
        });
    }

    public boolean deleteUser(final String username,String initiator){
//        assert serverAction!=null;
        StatementTemplate<Boolean> template =
                new StatementTemplate<Boolean>(connection);

        Object resO = template.executeStmSQL(template.new StatmentExecutor() {
            @Override
            public Boolean execute(Statement stm) throws SQLException {
                String proxySql = MessageFormat.format(deleteProxySql,username);
                String serverSql = MessageFormat.format(deleteServerSql,username);

                stm.addBatch(proxySql);
                stm.addBatch(serverSql);
//                try {
//                    serverAction.deleteServerUser(username,stm);
//                } catch (Exception e) {
//                    throw new SQLException("Server错误");
//                }
                int res[] = stm.executeBatch();

                return res.length == 2 ? true : false;
            }
        });

        boolean result = (resO == null ? false : true);
        operateBean.setDatetime(new Date(System.currentTimeMillis()));
        operateBean.setInitiator(initiator);
        if (result){
            operateBean.setF_describe("删除用户成功");
            operateBean.setResult(OperateBean.OperateResult.SUCCESS);
        }
        else{
            operateBean.setF_describe("删除用户失败");
            operateBean.setResult(OperateBean.OperateResult.FAIL);
        }
        operateLogger.addOperateLog(operateBean);

        return result;
    }

    public boolean deleteUserBySYSTEM(final String username){
        return deleteUser(username,SYSTEM_INITIATOR);
    }

    private Contract reverseToContract4Get(ResultSet resultSet,int tofriend) throws SQLException {
        int id = resultSet.getInt(1);
        String mail = resultSet.getString(2);
        String alias = resultSet.getString(3);
        String content = resultSet.getString(4);

        Contract contract = new Contract(id,mail,alias,content,tofriend);

        return contract;
    }

}
