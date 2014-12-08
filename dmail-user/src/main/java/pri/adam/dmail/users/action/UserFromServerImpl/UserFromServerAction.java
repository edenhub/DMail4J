package pri.adam.dmail.users.action.UserFromServerImpl;

import pri.adam.dmail.users.action.UserFromServer;
import pri.adam.dmail.users.model.User4Proxy;

import java.sql.Statement;
import java.text.MessageFormat;

/**
 * Created by adam on 2014/12/8.
 */
public class UserFromServerAction implements UserFromServer,UserServerSql {
    @Override
    public void addServerUser(User4Proxy proxy) throws Exception {
    }

    @Override
    public void addServerUser(final User4Proxy proxy, Statement statement) throws Exception {
        String serverSql = MessageFormat.format(addServerSql, proxy.toUser4Server().toObjectArray());
        statement.addBatch(serverSql);
    }

    @Override
    public void updateServerUser(String username, String newPwdHash) throws Exception {

    }

    @Override
    public void updateServerUser(String username, String newPwdHash, Statement statement) throws Exception {
        String updateServer = MessageFormat.format(updateServerSql,new Object[]{newPwdHash,username});
        statement.addBatch(updateServer);
    }

    @Override
    public void deleteServerUser(String username) throws Exception {

    }

    @Override
    public void deleteServerUser(String username, Statement statement) throws Exception {
        String serverSql = MessageFormat.format(deleteServerSql,username);
        statement.addBatch(serverSql);
    }
}
