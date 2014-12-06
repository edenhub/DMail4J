package pri.adam.dmail.users.action;

import org.apache.log4j.Logger;
import pri.adam.dmail.users.model.Contract;
import pri.adam.dmail.users.model.User4Proxy;
import pri.adam.dmail.utils.dbutil.DBToolkit;
import pri.adam.dmail.utils.dbutil.StatementTemplate;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by adam on 2014/12/6.
 */
public class ContractAction implements ContractActionSql {
    private static ContractAction action = null;
    private static Connection connection = null;
    private static Logger logger = Logger.getLogger(ContractAction.class);

    private ContractAction(){}

    public static ContractAction getInstance(Connection conn){
        assert conn!=null;
        if (connection!=null && connection!=conn){
            logger.warn("使用了不同的数据库连接");
        }

        if (action==null){
            connection = conn;
            action = new ContractAction();
        }

        return action;
    }

    public boolean addContract(final Contract contract){
        StatementTemplate<Boolean> template =
                new StatementTemplate<Boolean>(connection,addContractSql);

        Object resO =  template.executePreparedSQL(template.new PreparedExecutor() {
            @Override
            public Boolean execute(PreparedStatement preStatment, String exeSql) throws SQLException {
                preStatment.setString(1, contract.getMail());
                preStatment.setString(2, contract.getAlias());
                preStatment.setString(3, contract.getContent());
                preStatment.setInt(4, contract.getTofriend());

                return preStatment.execute();
            }
        });


        if (resO == null)
            return false;
        return true;
    }

    public Contract getContractById(final int contractId){
        StatementTemplate<Contract> template =
                new StatementTemplate<Contract>(connection,findContractByIdSql);

        return template.executePreparedSQL(template.new PreparedExecutor() {
            @Override
            public Contract execute(PreparedStatement preStatment, String exeSql) throws SQLException {
                preStatment.setInt(1, contractId);

                ResultSet resultSet = preStatment.executeQuery();
                Contract contract = null;
                if (resultSet.first()) {
                    String mail = resultSet.getString(2);
                    String alias = resultSet.getString(3);
                    String content = resultSet.getString(4);
                    int friendId = resultSet.getInt(5);

                    contract = new Contract(contractId, mail, alias, content, friendId);
                }

                DBToolkit.closeResultSet(resultSet);
                return contract;
            }
        });
    }

    public User4Proxy findFriendByContractId(final int contractId){
        StatementTemplate<User4Proxy> template =
                new StatementTemplate<User4Proxy>(connection,findFriendSql);

        return template.executePreparedSQL(template.new PreparedExecutor() {
            @Override
            public User4Proxy execute(PreparedStatement preStatment, String exeSql) throws SQLException {
                preStatment.setInt(1, contractId);

                ResultSet resultSet = preStatment.executeQuery();
                User4Proxy proxy = null;

                if (resultSet.first()) {
                    int friendId = resultSet.getInt(1);
                    String username = resultSet.getString(2);
                    String pwdHash = resultSet.getString(3);

                    proxy = new User4Proxy(friendId, username, pwdHash);
                }

                DBToolkit.closeResultSet(resultSet);

                return proxy;
            }
        });
    }

    public boolean updateContractById(final int contractId,final Contract newContract){
        StatementTemplate<Boolean> template =
                new StatementTemplate<Boolean>(connection, updateContractSql);

        Object resO =  template.executePreparedSQL(template.new PreparedExecutor() {
            @Override
            public Boolean execute(PreparedStatement preStatment, String exeSql) throws SQLException {
                preStatment.setString(1,newContract.getMail());
                preStatment.setString(2,newContract.getAlias());
                preStatment.setString(3,newContract.getContent());
                preStatment.setInt(4,contractId);

                return preStatment.execute();
            }
        });

        if (resO == null)
            return false;
        return true;
    }

    public boolean deleteContractById(final int contractId){
        StatementTemplate<Boolean> template =
                new StatementTemplate<Boolean>(connection,deleteContractByIdSql);

        Object resO =  template.executePreparedSQL(template.new PreparedExecutor() {
            @Override
            public Boolean execute(PreparedStatement preStatment, String exeSql) throws SQLException {
                preStatment.setInt(1,contractId);

                return preStatment.execute();
            }
        });


        if (resO == null)
            return false;
        return true;
    }


}
