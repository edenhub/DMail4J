package pri.adam.dmail.users.action;

import pri.adam.dmail.users.model.User4Proxy;

import java.sql.Statement;

/**
 * Created by adam on 2014/12/8.
 */
public interface UserFromServer {

    public void addServerUser(User4Proxy proxy) throws Exception;
    public void addServerUser(User4Proxy proxy,Statement statement) throws Exception;

    public void updateServerUser(final String username,final String newPwdHash) throws Exception;
    public void updateServerUser(final String username,final String newPwdHash,Statement statement) throws Exception;
//    public void updateServerUser(User4Proxy proxy) throws Exception;
//    public void updateServerUser(User4Proxy proxy,Statement statement) throws Exception;

    public void deleteServerUser(final String username) throws Exception;
    public void deleteServerUser(final String username,Statement statement) throws Exception;
//    public void deleteServerUser(User4Proxy proxy) throws Exception;
//    public void deleteServerUser(User4Proxy proxy,Statement statement) throws Exception;
}
